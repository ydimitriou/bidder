package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.dtos.response.CampaignDTO;
import com.bluebanana.bidder.dtos.response.CampaignResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TargetedCountryCampaign implements CampaignsGateway {

    private static final String RETRIEVE_CAMPAIGNS_URI = "https://campaigns.apiblueprint.org/campaigns";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CampaignDTO> retrieveCampaigns() {
        List<CampaignDTO> campaigns;
        try {
            CampaignResponseDTO campaignResponseDTO = restTemplate.
                    getForObject(RETRIEVE_CAMPAIGNS_URI,
                            CampaignResponseDTO.class);
            campaigns = campaignResponseDTO.getCampaigns();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve campaigns");
        }

        return campaigns;
    }
}
