package com.bluebanana.bidder.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceDTO {

    @JsonProperty("os")
    private String os;
    @JsonProperty("geo")
    private GeoDTO geoDTO;

    public DeviceDTO() {
    }

    public GeoDTO getGeoDTO() {
        return geoDTO;
    }
}
