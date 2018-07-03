package com.bluebanana.bidder.service;

import com.bluebanana.bidder.converter.CampaignConverter;
import com.bluebanana.bidder.dtos.request.BidRequestDto;
import com.bluebanana.bidder.dtos.response.BidResponseDto;
import com.bluebanana.bidder.dtos.response.CampaignDto;
import com.bluebanana.bidder.gateway.CampaignsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignBidder implements Bidder {

    @Autowired
    private CampaignsGateway campaignsGateway;
    @Autowired
    private CampaignConverter campaignConverter;
    @Autowired
    private Filter campaignFilter;
    @Autowired
    private CampaignCriteria campaignCriteria;

    @Override
    public Optional<BidResponseDto> makeBid(BidRequestDto bidRequestDto) {
        List<CampaignDto> campaigns = campaignsGateway.retrieveCampaigns();
        campaigns = campaignFilter.byCountry(bidRequestDto.getCountry(), campaigns);
        Optional<CampaignDto> campaignDto = campaignCriteria.withMaxPrice(campaigns);

        if (!campaignDto.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(campaignConverter.toBidResponseDTO(bidRequestDto.getId(), campaignDto.get()));
    }
}
