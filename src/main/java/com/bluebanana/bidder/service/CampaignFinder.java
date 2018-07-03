package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.response.CampaignDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class CampaignFinder implements CampaignCriteria {

    @Override
    public Optional<CampaignDto> withMaxPrice(List<CampaignDto> campaigns) {
        return campaigns.stream().max(Comparator.comparing(CampaignDto::getPrice));
    }
}
