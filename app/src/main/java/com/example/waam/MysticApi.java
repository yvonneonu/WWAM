package com.example.waam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MysticApi {

    String educationEndPoint = "school";
    @GET(educationEndPoint)
    Call<List<String>> getEducation();
}
