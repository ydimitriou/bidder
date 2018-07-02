package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.dtos.response.CampaignDTO;

import java.util.List;

public interface CampaignsGateway {

    public List<CampaignDTO> retrieveCampaigns();
}
