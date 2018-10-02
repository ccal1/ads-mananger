package com.mananger.ads.campaign.service;

import com.mananger.ads.campaign.dto.CampaignDto;
import com.mananger.ads.campaign.dto.CampaignRequest;

public interface CampaignService {

  CampaignDto createCampaign(CampaignRequest request);
}
