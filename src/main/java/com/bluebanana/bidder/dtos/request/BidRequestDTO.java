package com.bluebanana.bidder.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BidRequestDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("app")
    private AppDTO appDTO;
    @JsonProperty("device")
    private DeviceDTO deviceDTO;

    public BidRequestDTO() {
    }

    public String getId() {
        return id;
    }

    public DeviceDTO getDeviceDTO() {
        return deviceDTO;
    }

    public String getCountry() {
        return deviceDTO.getGeoDTO().getCountry();
    }
}
