package com.bluebanana.bidder.dtos.request;

public class BidDTO {

    private String campaignId;
    private Long price;
    private String adm;

    public BidDTO(String campaignId, Long price, String adm) {
        this.campaignId = campaignId;
        this.price = price;
        this.adm = adm;
    }
}
