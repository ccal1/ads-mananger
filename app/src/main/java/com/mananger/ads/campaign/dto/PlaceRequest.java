package com.mananger.ads.campaign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Data
@NoArgsConstructor(force = true)
public class PlaceRequest {
  @NonNull Double lat;

  @NonNull Double lng;
}
