package com.mananger.ads.campaign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PlaceRequest {
  String name;

  @NonNull Double lat;

  @NonNull Double lng;
}
