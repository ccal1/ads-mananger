package com.mananger.ads.server.config;

import com.mananger.ads.campaign.dto.CampaignDto;
import com.mananger.ads.campaign.dto.CampaignDto.CampaignDtoBuilder;
import com.mananger.ads.campaign.models.Campaign;
import java.sql.Date;
import java.time.LocalDate;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    final ModelMapper modelMapper = new ModelMapper();
    modelMapper
        .createTypeMap(Campaign.class, CampaignDtoBuilder.class)
        .addMappings(
            mapper -> {
              mapper.map(
                  campaign -> sqlToLocal(campaign.getEndDate()), CampaignDtoBuilder::endDate);
              mapper.map(
                  campaign -> sqlToLocal(campaign.getStartDate()), CampaignDtoBuilder::startDate);
            });
    modelMapper
        .createTypeMap(CampaignDto.class, Campaign.class)
        .addMappings(
            mapper -> {
              mapper.map(campaign -> localToSql(campaign.getEndDate()), Campaign::setEndDate);
              mapper.map(campaign -> localToSql(campaign.getStartDate()), Campaign::setStartDate);
            });
    return modelMapper;
  }

  private LocalDate sqlToLocal(Date sqlDate) {
    return (sqlDate == null ? null : sqlDate.toLocalDate());
  }

  private Date localToSql(LocalDate locDate) {
    return locDate == null ? null : Date.valueOf(locDate);
  }
}
