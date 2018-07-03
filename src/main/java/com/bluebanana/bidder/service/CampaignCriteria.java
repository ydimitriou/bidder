package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.request.BidRequestDto;
import com.bluebanana.bidder.dtos.response.CampaignDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class CampaignCriteria implements Criteria {

    @Override
    public Optional<CampaignDto> apply(List<CampaignDto> campaigns, BidRequestDto bidRequestDto) {
        return campaigns.stream()
                .filter(campaign ->  campaign.containsCountry(bidRequestDto.getCountry()))
                .max(Comparator.comparing(CampaignDto::getPrice));
    }
}
