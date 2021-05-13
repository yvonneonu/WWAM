package com.example.waam;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Location> locationConatiner;
    private LocationAdapter locationAdapter;
    BottomNavigationView bottomNavigationView;
    private GeneralFactory generalFactory;
    public LocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationFragment newInstance(String param1, String param2) {
        LocationFragment fragment = new LocationFragment();
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

        setHasOptionsMenu(true);
        generalFactory = GeneralFactory.getGeneralFactory(getActivity());
        locationConatiner = generalFactory.getLocationList();
        locationAdapter = new LocationAdapter(locationConatiner,getActivity());

        locationAdapter.onLocationListener(pos -> {
            Location location = locationConatiner.get(pos);
            Intent intent = new Intent(getActivity(),LocationDetailActivity.class);
            intent.putExtra("Eventdetail",location);
            startActivity(intent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.locrecycler);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Select destination");

        if(locationAdapter != null){
            recyclerView.setAdapter(locationAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
            //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.searchonly, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("Search","Something is going down");
        Toast.makeText(getActivity(),"Search is clicked ",Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}