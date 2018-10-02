package com.mananger.ads.campaign.presentation;

import com.mananger.ads.campaign.dto.CampaignDto;
import com.mananger.ads.campaign.dto.CampaignRequest;
import com.mananger.ads.campaign.dto.PlaceDto;
import com.mananger.ads.campaign.dto.PlaceRequest;
import com.mananger.ads.server.facade.Facade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campaign")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CampaignController {
  Facade facade;

  @PostMapping
  public String createCampaign(
      @ModelAttribute CampaignRequest request, Model model, HttpSession session) {

    final CampaignDto campaign = facade.createCampaign(requestToDto(request));

    model.addAttribute("place", new PlaceRequest());
    model.addAttribute("campaign", campaign);
    session.setAttribute("campaign", campaign);
    return "campaign";
  }

  @PostMapping("/place")
  public String addPlace(@ModelAttribute PlaceRequest request, Model model, HttpSession session) {

    CampaignDto campaign = (CampaignDto) session.getAttribute("campaign");
    campaign = facade.addPlace(campaign.getId(), requestToDto(request));
    model.addAttribute("place", new PlaceRequest());
    model.addAttribute("campaign", campaign);
    session.setAttribute("campaign", campaign);

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

  private PlaceDto requestToDto(PlaceRequest request) {
    return PlaceDto.builder().lng(request.getLng()).lat(request.getLat()).build();
  }

  private LocalDate stringToLocalDate(String date) {
    return date == null ? null : LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
