package com.example.waam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DateIdeasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateIdeasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GeneralFactory generalFactory;
    private EventAdapter eventAdapter;
    private LinearLayout linearLayout;
    private RatingBar ratStarone, ratStartwo, ratStarthree, ratStarfour, ratStarFive,ratStarSix;
    private ImageView imageViewTrending;
    private List<EventModel> eventModels;

    public DateIdeasFragment() {
        // Required empty public constructor
    }

    public static DateIdeasFragment newInstance(String param1, String param2) {
        DateIdeasFragment fragment = new DateIdeasFragment();
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

        generalFactory = GeneralFactory.getGeneralFactory();
        eventModels = generalFactory.getEventModelList();
        eventAdapter = new EventAdapter(eventModels,getActivity());

        eventAdapter.setOnTouch(position -> Toast.makeText(getActivity(),"Mean face "+eventModels.get(position).getTitle(),Toast.LENGTH_SHORT).show());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_ideas, container, false);

        RecyclerView recyclerViewtwo = view.findViewById(R.id.cyclertwo);
        linearLayout = view.findViewById(R.id.linearLayout10);
        ratStarone = view.findViewById(R.id.ratStarone);
        ratStartwo = view.findViewById(R.id.startwo);
        ratStarthree = view.findViewById(R.id.starthree);
        ratStarfour = view.findViewById(R.id.starfour);
        ratStarFive = view.findViewById(R.id.starfive);
        ratStarSix = view.findViewById(R.id.starsix);
        imageViewTrending = view.findViewById(R.id.imageView29);
        ScrollView myScrollView = view.findViewById(R.id.scroll);
        HorizontalScrollView horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        myScrollView.setVerticalScrollBarEnabled(false);
        RatingBar[] ratingBars = new RatingBar[]{ratStarone,ratStartwo,ratStarthree,ratStarfour,ratStarFive,ratStarSix};

        for (RatingBar ratingBar : ratingBars) {
            ratingBar.setNumStars(1);
        }

        imageViewTrending.setOnClickListener(v -> moveToTrending());
        linearLayout.setOnClickListener(v -> moveToTrending());
        if(eventAdapter != null){
            recyclerViewtwo.setAdapter(eventAdapter);
            recyclerViewtwo.setLayoutManager(new GridLayoutManager(getActivity(),2));
        }
        return view;
    }

    private void moveToTrending(){
        Fragment fragment = new TrendingFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentcontainer,fragment);
        ft.commit();
    }
}