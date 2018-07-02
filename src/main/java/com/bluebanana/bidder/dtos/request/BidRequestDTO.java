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

    public AppDTO getAppDTO() {
        return appDTO;
    }

    public DeviceDTO getDeviceDTO() {
        return deviceDTO;
    }
}
