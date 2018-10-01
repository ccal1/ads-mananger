package com.mananger.ads.campaign.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaceDto {
  Long id;

  Double lat;
  Double lng;
}
