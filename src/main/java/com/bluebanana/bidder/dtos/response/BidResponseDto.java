package com.bluebanana.bidder.dtos.response;

import com.bluebanana.bidder.dtos.request.BidDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BidResponseDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("bid")
    private BidDto bidDto;

    public BidResponseDto(String id, BidDto bidDto) {
        this.id = id;
        this.bidDto = bidDto;
    }
}
