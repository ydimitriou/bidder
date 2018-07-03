package com.bluebanana.bidder.service;

import com.bluebanana.bidder.converter.CampaignConverter;
import com.bluebanana.bidder.dtos.request.BidRequestDto;
import com.bluebanana.bidder.dtos.response.BidResponseDto;
import com.bluebanana.bidder.dtos.response.CampaignDto;
import com.bluebanana.bidder.gateway.CampaignsGateway;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CampaignBidderShould {

    @InjectMocks private CampaignBidder campaignBidder;
    @Mock private CampaignConverter campaignConverter;
    @Mock private CampaignDto campaignDto1;
    @Mock private CampaignDto campaignDto2;
    @Mock private CampaignsGateway campaignsGateway;
    @Mock private BidRequestDto bidRequestDto;
    @Mock private BidResponseDto bidResponseDto;

    @Before
    public void setUp() {
        initMocks(this);
        prepareMocks();
    }

    @Test
    public void returnEmptyOptionalWhenCampaignsNotAvailable() throws Exception {
        when(campaignsGateway.retrieveCampaigns()).thenReturn(Collections.EMPTY_LIST);

        Optional<BidResponseDto> actualResponse = campaignBidder.makeBid(bidRequestDto);

        assertThat(actualResponse).isEqualTo(Optional.empty());
    }

    @Test
    public void returnEmptyOptionalWhenCampaignsNotMatchTargetingCriteria() throws Exception {
        when(bidRequestDto.getCountry()).thenReturn("USA");
        when(campaignsGateway.retrieveCampaigns()).thenReturn(Arrays.asList(campaignDto1, campaignDto2));

        Optional<BidResponseDto> actualResponse = campaignBidder.makeBid(bidRequestDto);

        assertThat(actualResponse).isEqualTo(Optional.empty());
    }

    @Test
    public void returnTheHighestPayingCampaignWhenCampaignsMatchTargetingCriteria() throws Exception {
        when(bidRequestDto.getId()).thenReturn("1234");
        when(bidRequestDto.getCountry()).thenReturn("GRE");
        when(campaignsGateway.retrieveCampaigns()).thenReturn(Arrays.asList(campaignDto1, campaignDto2));
        when(campaignConverter.toBidResponseDTO("1234", campaignDto1)).thenReturn(bidResponseDto);

        Optional<BidResponseDto> actualResponse = campaignBidder.makeBid(bidRequestDto);

        verify(campaignConverter).toBidResponseDTO("1234", campaignDto1);
        assertThat(actualResponse.isPresent()).isTrue();
    }

    private void prepareMocks() {
        when(campaignDto1.containsCountry("GRE")).thenReturn(true);
        when(campaignDto1.getPrice()).thenReturn(1.25);
        when(campaignDto2.containsCountry("GRE")).thenReturn(true);
        when(campaignDto2.getPrice()).thenReturn(1.05);
        when(campaignsGateway.retrieveCampaigns()).thenReturn(Arrays.asList(campaignDto1, campaignDto2));
    }
}
