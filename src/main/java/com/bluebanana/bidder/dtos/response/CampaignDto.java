package com.bluebanana.bidder.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CampaignDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private double price;
    @JsonProperty("adm")
    private String adm;
    @JsonProperty("targetedCountries")
    private List<String> targetedCountries;

    public CampaignDto() {
    }

    public CampaignDto(String id,
                       String name,
                       double price,
                       String adm,
                       List<String> targetedCountries
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.adm = adm;
        this.targetedCountries = targetedCountries;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getAdm() {
        return adm;
    }

    public boolean containsCountry(String country) {
        return targetedCountries.contains(country);
    }
}
