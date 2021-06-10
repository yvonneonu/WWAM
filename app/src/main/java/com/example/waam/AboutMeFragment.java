package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private EventDisplayAdapter eventDisplayAdapter;
    private List<EventResult> eventResults;

    public AboutMeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutMeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutMeFragment newInstance(String param1, String param2) {
        AboutMeFragment fragment = new AboutMeFragment();
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
        view = inflater.inflate(R.layout.fragment_about_me, container, false);
        recyclerView = view.findViewById(R.id.horizontalScrollView);
        UserDisplay();
        return view;
    }

    private void UserDisplay(){
       Call<EventRecordmodel> eventRecordmodelCall = ApiClient.getService().getEvent("Bearer "+token);
       eventRecordmodelCall.enqueue(new Callback<EventRecordmodel>() {
           @Override
           public void onResponse(Call<EventRecordmodel> call, Response<EventRecordmodel> response) {
               if (!response.isSuccessful()) {
                   String message = "No Event";
                   Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                   Log.d("event1", response.message());
                   Log.d("event", response.errorBody().toString());
                   return;
               }
               String log = response.body().toString();
               Log.d("take", log);
               EventRecordmodel eventRecordmodel = response.body();
               eventRecordmodel.getEvenRecord();

               // politics.setText(politicsRecordModel.getPoliticsModel().get(0).getName());

               Log.d("event12",new Gson().toJson(response.body()));

           }

           @Override
           public void onFailure(Call<EventRecordmodel> call, Throwable t) {

               Log.d("no event",t.getMessage());

           }
       });
    }
}