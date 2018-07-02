package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.BidRequestDTO;
import com.bluebanana.bidder.dtos.BidResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CampaignBidder implements Bidder {

    @Override
    public Optional<BidResponseDTO> makeBid(BidRequestDTO bidRequestDTO) {
        return null;
    }
}
