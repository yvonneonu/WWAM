package com.example.waam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {


    @GET ("user")
    Call<RegisterResponse> getautho(@Header("Authorisation") String autokens);
    @POST("api/login")
    Call<LoginResponse>loginUser(@Body LoginRequest loginRequest);

    @POST("api/register")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);


    @POST("api/otp-request")
    Call<otpResponse> requestortp(@Body otprequest otprequest, @Header("Authorization") String token);

    @POST("api/otp-validate")
    Call<myotpresponse> otpgotten (@Body myotprequest myotprequest, @Header("Authorization") String token);




    //Call<otpResponse> requestortp(@Body otprequest otprequest, @Header("Authorization: Bearer") String token);

    //void registerUsers(String toString, String toString1);

  /*  @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );*/
}
