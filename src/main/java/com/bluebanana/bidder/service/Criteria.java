package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.request.BidRequestDto;
import com.bluebanana.bidder.dtos.response.CampaignDto;

import java.util.List;
import java.util.Optional;

public interface Criteria {

    public Optional<CampaignDto> apply(List<CampaignDto> campaigns, BidRequestDto bidRequestDto);
}
