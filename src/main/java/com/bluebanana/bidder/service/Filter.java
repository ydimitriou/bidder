package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.response.CampaignDto;

import java.util.List;

public interface Filter {

    public List<CampaignDto> byCountry(String country, List<CampaignDto> campaigns);
}
