package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.BidRequestDTO;
import com.bluebanana.bidder.dtos.BidResponseDTO;

import java.util.Optional;

public interface Bidder {

    public Optional<BidResponseDTO> makeBid(BidRequestDTO bidRequestDTO);
}
