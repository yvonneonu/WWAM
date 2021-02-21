package com.example.waam;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("{api_key}/SMS/+91{users_phone_no}/otp_request")
    Call<MessaseResponse> sentOTP(@Path("api_key")String apiKey, @Path("users_phone_no")String phone_no);
    @POST("{api_key}/SMS/VERIFY/{session_id}/otp_resend")
    Call<MessaseResponse> verifyOTP(@Path("api_key")String apiKey, @Path("session_id")String session_id,@Path("otp_entered_by_user")String otp_entered_by_user);


}
