package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.request.BidRequestDto;
import com.bluebanana.bidder.dtos.response.BidResponseDto;

import java.util.Optional;

public interface Bidder {

    public Optional<BidResponseDto> makeBid(BidRequestDto bidRequestDto);
}
