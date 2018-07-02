package com.bluebanana.bidder.converter;

import com.bluebanana.bidder.dtos.request.BidDto;
import com.bluebanana.bidder.dtos.response.BidResponseDto;
import com.bluebanana.bidder.dtos.response.CampaignDto;
import org.springframework.stereotype.Component;

@Component
public class CampaignConverter {

    public BidResponseDto toBidResponseDTO(String id, CampaignDto campaignDto) {
        BidDto bidDto = new BidDto(campaignDto.getId(),
                campaignDto.getPrice(),
                campaignDto.getAdm());

        return new BidResponseDto(id, bidDto);
    }
}
