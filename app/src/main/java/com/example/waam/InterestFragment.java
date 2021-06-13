package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InterestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String token;
    private View view;

    private TextView textView, career, education, children, politics, bodyType, faith, ehnity;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InterestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InterestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InterestFragment newInstance(String param1, String param2) {
        InterestFragment fragment = new InterestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        token = SharedPref.getInstance(getActivity()).getStoredToken();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_interest, container, false);

        career = view.findViewById(R.id.textView75);
        education = view.findViewById(R.id.edu);
        children = view.findViewById(R.id.child);
        politics = view.findViewById(R.id.poli);
        bodyType = view.findViewById(R.id.bodytype);
        faith = view.findViewById(R.id.fauthe);
        ehnity = view.findViewById(R.id.ethnicity);



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
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
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

}