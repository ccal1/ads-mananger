package com.mananger.ads.server.services;

import com.mananger.ads.server.controllers.CampaignRequest;
import com.mananger.ads.server.dal.Campaign;
import com.mananger.ads.server.dal.CampaignRepository;
import com.mananger.ads.server.dto.CampaignDto;
import com.mananger.ads.server.dto.CampaignDto.CampaignDtoBuilder;
import com.mananger.ads.server.exceptions.InsuficientFundsException;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CampaignServiceImpl implements CampaignService {
  private ModelMapper modelMapper;
  private CampaignRepository campaignRepository;
  private UserService userService;

  private static final double VISIT_COST = 5.0; // REMOVE THIS CONSTANT TO A ENV

  @Override
  public CampaignDto createCampaign(CampaignRequest request) {
    final CampaignDto dto = modelMapper.map(request, CampaignDto.class);

    final double cost = calculateCost(dto);

    if(!userService.hasFunds(request.getUserId(), cost)) {
      throw new InsuficientFundsException("You don't have enough funds");
    }

    final Campaign campaign = campaignRepository.save(modelMapper.map(dto, Campaign.class));

    return modelMapper.map(campaign, CampaignDtoBuilder.class).build();
  }

  public double calculateCost(CampaignDto campaignDto) {
    return campaignDto.getVisitsGoal() * VISIT_COST;
  }
}
