package com.bluebanana.bidder.converter;

import com.bluebanana.bidder.dtos.request.BidDto;
import com.bluebanana.bidder.dtos.response.BidResponseDto;
import com.bluebanana.bidder.dtos.response.CampaignDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CampaignConverterShould {

    private CampaignConverter campaignConverter;
    @Before
    public void setUp() {
        campaignConverter = new CampaignConverter();
    }

    @Test
    public void convertCampaignDtoToBidResponseDto() {
        CampaignDto campaignDto = new CampaignDto("12ad34", "foo", 1.07, "bar", new ArrayList<>());
        BidResponseDto expectedBidResponseDto = new BidResponseDto("123",
                new BidDto("12ad34", 1.07, "bar"));

        BidResponseDto actualBidResponseDto = campaignConverter.toBidResponseDTO("123", campaignDto);

        assertThat(actualBidResponseDto).isEqualToComparingFieldByFieldRecursively(expectedBidResponseDto);
    }
}
