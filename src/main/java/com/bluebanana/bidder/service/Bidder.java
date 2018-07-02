package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.BidRequestDTO;
import com.bluebanana.bidder.dtos.BidResponseDTO;

public interface Bidder {

    public BidResponseDTO makeBid(BidRequestDTO bidRequestDTO);
}
