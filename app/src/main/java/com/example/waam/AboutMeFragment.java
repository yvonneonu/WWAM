package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutMeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutMeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private View view;
    private String token;
    private TextView textView, career, education, children, politics, bodyType, faith, ehnity;
    public static final String PUT_PROFILE = "PutProfile";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private WaamUser user;
    private EventDisplayAdapter eventDisplayAdapter;
    private List<UserResult> userResults = new ArrayList<>();


    public AboutMeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment AboutMeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutMeFragment newInstance(WaamUser waamUser) {
        AboutMeFragment fragment = new AboutMeFragment();
        Bundle args = new Bundle();
        args.putSerializable(PUT_PROFILE, waamUser);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            user = (WaamUser) getArguments().getSerializable(PUT_PROFILE );
        }
        token = SharedPref.getInstance(getActivity()).getStoredToken();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_me, container, false);

        recyclerView = view.findViewById(R.id.horizontalScrollView);
        career = view.findViewById(R.id.textView75);
        education = view.findViewById(R.id.educ);
        children = view.findViewById(R.id.chil);
        politics = view.findViewById(R.id.poli);
        bodyType = view.findViewById(R.id.bodytype);
        faith = view.findViewById(R.id.fauthe);
        ehnity = view.findViewById(R.id.ethnicity);


        //eventDisplayAdapter = new EventDisplayAdapter(userResults, getActivity());
       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //recyclerView.setAdapter(eventDisplayAdapter);

       //recyclerView.setLayoutManager(linearLayoutManager);

        UserDisplay();
        occupationShow();
        educationShow();
        childrenShow();
        politicsShow();
        bodyShow();
        faithShow();
        ethnicity();


        return view;
    }

    private void ethnicity() {
        Call<EthnicityRecordmodel> ethnicityRecordmodelCall = ApiClient.getService().getEthnicity1("Bearer "+token);
        ethnicityRecordmodelCall.enqueue(new Callback<EthnicityRecordmodel>() {
            @Override
            public void onResponse(Call<EthnicityRecordmodel> call, Response<EthnicityRecordmodel> response) {

                if (!response.isSuccessful()){
                    String message = "No Display";
//check later                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    Log.d("display",response.message());
                    Log.d("display",response.errorBody().toString());
                    return;
                }

                String log = response.body().toString();
                Log.d("take", log);
                EthnicityRecordmodel ethnicityRecordmodel = response.body();
                ethnicityRecordmodel.getEtnicrecords();



//                ehnity.setText(ethnicityRecordmodel.getEtnicrecords().get(0).getName());



                Log.d("carer",new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<EthnicityRecordmodel> call, Throwable t) {

                Log.d("no career",t.getMessage());

            }
        });
    }

    private void faithShow() {
        Call<FaithRecordModel> faithRecordModelCall = ApiClient.getService().getFaith1("Bearer "+token);
        faithRecordModelCall.enqueue(new Callback<FaithRecordModel>() {
            @Override
            public void onResponse(Call<FaithRecordModel> call, Response<FaithRecordModel> response) {
                if (!response.isSuccessful()){
                    String message = "No Display";
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    Log.d("display",response.message());
                    Log.d("display",response.errorBody().toString());
                    return;
                }

                String log = response.body().toString();
                Log.d("take", log);
                FaithRecordModel faithRecordModel = response.body();
                faithRecordModel.getFaithRecords();


                //  faith.setText(faithRecordModel.getFaithRecords().get(0).getName());


                Log.d("carer",new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<FaithRecordModel> call, Throwable t) {

                Log.d("no career",t.getMessage());
            }
        });
    }

    private void bodyShow() {
        Call<BodyTypeRecordModel> bodyTypeRecordModelCall = ApiClient.getService().getBody1("Bearer "+token);
        bodyTypeRecordModelCall.enqueue(new Callback<BodyTypeRecordModel>() {
            @Override
            public void onResponse(Call<BodyTypeRecordModel> call, Response<BodyTypeRecordModel> response) {
                if (!response.isSuccessful()){
                    String message = "No Display";
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    Log.d("display",response.message());
                    Log.d("display",response.errorBody().toString());
                    return;
                }

                String log = response.body().toString();
                Log.d("take", log);
                BodyTypeRecordModel bodyTypeRecordModel = response.body();
                bodyTypeRecordModel.getBodyTypeRecord();

                // bodyType.setText(bodyTypeRecordModel.getBodyTypeRecord().get(0).getName());


                Log.d("carer",new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<BodyTypeRecordModel> call, Throwable t) {

                Log.d("no career",t.getMessage());
            }
        });
    }

    private void politicsShow() {
        Call<PoliticsRecordModel> politicsRecordModelCall = ApiClient.getService().getPolitics1("Bearer "+token);
        politicsRecordModelCall.enqueue(new Callback<PoliticsRecordModel>() {
            @Override
            public void onResponse(Call<PoliticsRecordModel> call, Response<PoliticsRecordModel> response) {
                if (!response.isSuccessful()){
                    String message = "No Display";
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    Log.d("display",response.message());
                    Log.d("display",response.errorBody().toString());
                    return;
                }

                String log = response.body().toString();
                Log.d("take", log);
                PoliticsRecordModel politicsRecordModel = response.body();
                politicsRecordModel.getPoliticsModel();

                // politics.setText(politicsRecordModel.getPoliticsModel().get(0).getName());

                Log.d("carer",new Gson().toJson(response.body()));

            }

            @Override
            public void onFailure(Call<PoliticsRecordModel> call, Throwable t) {

                Log.d("no career",t.getMessage());

            }
        });
    }

    private void childrenShow() {
        Call<ChildrenRecordModel> childrenRecordModelCall = ApiClient.getService().getChildren1("Bearer "+token);
        childrenRecordModelCall.enqueue(new Callback<ChildrenRecordModel>() {
            @Override
            public void onResponse(Call<ChildrenRecordModel> call, Response<ChildrenRecordModel> response) {
                if (!response.isSuccessful()){
                    String message = "No Display";
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    Log.d("display",response.message());
                    Log.d("display",response.errorBody().toString());
                    return;
                }

                String log = response.body().toString();
                Log.d("take", log);
                ChildrenRecordModel childrenRecordModel = response.body();

                childrenRecordModel.getChildrenRecords();

//                children.setText(childrenRecordModel.getChildrenRecords().get(0).getName());

                Log.d("carer",new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<ChildrenRecordModel> call, Throwable t) {

                Log.d("no career",t.getMessage());
            }
        });
    }

    private void educationShow() {
        Call<RecordModel> recordModelCall = ApiClient.getService().getEducation1("Bearer "+token);
        recordModelCall.enqueue(new Callback<RecordModel>() {
            @Override
            public void onResponse(Call<RecordModel> call, Response<RecordModel> response) {
                if (!response.isSuccessful()){
                    String message = "No Display";
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    Log.d("display",response.message());
                    Log.d("display",response.errorBody().toString());
                    return;
                }

                String log = response.body().toString();
                Log.d("take", log);

                RecordModel recordModel = response.body();
                recordModel.getModel();

                //education.setText(recordModel.getModel().get(0).getName());

                Log.d("carer",new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<RecordModel> call, Throwable t) {
                Log.d("no career",t.getMessage());

            }
        });

    }

    private void occupationShow() {
        Call<OcupationRecordModel> ocupationRecordModelCall = ApiClient.getService().getOccupation1("Bearer "+token);
        ocupationRecordModelCall.enqueue(new Callback<OcupationRecordModel>() {
            @Override
            public void onResponse(Call<OcupationRecordModel> call, Response<OcupationRecordModel> response) {
                if (!response.isSuccessful()){
                    String message = "No Display";
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    Log.d("display",response.message());
                    Log.d("display",response.errorBody().toString());
                    return;
                }

                String log = response.body().toString();
                Log.d("take", log);

                OcupationRecordModel ocupationRecordModel = response.body();
                ocupationRecordModel.getOccupationRecords();


                career.setText(ocupationRecordModel.getOccupationRecords().get(0).getName());

                Log.d("carer",new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<OcupationRecordModel> call, Throwable t) {

                Log.d("no career",t.getMessage());
            }
        });
    }

    private void UserDisplay(){
       Call<List<UserResult>> userRecordmodelCall = ApiClient.getService().getUserRecord("Bearer "+token);
       userRecordmodelCall.enqueue(new Callback<List<UserResult>>() {
           @Override
           public void onResponse(Call<List<UserResult>> call, Response<List<UserResult>> response) {
               if (!response.isSuccessful()) {
                   String message = "No Event";
                   Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                   Log.d("event1", response.message());
                   Log.d("event", response.errorBody().toString());
                   return;
               }
               String log = response.body().toString();
               Log.d("take", log);




               List<UserResult> userResults = response.body();

               eventDisplayAdapter = new EventDisplayAdapter(userResults, getActivity());
               recyclerView.setAdapter(eventDisplayAdapter);

               recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

               // politics.setText(politicsRecordModel.getPoliticsModel().get(0).getName());

               Log.d("event12",new Gson().toJson(response.body()));

           }

           @Override
           public void onFailure(Call<List<UserResult>> call, Throwable t) {

               Log.d("no event",t.getMessage());

           }
       });
    }
}