package com.example.waam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {


    @GET ("user")
    Call<LoginResponse> getautho(@Header("Authorisation") String autHeads);
    @POST("api/login")
    Call<LoginResponse>loginUser(@Body LoginRequest loginRequest);

    @POST("api/register")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);

  /*  @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );*/
}
