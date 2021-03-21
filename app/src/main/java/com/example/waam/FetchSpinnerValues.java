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

    private static final String TAG = "CRASHED";
    private static FetchSpinnerValues spinnerValues;


    private FetchSpinnerValues() {

    }


    public static FetchSpinnerValues getSpinnerValues() {
        if (spinnerValues == null) {
            spinnerValues = new FetchSpinnerValues();
        }
        return spinnerValues;
    }

    public void fetchEducation(EducationListener educationListener, String token) {
        //http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/education
        String baseUrl = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MysticApi educationResult = retrofit.create(MysticApi.class);
        Call<RecordModel> allEdu = educationResult.getEducation("Bearer " + token);

        allEdu.enqueue(new Callback<RecordModel>() {
            @Override
            public void onResponse(Call<RecordModel> call, Response<RecordModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error", "An error ocured");
                    return;
                }

                List<String> name = new ArrayList<>();
                List<EduactionRsesult> userSchool = response.body().getModel();

                for (int i = 0; i < userSchool.size(); i++) {
                    name.add(userSchool.get(i).getName());
                    Log.d("Name", userSchool.get(i).getName());
                }
                Log.d("Success", "Succesfully connected");
                List<EduactionRsesult> results = response.body().getModel();
                if (educationListener != null) {
                    educationListener.onEducationListener(name);
                }

            }
            @Override
            public void onFailure(Call<RecordModel> call, Throwable t) {
                Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });

    }

    public interface EducationListener {
        void onEducationListener(List<String> userSchool);
    }

    public void fetchOccupation(OccupationListener occupationListener, String token){
        String baseUrl = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MysticApi occupationResult = retrofit.create(MysticApi.class);
        Call<OcupationRecordModel> allOccu = occupationResult.getOccupation("Bearer "+token);
        allOccu.enqueue(new Callback<OcupationRecordModel>() {
            @Override
            public void onResponse(Call<OcupationRecordModel> call, Response<OcupationRecordModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error", "An error ocured");
                    return;
                }
                List<String> school = new ArrayList<>();
                List<OcupationResult> userOccupation = response.body().getOccupationRecords();
                for (int i = 0 ; i < userOccupation.size() ; i++) {

                    school.add(userOccupation.get(i).getName());
                    Log.d("Name",userOccupation.get(i).getName());

                }

                    Log.d("Success","Succesfully connected");
                    List<OcupationResult> resultList = response.body().getOccupationRecords();
                    if (occupationListener != null) {
                        occupationListener.onOccupationListener(school);
                    }
            }

            @Override
            public void onFailure(Call<OcupationRecordModel> call, Throwable t) {

                    Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });
    }


    public interface OccupationListener {
        void onOccupationListener(List<String> userSchool);
        }
    public void fetchBody(BodyTypeListener bodyTypeListener, String token){
        String baseUrl = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MysticApi bodyResult = retrofit.create(MysticApi.class);
        Call<BodyTypeResultRecordModel> allBody = bodyResult.getBody("Bearer "+token);
        allBody.enqueue(new Callback<BodyTypeResultRecordModel>() {
            @Override
            public void onResponse(Call<BodyTypeResultRecordModel> call, Response<BodyTypeResultRecordModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error", "An error ocured");
                    return;
                }
                List<String> body = new ArrayList<>();
                List<BodyTypeResult> userBody = response.body().getBodyTypeRecord();
                for (int i = 0 ; i < userBody.size() ; i++) {

                    body.add(userBody.get(i).getName());
                    Log.d("Name",userBody.get(i).getName());

                }

                Log.d("Success","Succesfully connected");
                List<BodyTypeResult> bodyTypeResultsList = response.body().getBodyTypeRecord();
                if (bodyTypeListener != null) {
                    bodyTypeListener.onBodyTypeListener(body);
                }
            }

            @Override
            public void onFailure(Call<BodyTypeResultRecordModel> call, Throwable t) {

                Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });
    }
    public interface BodyTypeListener {
        void onBodyTypeListener(List<String> userBody);
    }

    public void fetchEthnicity(EthnicityListener ethnicityListener, String token){
        //http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/education
        String baseUrl = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MysticApi ethnicityResult = retrofit.create(MysticApi.class);
        Call<EthnicityRecordmodel> allEth = ethnicityResult.getEthnicity("Bearer " + token);
        allEth.enqueue(new Callback<EthnicityRecordmodel>() {
            @Override
            public void onResponse(Call<EthnicityRecordmodel> call, Response<EthnicityRecordmodel> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error", "An error ocured");
                    return;
                }

                List<String> ethnicalname = new ArrayList<>();
                List<EthnicityResult> userEthnicity = response.body().getEtnicrecords();

                for (int i = 0; i < userEthnicity.size(); i++) {
                    ethnicalname.add(userEthnicity.get(i).getName());
                    Log.d("Name", userEthnicity.get(i).getName());
                }
                Log.d("Success", "Succesfully connected");
                List<EthnicityResult> results = response.body().getEtnicrecords();
                if (ethnicityListener != null) {
                    ethnicityListener.onEthnicityListener(ethnicalname);
                }
            }

            @Override
            public void onFailure(Call<EthnicityRecordmodel> call, Throwable t) {

                Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });
    }


    public interface EthnicityListener {
        void onEthnicityListener(List<String> userEthnicity);
    }
}
