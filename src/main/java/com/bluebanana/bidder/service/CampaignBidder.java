package com.bluebanana.bidder.service;

import com.bluebanana.bidder.converter.CampaignConverter;
import com.bluebanana.bidder.dtos.request.BidRequestDTO;
import com.bluebanana.bidder.dtos.response.BidResponseDTO;
import com.bluebanana.bidder.dtos.response.CampaignDTO;
import com.bluebanana.bidder.gateway.CampaignsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignBidder implements Bidder {

    @Autowired
    private CampaignsGateway campaignsGateway;
    @Autowired
    private CampaignConverter campaignConverter;

    @Override
    public Optional<BidResponseDTO> makeBid(BidRequestDTO bidRequestDTO) {
        List<CampaignDTO> campaigns = campaignsGateway.retrieveCampaigns();
        Optional<CampaignDTO> campaignDTO = campaigns.stream()
                .filter(campaign ->  campaign.containsCountry(bidRequestDTO.getDeviceDTO().getGeoDTO().getCountry()))
                .max(Comparator.comparing(CampaignDTO::getPrice));

        if (campaignDTO.isPresent()) {
            return Optional.of(campaignConverter.toBidResponseDTO(campaignDTO.get(), bidRequestDTO.getId()));
        } else {
            return Optional.empty();
        }
    }
}
