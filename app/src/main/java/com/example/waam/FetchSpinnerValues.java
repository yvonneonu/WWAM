package com.example.waam;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchSpinnerValues {

    private static  FetchSpinnerValues spinnerValues;


    private FetchSpinnerValues(){

    }


    public static FetchSpinnerValues getSpinnerValues(){
        if(spinnerValues == null){
            spinnerValues = new FetchSpinnerValues();
        }
        return spinnerValues;
    }


    public void fetchEducation(EducationListener educationListener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.google.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MysticApi educationResult = retrofit.create(MysticApi.class);
        Call<List<String>> allEdu = educationResult.getEducation();

        allEdu.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(!response.isSuccessful()){
                    Log.d("Error Code",""+response.code());
                    return;
                }

                if(educationListener != null){
                    educationListener.onEducationListener(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

                Log.d("Error Message",t.getMessage());
            }
        });
    }


    public interface EducationListener{
        void onEducationListener(List<String> userSchool);
    }



}
