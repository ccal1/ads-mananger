package com.mananger.ads.campaign.presentation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campaigns")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CampaignController {
  //
  //  private CampaignService campaignService;
  //
  //  @PostMapping
  //  @ResponseStatus(HttpStatus.CREATED)
  //  public CampaignDto createCampaign(@RequestBody @Valid CampaignRequest request) {
  //    return campaignService.createCampaign(request);
  //  }

//  @PostMapping
//  public String createCampaign(@ModelAttribute Greeting greeting) {
//    return "result";
//  }


  @GetMapping
  String getView(@RequestBody Model model) {
    model.addAttribute("message", "Hello Spring MVC 5!");

    return "campaign";
  }
}
