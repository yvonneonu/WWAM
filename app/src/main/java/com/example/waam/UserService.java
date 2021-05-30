package com.example.waam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface UserService {


 //   @GET("user")
    //Call<RegisterResponse> getautho(@Header("Authorisation") String autokens);

    //@GET("api/connectycubeverify")
   // Call<>
   // @GET ("profile")
    //Call<profileresponse> responseuser(@Body profilerequest profilerequest);

   //@GET("api/profile")
   // Call<EthnicityRecordmodel> getEthnicity (@Header("Authorization")String token);

    @GET("api/userinterest")
    Call<List<DispalyInterest>>display(@Header("Authorization")String token);

    @POST("api/forgot-password")
    Call<EmailResponse>emailLink(@Body emailAddress getEmailAddress);

    @POST("api/interestuser")
    Call<InterestResponds>interest(@Body List<InterestRequest> interestResponds, @Header("Authorization") String token);

    @POST("api/friendrequest")
    Call<FriendResponseModel> getFriendRequest(@Body FriendRequestModel friendRequestModel, @Header("Authorization") String token);

    @PATCH("api/profile")
    Call<SpinnerRequest> getSpinner(@Body SpinnerResponse getSpinner, @Header("Authorization") String token);

    @PATCH("api/profile")
    Call<GetImage>getimage(@Body GetImageResponse getImageResponse, @Header("Authorization") String token);

    @POST("api/login")
    Call<LoginResponse>loginUser(@Body LoginRequest loginRequest);

    @POST("api/register")
    Call<RegisterResponse> registerUsers(@Body WaamUser waamUser);


    @POST("api/otp-request")
    Call<otpResponse> requestortp(@Body otprequest otprequest, @Header("Authorization") String token);

    @POST("api/otp-validate")
    Call<Myotpresponse> otpgotten (@Body Myotprequest myotprequest, @Header("Authorization") String bearer);
    //, @Header("Authorization") String token);
    @POST("api/otp-resend")
    Call<Resendotpresponse> resendotpgotten (@Body Resendotprequest resendotprequest, @Header("Authorization") String toke);

    @POST("api/ephemeralkey")
    Call<Ephemeral> getEphemeral(@Body EphemeralPost ephemeralPost, @Header("Authorization") String token);


    @POST("api/upgrademembership")
    Call<UpgradeMembershipResponse> upgrade(@Body UpgradeMembership upgrade, @Header("Authorization") String token);


    @POST("api/upgrademembership2")
    Call<UpgradeMembershipResponse> upgr(@Body Upgara upgra , @Header("Authorization") String token);

    //Call<otpResponse> requestortp(@Body otprequest otprequest, @Header("Authorization: Bearer") String token);

    //void registerUsers(String toString, String toString1);

  /*  @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );*/
}
