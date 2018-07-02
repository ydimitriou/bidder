package com.bluebanana.bidder.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoDto {

    @JsonProperty("country")
    private String country;
    @JsonProperty("lat")
    private Long lat;
    @JsonProperty("lon")
    private Long lon;

    public GeoDto() {
    }

    public String getCountry() {
        return country;
    }
}
