package com.mananger.ads.server.services;

import com.mananger.ads.server.controllers.CampaignRequest;
import com.mananger.ads.server.dto.CampaignDto;

public interface CampaignService {

  CampaignDto createCampaign(CampaignRequest request);
}
