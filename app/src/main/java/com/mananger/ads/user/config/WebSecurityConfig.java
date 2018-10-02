package com.mananger.ads.user.config;

import com.mananger.ads.user.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  final LoginService loginService;

  @Autowired
  public WebSecurityConfig(LoginService loginService) {
    this.loginService = loginService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/campaign", true)
        .permitAll()
        .and()
        .logout()
        .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(loginService);
    auth.inMemoryAuthentication()
        .withUser(
            User.withDefaultPasswordEncoder()
                .username("inloco")
                .password("inloco")
                .roles("PUBLISHER"));
  }
}
