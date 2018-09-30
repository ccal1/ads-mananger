package com.mananger.ads.server.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@Builder
public class CampaignDto {

  Long id;

  String title;

  Long visitsGoal;

  Set<PlaceDto> places = new HashSet<>();

  LocalDate startDate;

  LocalDate endDate;
}
