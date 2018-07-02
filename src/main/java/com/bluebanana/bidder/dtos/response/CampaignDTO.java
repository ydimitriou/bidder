package com.bluebanana.bidder.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CampaignDTO {

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

    public CampaignDTO() {
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
