package com.bluebanana.bidder.converter;

import com.bluebanana.bidder.dtos.request.BidDTO;
import com.bluebanana.bidder.dtos.response.BidResponseDTO;
import com.bluebanana.bidder.dtos.response.CampaignDTO;
import org.springframework.stereotype.Component;

@Component
public class CampaignConverter {

    public BidResponseDTO toBidResponseDTO(CampaignDTO campaignDTO, String id) {
        BidDTO bidDTO = new BidDTO(campaignDTO.getId(),
                campaignDTO.getPrice(),
                campaignDTO.getAdm());

        return new BidResponseDTO(id, bidDTO);
    }
}
