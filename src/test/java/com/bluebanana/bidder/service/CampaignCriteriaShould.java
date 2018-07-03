package com.bluebanana.bidder.service;

import com.bluebanana.bidder.dtos.request.BidRequestDto;
import com.bluebanana.bidder.dtos.response.CampaignDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CampaignCriteriaShould {

    private CampaignCriteria campaignCriteria;
    @Mock private CampaignDto campaignDto1;
    @Mock private CampaignDto campaignDto2;
    @Mock private CampaignDto campaignDto3;
    @Mock private BidRequestDto bidRequestDto;

    @Before
    public void setUp() {
        campaignCriteria = new CampaignCriteria();
        initMocks(this);
    }

    @Test
    public void returnOptionalEmptyWhenCampaignsDoNotMatchRequestedCountry() {
        prepareMocks();

        Optional<CampaignDto> actualResponse = campaignCriteria.apply(
                Arrays.asList(campaignDto1, campaignDto2, campaignDto3), bidRequestDto);

        assertThat(actualResponse).isEqualTo(Optional.empty());
    }

    @Test
    public void returnOptionalEmptyWhenCampaignListIsEmpty() {
        Optional<CampaignDto> actualResponse = campaignCriteria.apply(Collections.EMPTY_LIST, bidRequestDto);

        assertThat(actualResponse).isEqualTo(Optional.empty());
    }

    @Test
    public void returnCampaignWithHighestPriceWhenCampaignsMatchesRequestedCountry() {
        prepareMocks();
        when(campaignDto1.containsCountry("GRE")).thenReturn(true);
        when(campaignDto3.containsCountry("GRE")).thenReturn(true);

        Optional<CampaignDto> actualResponse = campaignCriteria.apply(
                Arrays.asList(campaignDto1, campaignDto2, campaignDto3), bidRequestDto);

        assertThat(actualResponse).isEqualTo(Optional.of(campaignDto1));
    }

    private void prepareMocks() {
        when(campaignDto1.containsCountry("GRE")).thenReturn(false);
        when(campaignDto1.getPrice()).thenReturn(1.25);
        when(campaignDto2.containsCountry("GRE")).thenReturn(false);
        when(campaignDto3.containsCountry("GRE")).thenReturn(false);
        when(campaignDto3.getPrice()).thenReturn(1.05);
        when(bidRequestDto.getCountry()).thenReturn("GRE");
    }
}
