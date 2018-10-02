package com.mananger.ads.server.facade;


import com.mananger.ads.campaign.dto.CampaignDto;
import com.mananger.ads.campaign.service.CampaignService;
import com.mananger.ads.user.models.User;
import com.mananger.ads.user.services.LoginService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Facade {
  CampaignService campaignService;
  LoginService loginService;

  public CampaignDto createCampaign(CampaignDto campaign) {
    campaign.toBuilder().userId("divino");
    return campaignService.createCampaign(campaign);
  }

  public void loginUser(String username, String password) {
    loginService.authenticate(new UsernamePasswordAuthenticationToken(username, password));
  }
}
