package com.example.waam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment implements View.OnClickListener {

    private Toolbar tool_bar;

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

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


       // ((AppCompatActivity) getActivity()).openDrawer();

        setHasOptionsMenu(true);
        Fragment fragment = new EventFragment();
        if(event != null){

        }

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.frcontainer,fragment);
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        event = view.findViewById(R.id.button8);
        dateIdeas = view.findViewById(R.id.button9);
        tool_bar = view.findViewById(R.id.tool_bar);

        //getaway = view.findViewById(R.id.button10);

        //options that make th
        event.setOnClickListener(this);
        dateIdeas.setOnClickListener(this);
      //  getaway.setOnClickListener(this);
        //event.setBackgroundColor(getActivity().getResources().getColor(R.color.purple_500));

        buttonsArrays = new Button[]{event,dateIdeas};
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Explore Vegas");
      //  Objects.requireNonNull(activity.getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        //.setTitle("Explore");

        return view;

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        tool_bar.inflateMenu(R.menu.exploremenu);

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.exploremenu, menu);


        tool_bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                final int Location = R.id.location;
                final int Search = R.id.search;
                switch (item.getItemId()){

                    case Location:
                        Fragment fragment = new LocationFragment();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragmentcontainer,fragment);
                        ft.commit();
                        break;
                    case Search:

                        Intent intent = new Intent(getActivity(), SearchExplore.class);
                        startActivity(intent);
                        Log.d("Search","Search is being clicked");
                        break;
                }

                return true;
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        final int Location = R.id.location;
        final int Search = R.id.search;
        switch (item.getItemId()){

            case Location:
                Fragment fragment = new LocationFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentcontainer,fragment);
                ft.commit();
                break;
            case Search:

                Intent intent = new Intent(getActivity(), SearchExplore.class);
                startActivity(intent);
                Log.d("Search","Search is being clicked");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        final int explore = R.id.button8;
        final int date = R.id.button9;

        Fragment fragment = null;

        switch (v.getId()){

            case explore:

                if (v.getId() == event.getId()){

                    event.setBackgroundResource(R.drawable.bordertext);
                    dateIdeas.setBackgroundColor(Color.TRANSPARENT);


                    fragment = new EventFragment();
                    getClicked(v);
                }

                break;


            case date:
                if (v.getId() == dateIdeas.getId()){

                    dateIdeas.setBackgroundResource(R.drawable.bordertext);
                    event.setBackgroundColor(Color.TRANSPARENT);

                    fragment = new DateIdeasFragment();
                    getClicked(v);
                }

                break;
           /* case R.id.button10:
                fragment = new GetaAwayFragment();
                getClicked(v);
                break;*/
        }

        if(fragment != null){
            Log.d("TAG","not null");
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.replace(R.id.frcontainer,fragment);
            ft.commit();
        }
    }


    private void getClicked(View view){

        for(int i = 0 ; i < buttonsArrays.length ; i++){
            if(view.getId() == buttonsArrays[i].getId()){
               buttonsArrays[i].setBackgroundColor(getActivity().getResources().getColor(R.color.orange));
            }else{
                buttonsArrays[i].setBackground(getActivity().getResources().getDrawable(R.drawable.button_border));
            }
        }
    }
}