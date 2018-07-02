package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.BidRequestDTO;
import com.bluebanana.bidder.dtos.BidResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class CampaignBidder implements Bidder {

    @Override
    public BidResponseDTO makeBid(BidRequestDTO bidRequestDTO) {
        return null;
    }
}
