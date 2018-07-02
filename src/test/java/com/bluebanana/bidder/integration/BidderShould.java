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

    @MockBean
    RestTemplate restTemplate;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private FixtureLoader fixtureLoader = new FixtureLoader();

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void returnBidWithMaxPriceWhenCountryExistsInCampaign() throws Exception {
        String bidRequest = fixtureLoader.load("fixtures/bid/bid_matching_with_campaign.json");
        String campaigns = fixtureLoader.load("fixtures/campaign/matching_campaigns.json");
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
}
