package com.bluebanana.bidder.integration;

import com.bluebanana.bidder.BidderApplication;
import com.bluebanana.bidder.fixture.FixtureLoader;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BidderApplication.class})
public class BidderShould {

    private static final String BID_MATCHING_WITH_CAMPAIGN = "fixtures/bid/bid_matching_with_campaign.json";
    private static final String BID_NOT_MATCHING_WITH_CAMPAIGN = "fixtures/bid/bid_not_matching_with_campaigns.json";
    private static final String MATCHING_CAMPAIGNS = "fixtures/campaign/matching_campaigns.json";
    private static final String NO_MATCHING_CAMPAIGNS = "fixtures/campaign/no_matching_campaigns.json";
    private static final String SUCCESS_BID_RESPONSE="fixtures/bid/expected_success_bid_response.json";
    private static final FixtureLoader fixtureLoader = new FixtureLoader();
    private String bidRequest;
    private String campaigns;
    private MockMvc mockMvc;

    @MockBean
    RestTemplate restTemplate;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void respondWithTheBidForHighestPayingCampaignThatMatchesTheTargetingCriteria() throws Exception {
        prepareFixtures(BID_MATCHING_WITH_CAMPAIGN, MATCHING_CAMPAIGNS);
        String bidSuccessResponse = fixtureLoader.load(SUCCESS_BID_RESPONSE);
        prepareMockCampaigns();

        MvcResult result = mockMvc.perform(post("/bid")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bidRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        JSONObject actualContentResponse = new JSONObject(result.getResponse().getContentAsString());
        JSONObject expectedContentResponse = new JSONObject(bidSuccessResponse);

        assertThat(actualContentResponse).isEqualToComparingFieldByFieldRecursively(expectedContentResponse);
    }

    @Test
    public void respondWithoutBidAndNoContentHttpStatusWhenThereAreNoMatchingCampaigns() throws Exception {
        prepareFixtures(BID_NOT_MATCHING_WITH_CAMPAIGN, NO_MATCHING_CAMPAIGNS);
        prepareMockCampaigns();

        mockMvc.perform(post("/bid")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bidRequest))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void respondWithoutBidAndNoContentHttpStatusWhenThereAreNoCampaignsAvailable() throws Exception {
        prepareFixtures(BID_NOT_MATCHING_WITH_CAMPAIGN, NO_MATCHING_CAMPAIGNS);
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn("[]");

        mockMvc.perform(post("/bid")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bidRequest))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    private void prepareFixtures(String bidPath, String campaignPath) {
        bidRequest = fixtureLoader.load(bidPath);
        campaigns = fixtureLoader.load(campaignPath);
    }

    private void prepareMockCampaigns() {
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(campaigns);
    }
}
