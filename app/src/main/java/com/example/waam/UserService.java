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
import retrofit2.http.Path;

public interface UserService {


    @GET ("user")
    Call<RegisterResponse> getautho(@Header("Authorisation") String autokens);

    @GET ("profile")
    Call<profileresponse> responseuser(@Body profilerequest profilerequest);

    @POST("api/login")
    Call<LoginResponse>loginUser(@Body LoginRequest loginRequest);

    @POST("api/register")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);


    @POST("api/otp-request")
    Call<otpResponse> requestortp(@Body otprequest otprequest, @Header("Authorization") String token);

   @POST("api/otp-validate")
    Call<myotpresponse> verifyOTP(@Body myotprequest myotprequest);

    //@POST("api/otp-validate")
    //Call<MessageResponse> verifyOTP(@Path("api_key")String apiKey, @Path("session_id")String session_id, @Path("otp_entered_by_user")String otp_entered_by_user);



    //Call<otpResponse> requestortp(@Body otprequest otprequest, @Header("Authorization: Bearer") String token);

    //void registerUsers(String toString, String toString1);

  /*  @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );*/
}
