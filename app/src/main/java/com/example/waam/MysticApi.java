package com.example.waam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MysticApi {
    @GET("education")
    Call<RecordModel> getEducation(@Header("Authorization")String token);
}
