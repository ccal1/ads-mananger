package com.mananger.ads.campaign.service;

import com.mananger.ads.campaign.dto.CampaignDto;
import com.mananger.ads.campaign.dto.PlaceDto;
import com.mananger.ads.campaign.models.Campaign;
import com.mananger.ads.campaign.models.Place;
import com.mananger.ads.campaign.repository.CampaignRepository;
import com.mananger.ads.server.dal.BalanceRepository;
import com.mananger.ads.server.exceptions.InsuficientFundsException;
import com.mananger.ads.server.models.Balance;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CampaignService {
  private CampaignRepository campaignRepository;
  private BalanceRepository balanceRepository;

  private static final double VISIT_COST = 5.0; // REMOVE THIS CONSTANT TO A ENV

  public CampaignDto createCampaign(CampaignDto dto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();

    final double cost = calculateCost(dto);

    final Campaign campaign = dtoToEntity(dto);

    campaign.setUserId(currentPrincipalName);

    if (!hasFunds(currentPrincipalName, cost)) {
      throw new InsuficientFundsException("You don't have enough funds");
    }

    return entityToDto(campaignRepository.save(campaign));
  }

  public CampaignDto addPlace(long campaignId, PlaceDto placeDto) {
    final Campaign campaign = campaignRepository.getOne(campaignId);
    campaign.getPlaces().add(dtoToEntity(campaign, placeDto));

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

  private Place dtoToEntity(Campaign campaign, PlaceDto dto) {
    final Place place = new Place();
    place.setCampaign(campaign);
    place.setLat(dto.getLat());
    place.setLng(dto.getLng());
    place.setVisitCount(0l);

    return place;
  }

  private PlaceDto dtoToEntity(Place place) {
    return PlaceDto.builder().id(place.getId()).lat(place.getLat()).lng(place.getLng()).build();
  }

  private CampaignDto entityToDto(Campaign campaign) {
    return CampaignDto.builder()
        .cpv(campaign.getCpv())
        .visitsGoal(campaign.getVisitsGoal())
        .startDate(sqlToLocal(campaign.getStartDate()))
        .endDate(sqlToLocal(campaign.getEndDate()))
        .id(campaign.getId())
        .places(campaign.getPlaces().stream().map(this::dtoToEntity).collect(Collectors.toList()))
        .build();
  }

  private LocalDate sqlToLocal(Date sqlDate) {
    return (sqlDate == null ? null : sqlDate.toLocalDate());
  }

  private Date localToSql(LocalDate locDate) {
    return locDate == null ? null : Date.valueOf(locDate);
  }

  boolean hasFunds(String user, Double value) {
    final Optional<Balance> balance = balanceRepository.findById(user);
    final Double amount1 = balance.get().getAmount();
    return balance.map(Balance::getAmount).map(amount -> amount >= value).orElse(false);
  }
}
