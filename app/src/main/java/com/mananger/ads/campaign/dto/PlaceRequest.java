package com.mananger.ads.campaign.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor(force = true)
public class PlaceRequest {
  @NonNull Double lat;

  @NonNull Double lng;
}
