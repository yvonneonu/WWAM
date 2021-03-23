package com.example.waam;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

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
    Call<Myotpresponse> otpgotten (@Body Myotprequest myotprequest, @Header("Authorization") String bearer);
    //, @Header("Authorization") String token);
    @POST("api/otp-resend")
    Call<Resendotpresponse> resendotpgotten (@Body Resendotprequest resendotprequest, @Header("Authorization") String toke);

    @POST("ephemeralkey")
    Call<Ephemeral> getEphemeral(@Body String epheral,@Header("Authorization") String token);


    @POST("upgrademembership")
    Call<UpgradeMembership> upgrade(@Body UpgradeMembership upgrade, @Header("Authorization") String token);

    //Call<otpResponse> requestortp(@Body otprequest otprequest, @Header("Authorization: Bearer") String token);

    //void registerUsers(String toString, String toString1);

  /*  @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );*/
}
