package com.bluebanana.bidder.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BidDTO {

    @JsonProperty("campaignId")
    private String campaignId;
    @JsonProperty("price")
    private double price;
    @JsonProperty("adm")
    private String adm;

    public BidDTO(String campaignId, double price, String adm) {
        this.campaignId = campaignId;
        this.price = price;
        this.adm = adm;
    }
}
