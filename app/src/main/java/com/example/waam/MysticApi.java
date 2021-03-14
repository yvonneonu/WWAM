package com.example.waam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MysticApi {
    @GET("education")
    Call<List<String>> getEducation(@Header("Authorization")String token);
}
