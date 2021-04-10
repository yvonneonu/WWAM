package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button event, dateIdeas, getaway;

    private Button[] buttonsArrays;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
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

        Fragment fragment = new EventFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove( fragment);
       ft.replace(R.id.frcontainer, fragment);
       ft.attach(fragment);

        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        event = view.findViewById(R.id.button8);
        dateIdeas = view.findViewById(R.id.button9);
        getaway = view.findViewById(R.id.button10);

        //options that make th
        event.setOnClickListener(this);
        dateIdeas.setOnClickListener(this);
        getaway.setOnClickListener(this);


        buttonsArrays = new Button[]{event,dateIdeas,getaway};
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Explore");
        return view;

    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.button8:
                fragment = new EventFragment();
                getClicked(v);
                break;
            case R.id.button9:
                fragment = new DateIdeasFragment();
                getClicked(v);
                break;
            case R.id.button10:
                fragment = new GetaAwayFragment();
                getClicked(v);
                break;
        }

        if(fragment != null){
            Log.d("TAG","not null");
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frcontainer,fragment);
            ft.commit();
        }
    }


    private void getClicked(View view){

        for(int i = 0 ; i < buttonsArrays.length ; i++){
            if(view.getId() == buttonsArrays[i].getId()){
               buttonsArrays[i].setBackgroundColor(getActivity().getResources().getColor(R.color.purple_500));
            }else{
                buttonsArrays[i].setBackground(getActivity().getResources().getDrawable(R.drawable.button_border));
            }
        }
    }
}