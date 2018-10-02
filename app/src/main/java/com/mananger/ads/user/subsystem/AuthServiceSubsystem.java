package com.mananger.ads.user.subsystem;

import com.mananger.ads.user.models.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthServiceSubsystem {
  @FormUrlEncoded
  @POST("/login")
  Call<User> authenticateUser(
      @Field("username") String username, @Field("password") String password);
}
