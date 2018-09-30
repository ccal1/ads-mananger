package com.mananger.ads.server.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CampaignDto {

  Long id;

  String title;

  Long visitsGoal;

  Set<PlaceDto> places = new HashSet<>();

  LocalDate startDate;

  LocalDate endDate;
}
