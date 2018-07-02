package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.dtos.response.CampaignDto;

import java.util.List;

public interface CampaignsGateway {

    public List<CampaignDto> retrieveCampaigns();
}
