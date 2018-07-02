package com.bluebanana.bidder.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BidDto {

    @JsonProperty("campaignId")
    private String campaignId;
    @JsonProperty("price")
    private double price;
    @JsonProperty("adm")
    private String adm;

    public BidDto(String campaignId, double price, String adm) {
        this.campaignId = campaignId;
        this.price = price;
        this.adm = adm;
    }
}
