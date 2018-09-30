package com.mananger.ads.server.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaceDto {
  Long id;

  Double lat;
  Double lng;
}
