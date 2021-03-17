package com.example.waam;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchSpinnerValues {

    private static final String TAG = "CRASHED" ;
    private static  FetchSpinnerValues spinnerValues;


    private FetchSpinnerValues(){

    }


    public static FetchSpinnerValues getSpinnerValues(){
        if(spinnerValues == null){
            spinnerValues = new FetchSpinnerValues();
        }
        return spinnerValues;
    }

    public void fetchEducation(EducationListener educationListener,String token){
        //http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/education
        String baseUrl = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpinnerApi educationResult = retrofit.create(SpinnerApi.class);
        Call<RecordModel> allEdu = educationResult.getEducation("Bearer "+token);

        allEdu.enqueue(new Callback<RecordModel>() {
            @Override
            public void onResponse(Call<RecordModel> call, Response<RecordModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error","An error ocured");
                    return;
                }

                List<String> name = new ArrayList<>();
                List<EducationModel> userSchool = response.body().getModel();

                for(int i = 0 ; i < userSchool.size() ; i++){
                    name.add(userSchool.get(i).getName());
                    Log.d("Name",userSchool.get(i).getName());
                }
                Log.d("Success","Succesfully connected");
                List<EducationModel> results =response.body().getModel();
                if(educationListener != null){
                    educationListener.onEducationListener(name);
                }


            }

            @Override
            public void onFailure(Call<RecordModel> call, Throwable t) {
                Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });

        /*allEdu.enqueue(new Callback<List<RecordModel>>() {
            @Override
            public void onResponse(Call<List<RecordModel>> call, Response<List<String>> response) {
                if(!response.isSuccessful()){
                    Log.d("Error Code",""+response.code());
                    Log.d("Error",response.headers().toString());
                    return;
                }

                Log.d("Successful","Got to the server");
                if(educationListener != null){
                    educationListener.onEducationListener(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("Error Message",t.getMessage());
            }
        });*/
    }


    public interface EducationListener{
        void onEducationListener(List<String> userSchool);
    }



}
