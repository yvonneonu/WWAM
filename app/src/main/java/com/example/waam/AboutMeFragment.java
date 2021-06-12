package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        //eventDisplayAdapter = new EventDisplayAdapter(userResults, getActivity());
       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //recyclerView.setAdapter(eventDisplayAdapter);

       //recyclerView.setLayoutManager(linearLayoutManager);



        UserDisplay();
        return view;
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