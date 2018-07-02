package com.bluebanana.bidder.dtos.response;

import java.util.List;

public class CampaignDTO {

    private String id;
    private String name;
    private Long price;
    private String adm;
    private List<String> targetedCountries;

    public String getId() {
        return id;
    }

    public Long getPrice() {
        return price;
    }

    public String getAdm() {
        return adm;
    }

    public boolean containsCountry(String country) {
        return targetedCountries.contains(country);
    }
}
