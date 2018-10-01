package com.mananger.ads.campaign.service;

import com.mananger.ads.campaign.dto.CampaignDto.CampaignRequest;
import com.mananger.ads.campaign.dto.CampaignDto;

public interface CampaignService {

  CampaignDto createCampaign(CampaignRequest request);
}
