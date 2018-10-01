package com.mananger.ads.campaign.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
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

  @Builder
  @NoArgsConstructor(force = true)
  @AllArgsConstructor
  @Value
  public static class CampaignRequest {
    @NotNull String title;

    @NonNull
    Long userId;

    @NotNull LocalDate startDate;
    @NotNull LocalDate endDate;

    @NotNull @Positive Long visitsGoal;

    @NonNull String imageUrl;
  }

  @Value
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor(force = true)
  public static class PlaceRequest {
    String name;

    @NonNull Double lat;

    @NonNull Double lng;
  }
}
