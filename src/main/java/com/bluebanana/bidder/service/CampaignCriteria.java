package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.response.CampaignDto;

import java.util.List;
import java.util.Optional;

public interface CampaignCriteria {

    public Optional<CampaignDto> withMaxPrice(List<CampaignDto> campaigns);
}
