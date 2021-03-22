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
        Call<BodyTypeRecordModel> allBody = bodyResult.getBody("Bearer "+token);
        allBody.enqueue(new Callback<BodyTypeRecordModel>() {
            @Override
            public void onResponse(Call<BodyTypeRecordModel> call, Response<BodyTypeRecordModel> response) {
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
            public void onFailure(Call<BodyTypeRecordModel> call, Throwable t) {

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

    public void fetchFaith(FaithListener faithListener, String token) {
        //http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/education
        String baseUrl = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MysticApi faithResult = retrofit.create(MysticApi.class);
        Call<FaithRecordModel> allFaith = faithResult.getFaith("Bearer " + token);

        allFaith.enqueue(new Callback<FaithRecordModel>() {
            @Override
            public void onResponse(Call<FaithRecordModel> call, Response<FaithRecordModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error", "An error ocured");
                    return;
                }

                List<String> faithName = new ArrayList<>();
                List<FaithResult> userFaith = response.body().getFaithRecords();

                for (int i = 0; i < userFaith.size(); i++) {
                    faithName.add(userFaith.get(i).getName());
                    Log.d("Name", userFaith.get(i).getName());
                }
                Log.d("Success", "Succesfully connected");
                List<FaithResult> faithResults = response.body().getFaithRecords();
                if (faithListener != null) {
                    faithListener.onFaithListener(faithName);
                }

            }
            @Override
            public void onFailure(Call<FaithRecordModel> call, Throwable t) {
                Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });

    }

    public interface FaithListener{
        void onFaithListener(List<String> userFaith);
    }

    public void fetchPolitics(PoliticsListener politicsListener, String token) {
        //http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/education
        String baseUrl = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MysticApi politicsResult = retrofit.create(MysticApi.class);
        Call<PoliticsRecordModel> allPoli = politicsResult.getPolitics("Bearer " + token);

        allPoli.enqueue(new Callback<PoliticsRecordModel>() {
            @Override
            public void onResponse(Call<PoliticsRecordModel> call, Response<PoliticsRecordModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error", "An error ocured");
                    return;
                }

                List<String> namePolitics = new ArrayList<>();
                List<PoliticsResult> userPolitics = response.body().getPoliticsModel();

                for (int i = 0; i < userPolitics.size(); i++) {
                    namePolitics.add(userPolitics.get(i).getName());
                    Log.d("Name", userPolitics.get(i).getName());
                }
                Log.d("Success", "Succesfully connected");
                List<PoliticsResult> resultsPolitics = response.body().getPoliticsModel();
                if (politicsListener != null) {
                    politicsListener.onPoliticsListener(namePolitics);
                }

            }
            @Override
            public void onFailure(Call<PoliticsRecordModel> call, Throwable t) {
                Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });

    }

    public interface PoliticsListener{
        void onPoliticsListener(List<String> userPolitics);
    }

    public void fetchChildren(ChildrenListener childrenListener, String token) {
        //http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/education
        String baseUrl = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MysticApi childrenResult = retrofit.create(MysticApi.class);
        Call<ChildrenRecordModel> allChil = childrenResult.getChildren("Bearer " + token);

        allChil.enqueue(new Callback<ChildrenRecordModel>() {
            @Override
            public void onResponse(Call<ChildrenRecordModel> call, Response<ChildrenRecordModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error", "An error ocured");
                    return;
                }

                List<String> nameChildren = new ArrayList<>();
                List<ChildrenResult> userChildren = response.body().getChildrenRecords();

                for (int i = 0; i < userChildren.size(); i++) {
                    nameChildren.add(userChildren.get(i).getName());
                    Log.d("Name", userChildren.get(i).getName());
                }
                Log.d("Success", "Succesfully connected");
                List<ChildrenResult> resultschildren = response.body().getChildrenRecords();
                if (childrenListener != null) {
                    childrenListener.onChildrenListerner(nameChildren);
                }
            }

            @Override
            public void onFailure(Call<ChildrenRecordModel> call, Throwable t) {
                Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });
    }
    public interface ChildrenListener{
        void onChildrenListerner(List<String> userChildren);
    }

    public void fetchSmoke(SmokeListener smokeListener, String token) {
        //http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/education
        String baseUrl = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MysticApi smokeResult = retrofit.create(MysticApi.class);
        Call<SmokeRecordModel> allSmok = smokeResult.getSmoke("Bearer " + token);

        allSmok.enqueue(new Callback<SmokeRecordModel>() {
            @Override
            public void onResponse(Call<SmokeRecordModel> call, Response<SmokeRecordModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error", "An error ocured");
                    return;
                }

                List<String> nameSmoke = new ArrayList<>();
                List<SmokeResult> userSmoke = response.body().getSmokeRecord();

                for (int i = 0; i < userSmoke.size(); i++) {
                    nameSmoke.add(userSmoke.get(i).getName());
                    Log.d("Name", userSmoke.get(i).getName());
                }
                Log.d("Success", "Succesfully connected");
                List<SmokeResult> resultsSmoke = response.body().getSmokeRecord();
                if (smokeListener != null) {
                    smokeListener.onSmokeListerner(nameSmoke);
                }
            }

            @Override
            public void onFailure(Call<SmokeRecordModel> call, Throwable t) {
                Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });
    }



    public interface SmokeListener{
        void onSmokeListerner(List<String> userSmoke);
    }


    public void fetchDrink(DrinkListener drinkListener, String token) {
        //http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/education
        String baseUrl = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MysticApi drinkResult = retrofit.create(MysticApi.class);
        Call<DrinkRecordModel> allDrink = drinkResult.getDrink("Bearer " + token);

        allDrink.enqueue(new Callback<DrinkRecordModel>() {
            @Override
            public void onResponse(Call<DrinkRecordModel> call, Response<DrinkRecordModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error", "An error ocured");
                    return;
                }

                List<String> nameDrink = new ArrayList<>();
                List<DrinkResult> userDrink = response.body().getDrinkRecords();

                for (int i = 0; i < userDrink.size(); i++) {
                    nameDrink.add(userDrink.get(i).getName());
                    Log.d("Name", userDrink.get(i).getName());
                }
                Log.d("Success", "Succesfully connected");
                List<DrinkResult> resultsDrink = response.body().getDrinkRecords();
                if (drinkListener != null) {
                    drinkListener.onDrinkListerner(nameDrink);
                }
            }

            @Override
            public void onFailure(Call<DrinkRecordModel> call, Throwable t) {
                Log.d(TAG, "Something is wrong " + t.getMessage());
            }
        });
    }

    public interface DrinkListener{
        void onDrinkListerner(List<String> userDrink);
    }
}
