package com.mananger.ads.server.services;

import com.mananger.ads.server.dal.Campaign;
import com.mananger.ads.server.dto.CampaignDto;
import com.mananger.ads.server.dto.CampaignDto.CampaignDtoBuilder;
import java.sql.Date;
import java.time.LocalDate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    final ModelMapper modelMapper = new ModelMapper();
    final TypeMap<Campaign, CampaignDtoBuilder> typeMapToDto =
        modelMapper.getTypeMap(Campaign.class, CampaignDtoBuilder.class);

    typeMapToDto.addMapping(
        campaign -> sqlToLocal(campaign.getEndDate()), CampaignDtoBuilder::endDate);
    typeMapToDto.addMapping(
        campaign -> sqlToLocal(campaign.getStartDate()), CampaignDtoBuilder::startDate);

    final TypeMap<CampaignDto, Campaign> typeMapfromDto =
        modelMapper.getTypeMap(CampaignDto.class, Campaign.class);

    typeMapfromDto.addMapping(campaign -> localToSql(campaign.getEndDate()), Campaign::setEndDate);
    typeMapfromDto.addMapping(
        campaign -> localToSql(campaign.getStartDate()), Campaign::setStartDate);

    return modelMapper;
  }

  private LocalDate sqlToLocal(Date sqlDate) {
    return (sqlDate == null ? null : sqlDate.toLocalDate());
  }

  private Date localToSql(LocalDate locDate) {
    return locDate == null ? null : Date.valueOf(locDate);
  }
}
