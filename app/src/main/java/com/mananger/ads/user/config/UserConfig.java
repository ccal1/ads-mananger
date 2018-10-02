package com.mananger.ads.user.config;

import com.mananger.ads.user.subsystem.AuthServiceSubsystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class UserConfig {
  @Bean
  public AuthServiceSubsystem authServiceSubsystem() {
    return new Retrofit.Builder()
        .baseUrl("http://auth.inloco.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthServiceSubsystem.class);
  }
}
