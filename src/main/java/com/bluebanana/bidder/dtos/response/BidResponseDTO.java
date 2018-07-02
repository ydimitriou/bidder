package com.bluebanana.bidder.dtos.response;

import com.bluebanana.bidder.dtos.request.BidDTO;

public class BidResponseDTO {

    private String id;
    private BidDTO bidDto;

    public BidResponseDTO(String id, BidDTO bidDto) {
        this.id = id;
        this.bidDto = bidDto;
    }
}
