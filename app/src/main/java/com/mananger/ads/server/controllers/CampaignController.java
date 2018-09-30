package com.mananger.ads.server.controllers;

import com.mananger.ads.server.dto.CampaignDto;
import com.mananger.ads.server.services.CampaignService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaigns/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CampaignController {

  private CampaignService campaignService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CampaignDto createCampaign(@RequestBody @Valid CampaignRequest request) {
    return campaignService.createCampaign(request);
  }
}
