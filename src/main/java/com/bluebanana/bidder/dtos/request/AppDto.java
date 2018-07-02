package com.bluebanana.bidder.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    public AppDto() {
    }
}
