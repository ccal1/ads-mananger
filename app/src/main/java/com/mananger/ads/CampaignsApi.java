package com.mananger.ads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class CampaignsApi {
  public static void main(String[] args) {
    SpringApplication.run(CampaignsApi.class, args);
  }
}
