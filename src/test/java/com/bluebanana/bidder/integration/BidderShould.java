package com.bluebanana.bidder.integration;

import com.bluebanana.bidder.BidderApplication;
import com.bluebanana.bidder.utils.FixtureLoader;
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
    private static final String MATCHING_CAMPAIGNS = "fixtures/campaign/matching_campaigns.json";
    private static final FixtureLoader fixtureLoader = new FixtureLoader();
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
        String bidRequest = fixtureLoader.load(BID_MATCHING_WITH_CAMPAIGN);
        String campaigns = fixtureLoader.load(MATCHING_CAMPAIGNS);
        String bidSuccessResponse = fixtureLoader.load("fixtures/bid/expected_success_bid_response.json");

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(campaigns);

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
        String bidRequest = fixtureLoader.load("fixtures/bid/bid_not_matching_with_campaigns.json");
        String campaigns = fixtureLoader.load("fixtures/campaign/no_matching_campaigns.json");

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(campaigns);

        mockMvc.perform(post("/bid")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bidRequest))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}
