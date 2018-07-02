package com.bluebanana.bidder.dtos.response;

import com.bluebanana.bidder.dtos.request.BidDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BidResponseDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("bid")
    private BidDTO bidDto;

    public BidResponseDTO(String id, BidDTO bidDto) {
        this.id = id;
        this.bidDto = bidDto;
    }
}
