package com.mananger.ads.user.services;

import com.mananger.ads.user.models.User;
import com.mananger.ads.user.subsystem.AuthServiceSubsystem;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import retrofit2.Response;

@Component
public class LoginService implements AuthenticationProvider {
  private static final Set<User> whiteListedUsers =
      Collections.singleton(new User(0L, "inloco", "inloco"));

  private final AuthServiceSubsystem authServiceSubsystem;

  @Autowired
  public LoginService(AuthServiceSubsystem authServiceSubsystem) {
    this.authServiceSubsystem = authServiceSubsystem;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password =
        Optional.of(authentication)
            .map(Authentication::getCredentials)
            .map(Object::toString)
            .orElse(null);
    try {
      Response<User> response = authServiceSubsystem.authenticateUser(username, password).execute();
      if (response.isSuccessful()) {
        User user = response.body();
        return new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
      }
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
