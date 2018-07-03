package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.response.CampaignDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampaignFilter implements Filter {

    @Override
    public List<CampaignDto> byCountry(String country, List<CampaignDto> campaigns) {
        return campaigns.stream().filter(campaign -> campaign.containsCountry(country)).collect(Collectors.toList());
    }
}
