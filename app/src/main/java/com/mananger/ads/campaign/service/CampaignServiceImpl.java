package com.mananger.ads.campaign.service;

import com.mananger.ads.campaign.dto.CampaignDto;
import com.mananger.ads.campaign.models.Campaign;
import com.mananger.ads.campaign.repository.CampaignRepository;
import com.mananger.ads.server.dal.BalanceRepository;
import com.mananger.ads.server.exceptions.InsuficientFundsException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CampaignServiceImpl implements CampaignService {
  private CampaignRepository campaignRepository;
  private BalanceRepository balanceRepository;

  private static final double VISIT_COST = 5.0; // REMOVE THIS CONSTANT TO A ENV

  @Override
  public CampaignDto createCampaign(CampaignDto dto) {

    final double cost = calculateCost(dto);

    final Campaign campaign = dtoToEntity(dto);


    campaign.setUserId("divino");

    if (!hasFunds("divino", cost)) {
      throw new InsuficientFundsException("You don't have enough funds");
    }

    return entityToDto(campaignRepository.save(campaign));
  }

  public double calculateCost(CampaignDto campaignDto) {
    return campaignDto.getVisitsGoal() * VISIT_COST;
  }

  private Campaign dtoToEntity(CampaignDto dto) {
    Campaign campaign = new Campaign();
    campaign.setEndDate(localToSql(dto.getEndDate()));
    campaign.setStartDate(localToSql(dto.getStartDate()));
    campaign.setVisitsGoal(dto.getVisitsGoal());
    campaign.setCpv(dto.getCpv());
    return campaign;
  }


  private CampaignDto entityToDto(Campaign campaign) {
    return CampaignDto.builder().cpv(campaign.getCpv()).visitsGoal(campaign.getVisitsGoal()).startDate(sqlToLocal(campaign.getStartDate())).endDate(sqlToLocal(campaign.getEndDate())).id(campaign.getId()).build();
  }


  private LocalDate sqlToLocal(Date sqlDate) {
    return (sqlDate == null ? null : sqlDate.toLocalDate());
  }

  private Date localToSql(LocalDate locDate) {
    return locDate == null ? null : Date.valueOf(locDate);
  }

  boolean hasFunds(String user, Double value) {
    return Optional.ofNullable(balanceRepository.balanceById(user)).map(amount -> amount >= value)
        .orElse(false);
  }
}
