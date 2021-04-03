package com.example.waam;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Mysapi {

    @POST("upgrademembership2")
    Call<UpgradeMembershipResponse> upgr(@Body Upgara upgra , @Header("Authorization") String token);
}
