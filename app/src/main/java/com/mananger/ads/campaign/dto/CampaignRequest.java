package com.mananger.ads.campaign.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Value
public class CampaignRequest {
  @NotNull String title;

  @NonNull Long userId;

  @NotNull LocalDate startDate;
  @NotNull LocalDate endDate;

  @NotNull @Positive Long visitsGoal;

  @NonNull String imageUrl;
}
