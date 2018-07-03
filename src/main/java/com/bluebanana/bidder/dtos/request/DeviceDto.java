package com.bluebanana.bidder.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceDto {

    @JsonProperty("os")
    private String os;
    @JsonProperty("geo")
    private GeoDto geoDto;

    public GeoDto getGeoDto() {
        return geoDto;
    }
}
