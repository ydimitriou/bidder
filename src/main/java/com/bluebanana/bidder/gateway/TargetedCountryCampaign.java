package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.dtos.response.CampaignDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TargetedCountryCampaign implements CampaignsGateway {

    private static final String RETRIEVE_CAMPAIGNS_URI = "https://campaigns.apiblueprint.org/campaigns";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CampaignDTO> retrieveCampaigns() {
        List<CampaignDTO> campaigns;
        try {
            String response = restTemplate.getForObject(RETRIEVE_CAMPAIGNS_URI, String.class);
            campaigns = objectMapper.readValue(response, new TypeReference<List<CampaignDTO>>(){});
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve campaigns");
        }

        return campaigns;
    }
}
