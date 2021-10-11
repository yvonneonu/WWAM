package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
    private TextView textView, career, education, children, politics, bodyType, faith, ehnity, littleMoredetails, ready;
    public static final String PUT_PROFILE = "PutProfile";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private WaamUser user;
    private EventDisplayAdapter eventDisplayAdapter;
    private NoeventAdapter adapter;
    private List<UserResult> userResults = new ArrayList<>();


    private String example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nibh cras pulvinar mattis nunc. Ut tristique et egestas quis ipsum suspendisse ultrices gravida. Fames ac turpis egestas integer eget. Aliquet eget sit amet tellus. Vitae et leo duis ut diam quam nulla porttitor. Nunc pulvinar sapien et ligula ullamcorper malesuada. Vestibulum rhoncus est pellentesque elit ullamcorper dignissim cras. A diam maecenas sed enim. Augue mauris augue neque gravida in fermentum et sollicitudin. Rhoncus urna neque viverra justo nec ultrices dui sapien. Amet facilisis magna etiam tempor orci eu. Pellentesque elit eget gravida cum.";


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
        littleMoredetails = view.findViewById(R.id.textView73);
        ready = view.findViewById(R.id.read);



        if(countWords(example) > 50){
            String words = getNwords(example,50);
            littleMoredetails.setText(words);
            ready.setVisibility(View.VISIBLE);
        }else{
            ready.setVisibility(View.GONE);
        }

        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                littleMoredetails.setText(example);
                ready.setVisibility(View.GONE);
            }
        });
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

    public String getNwords(String myString, int number) {
        String [] arr = myString.split("\\s+");
        //Splits words & assign to the arr[]  ex : arr[0] -> Copying ,arr[1] -> first


        ; // NUMBER OF WORDS THAT YOU NEED
        String nWords="";

        // concatenating number of words that you required
        for(int i=0; i<number ; i++){
            nWords = nWords + " " + arr[i] ;
        }

        return nWords;
    }

    public static int countWords(String s) {
        int wordCount = 0;

        boolean word = false;
        int endOfLine = s.length() - 1;

        for (int i = 0; i < s.length(); i++) {
            // if the char is a letter, word = true.
            if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                word = true;
                // if char isn't a letter and there have been letters before,
                // counter goes up.
            } else if (!Character.isLetter(s.charAt(i)) && word) {
                wordCount++;
                word = false;
                // last word of String; if it doesn't end with a non letter, it
                // wouldn't count without this.
            } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        return wordCount;
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

                if (user.getUid().equals(response.body().getOccupationRecords().get(0).getId())){

                    OcupationRecordModel ocupationRecordModel = response.body();
                    ocupationRecordModel.getOccupationRecords();


                    career.setText(ocupationRecordModel.getOccupationRecords().get(0).getName());

                    Log.d("carer",new Gson().toJson(response.body()));
                }else {
                    Log.d("event12", ""+ log);
                }

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
               String loggg = response.body().get(0).getId();
               Log.d("take", loggg);




                   List<UserResult> userResults = response.body();

                   if (userResults.isEmpty()){
                       adapter = new NoeventAdapter(getActivity());
                       recyclerView.setAdapter(adapter);

                       recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

                   }else {
                       eventDisplayAdapter = new EventDisplayAdapter(userResults, getActivity());
                       recyclerView.setAdapter(eventDisplayAdapter);

                       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

                       // politics.setText(politicsRecordModel.getPoliticsModel().get(0).getName());

                       Log.d("event12",new Gson().toJson(response.body()));
                   }





           }

           @Override
           public void onFailure(Call<List<UserResult>> call, Throwable t) {

               Log.d("no event",t.getMessage());

           }
       });
    }
}