package com.bluebanana.bidder.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BidRequestDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("app")
    private AppDto appDto;
    @JsonProperty("device")
    private DeviceDto deviceDTO;

    public String getId() {
        return id;
    }

    public String getCountry() {
        return deviceDTO.getGeoDto().getCountry();
    }
}
