package com.bluebanana.bidder.gateway;

import com.bluebanana.bidder.dtos.response.CampaignDto;
import com.bluebanana.bidder.fixture.FixtureLoader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TargetedCountryCampaignShould {

    private static final String CAMPAIGNS_AVAILABLE = "fixtures/campaign/matching_campaigns.json";
    private FixtureLoader fixtureLoader = new FixtureLoader();
    @InjectMocks private TargetedCountryCampaign targetedCountryCampaign;
    @Mock private RestTemplate restTemplate;
    @Rule public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void returnEmptyArrayListWhenCampaignsNotAvailable() throws Exception {
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn("[]");

        List<CampaignDto> actualResponse = targetedCountryCampaign.retrieveCampaigns();

        assertThat(actualResponse).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    public void returnAnArrayListWithCampaignsWhenCampaignsAvailable() throws Exception {
        String campaigns = fixtureLoader.load(CAMPAIGNS_AVAILABLE);
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(campaigns);

        List<CampaignDto> actualResponse = targetedCountryCampaign.retrieveCampaigns();

        assertThat(actualResponse.size()).isEqualTo(5);
    }

    @Test
    public void throwProperRunTimeExceptionWhenRetrieveCampaignsFail() throws Exception {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Failed to retrieve campaigns");

        doThrow(new RuntimeException()).when(restTemplate).getForObject(anyString(), any(Class.class));

        targetedCountryCampaign.retrieveCampaigns();
    }
}
