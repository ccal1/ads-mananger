package com.mananger.ads.campaign.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor(force = true)
public class CampaignRequest {

  @NotNull String startDate;
  @NotNull String endDate;

  @NotNull @Positive Long visitsGoal;

  @NonNull Double cpv;
}
