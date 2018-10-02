package com.mananger.ads.campaign.presentation;

import com.mananger.ads.campaign.dto.CampaignDto;
import com.mananger.ads.campaign.dto.CampaignRequest;
import com.mananger.ads.campaign.dto.PlaceRequest;
import com.mananger.ads.server.facade.Facade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campaign")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CampaignController {
  Facade facade;


  @PostMapping
  public String createCampaign(@ModelAttribute CampaignRequest request, Model model) {

    final CampaignDto campaign = facade.createCampaign(requestToDto(request));

    model.addAttribute("place", new PlaceRequest());
    model.addAttribute("campaign", campaign);
    return "campaign";
  }

  @GetMapping("/{cp_id}/addplace")
  public String getPlaceForm(@PathVariable("cp_id") long campaignId, Model model) {


    model.addAttribute("cp_id", campaignId);
    return "addplace";
  }

  @PostMapping("/addplace")
  public String addPlace(@ModelAttribute PlaceRequest request, Model model) {


//    model.addAttribute("campaign", CampaignDto.builder().id(request.getCampaignId()).build());
    return "campaign";
  }


  @PostMapping("/{cp_id}/addplace")
  public String addPlace(@PathVariable("cp_id") long campaignId, @ModelAttribute PlaceRequest request, Model model) {


    model.addAttribute("campaign", CampaignDto.builder().id(campaignId).build());
    return "campaign";
  }

  @GetMapping
  String getForm(Model model) {
    model.addAttribute("campaign", new CampaignRequest());

    return "campaignform";
  }

  private CampaignDto requestToDto(CampaignRequest request) {
    return CampaignDto.builder()
        .endDate(stringToLocalDate(request.getEndDate()))
        .startDate(stringToLocalDate(request.getStartDate()))
        .visitsGoal(request.getVisitsGoal())
        .cpv(request.getCpv())
        .build();
  }

  private LocalDate stringToLocalDate(String date) {
    return date == null ? null : LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
