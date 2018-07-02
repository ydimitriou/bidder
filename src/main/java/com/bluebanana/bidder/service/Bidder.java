package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.request.BidRequestDTO;
import com.bluebanana.bidder.dtos.response.BidResponseDTO;

import java.util.Optional;

public interface Bidder {

    public Optional<BidResponseDTO> makeBid(BidRequestDTO bidRequestDTO);
}
