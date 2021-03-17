package com.example.waam;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SpinnerApi {
    @GET ("education")
    Call<RecordModel> getEducation (@Header("Authorization") String token);
}
