package com.example.waam;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MysticApi {
    @GET("education")
    Call<RecordModel> getEducation(@Header("Authorization")String token);

    @GET("occupation")
    Call<OcupationRecordModel> getOccupation(@Header("Authorization")String token);

    @GET("bodytype")
    Call<BodyTypeRecordModel> getBody (@Header("Authorization")String token);

    @GET("ethnicity")
    Call<EthnicityRecordmodel> getEthnicity (@Header("Authorization")String token);

    @GET("faith")
    Call<FaithRecordModel> getFaith (@Header("Authorization")String token);

    @GET("politics")
    Call<PoliticsRecordModel> getPolitics (@Header("Authorization")String token);

    @GET("children")
    Call<ChildrenRecordModel> getChildren (@Header("Authorization")String token);

    @GET("smoke")
    Call<SmokeRecordModel> getSmoke (@Header("Authorization")String token);

    @GET("drink")
    Call<DrinkRecordModel> getDrink (@Header("Authorization")String token);

    @GET("income")
    Call<SalaryRecordModel> getSalary (@Header("Authorization")String token);
}
