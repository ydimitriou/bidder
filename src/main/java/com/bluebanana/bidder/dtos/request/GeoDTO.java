package com.bluebanana.bidder.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoDTO {

    @JsonProperty("country")
    private String country;
    @JsonProperty("lat")
    private Long lat;
    @JsonProperty("lon")
    private Long lon;

    public GeoDTO() {
    }

    public String getCountry() {
        return country;
    }
}
