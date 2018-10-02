package com.mananger.ads.campaign.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

  @Builder.Default List<PlaceDto> places = new ArrayList<>();

  LocalDate startDate;

  LocalDate endDate;
}
