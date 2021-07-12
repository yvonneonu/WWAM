package com.example.waam;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waam.utils.ViewEventAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewProfileDefaultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewProfileDefaultFragment extends Fragment {
    private ImageView imageView;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private ViewProfileAdapter viewProfileAdapter;
    private List<Location> locationView = new ArrayList<>();

    private boolean post;
    private List<Location> eventView = new ArrayList<>();
    private ViewEventAdapter viewEventAdapter;
    private RecyclerView recyclerView1;

    private RecyclerView recyclerView2;
    //private RecyclerView recyclerView31;
    private List<Location> dateIdea = new ArrayList<>();
    private ViewEventAdapter dateIdeaAdapter;

    private WaamUser waamUser;
    private ConstraintLayout constraintLayout;



    private   ImageView test;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewProfileDefaultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewProfileDefaultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewProfileDefaultFragment newInstance(String param1, String param2) {
        ViewProfileDefaultFragment fragment = new ViewProfileDefaultFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_view_profile_default, container, false);
        constraintLayout = view.findViewById(R.id.constraintLayout1);


        //textView1 = view.findViewById(R.id.arrow_matches);
        imageView = view.findViewById(R.id.imageView);
        recyclerView = view.findViewById(R.id.recyclerView3);
        recyclerView1 = view.findViewById(R.id.recyclerView7);
        recyclerView2 = view.findViewById(R.id.recyclerView9);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);


        matchDesign();
        eventDesign();
        dateIdeaDisplay();


        viewProfileAdapter = new ViewProfileAdapter(locationView, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(viewProfileAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        //  textView1 = view.findViewById(R.id.textrt);


        viewEventAdapter = new ViewEventAdapter(eventView, getActivity());
        recyclerView1.setAdapter(viewEventAdapter);
        recyclerView1.setLayoutManager(linearLayoutManager1);

        /*recyclerView31 = view.findViewById(R.id.recyclerView31);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getActivity(),2);
        recyclerView31.setAdapter(postAdapter);
        recyclerView31.setLayoutManager(gridLayoutManager);*/




        dateIdeaAdapter = new ViewEventAdapter(dateIdea, getActivity());
        recyclerView2.setAdapter(dateIdeaAdapter);
        recyclerView2.setLayoutManager(linearLayoutManager2);



//        toolbar.setTitle("Dashboard");

        return view;
    }


    private void matchDesign() {
        int[] display = {
                R.drawable.topnav_profile,
                R.drawable.top_scroll_profile_img,
                R.drawable.profile_img_user,
                R.drawable.group_img_2,
                R.drawable.topnav_profile,


        };



        String[] rate2 = {"Ada, 25", "Kemi, 19", "Adora, 20", "Caio, 25", "Aish, 35",

        };

        for (int i = 0; i < display.length; i++) {

            locationView.add(new Location(display[i], rate2[i]));
        }
    }
    private void eventDesign() {
        int[] display = {



                R.drawable.diningout,
                R.drawable.coffeeconversation,
                R.drawable.travel,
                R.drawable.winetasting,
                R.drawable.nightclubsdancing,
        };



        String[] rate2 = {"Micheal Jackson", "Criss Angel", "One By Circle", "Freak at Planet", "By Circle",

        };

        for (int i = 0; i < display.length; i++) {

            eventView.add(new Location(display[i], rate2[i]));
        }
    }

    private void dateIdeaDisplay() {
        int[] display = {



                R.drawable.diningout,
                R.drawable.coffeeconversation,
                R.drawable.travel,
                R.drawable.winetasting,
                R.drawable.nightclubsdancing,
        };



        String[] rate2 = {"Secre Food", "SkyJump at Las", "Las Vegas", "Stratosphere Tower", "By Circle",

        };

        for (int i = 0; i < display.length; i++) {

            dateIdea.add(new Location(display[i], rate2[i]));
        }
    }

}