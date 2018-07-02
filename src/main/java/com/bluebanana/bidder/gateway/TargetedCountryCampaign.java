package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.dtos.response.CampaignDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TargetedCountryCampaign implements CampaignsGateway {

    private static final String CAMPAIGN_RETRIEVE_ERROR = "Failed to retrieve campaigns";
    private static final String RETRIEVE_CAMPAIGNS_URI = "https://campaigns.apiblueprint.org/campaigns";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CampaignDto> retrieveCampaigns() {
        List<CampaignDto> campaigns;
        try {
            String response = restTemplate.getForObject(RETRIEVE_CAMPAIGNS_URI, String.class);
            campaigns = objectMapper.readValue(response, new TypeReference<List<CampaignDto>>(){});
        } catch (Exception e) {
            throw new RuntimeException(CAMPAIGN_RETRIEVE_ERROR);
        }

        return campaigns;
    }
}
