package com.example.waam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waam.utils.ViewEventAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewProfile extends Fragment implements View.OnClickListener {
    private TextView textView, textView1;
    private ImageView imageView;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private ViewProfileAdapter viewProfileAdapter;
    private List<Location> locationView = new ArrayList<>();

    private List<Location> eventView = new ArrayList<>();
    private ViewEventAdapter viewEventAdapter;
    private RecyclerView recyclerView1;

    private RecyclerView recyclerView2;
    private List<Location> dateIdea = new ArrayList<>();
    private ViewEventAdapter dateIdeaAdapter;

    private WaamUser waamUser;
    private ConstraintLayout constraintLayout;
    private GeneralFactory generalFactory;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private FirebaseAuth mAuth;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ViewProfile(WaamUser waamUser){
        this.waamUser = waamUser;
    }

    public ViewProfile() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewProfile newInstance(String param1, String param2) {
        ViewProfile fragment = new ViewProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
        mAuth = FirebaseAuth.getInstance();
        if(getActivity() != null){
            bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setVisibility(View.GONE);
        }



    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        menu.clear();
        inflater.inflate(R.menu.viewprofile, menu);
        super.onCreateOptionsMenu(menu, inflater);
       // MenuItem item = menu.findItem(R.id.message);
        //item.setIcon(R.drawable.lowernav_friends_icon);
      //super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_view_profile, container, false);


        matchDesign();
        eventDesign();
        dateIdeaDisplay();

        ImageView test = view.findViewById(R.id.text);




        constraintLayout = view.findViewById(R.id.constraintLayout1);

        textView = view.findViewById(R.id.textView71);
        textView1 = view.findViewById(R.id.arrow_matches);
        imageView = view.findViewById(R.id.imageView);

        recyclerView = view.findViewById(R.id.recyclerView3);
        viewProfileAdapter = new ViewProfileAdapter(locationView, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(viewProfileAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
      //  textView1 = view.findViewById(R.id.textrt);

        recyclerView1 = view.findViewById(R.id.recyclerView7);
        viewEventAdapter = new ViewEventAdapter(eventView, getActivity());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setAdapter(viewEventAdapter);
        recyclerView1.setLayoutManager(linearLayoutManager1);

        recyclerView2 = view.findViewById(R.id.recyclerView9);
        dateIdeaAdapter = new ViewEventAdapter(dateIdea, getActivity());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setAdapter(dateIdeaAdapter);
        recyclerView2.setLayoutManager(linearLayoutManager2);



//        toolbar.setTitle("Dashboard");

        String uid = FirebaseAuth.getInstance().getUid();

        GeneralFactory.getGeneralFactory(getContext()).loadSpecUser(uid, new GeneralFactory.SpecificUser() {
            @Override
            public void loadSpecUse(WaamUser user) {
                Glide.with(getContext())
                        .asBitmap()
                        .fitCenter()
                        .circleCrop()
                        .load(user.getImageUrl())
                        .into(imageView);

                textView.setText(user.getFullname());
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 4; i++){
                   // locationView.add(new Location())
                }
            }
        });
        test.setOnClickListener(this);

       // assert activity != null;

        setHasOptionsMenu(true);
//        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Profile");
        return  view;

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


    @Override
    public void onClick(View v) {
        final int tet = R.id.text;
        final int fram = R.id.fram1;

        switch (v.getId()){
            case tet:
                if (waamUser != null){
                    Fragment fragment = new TextdisplayFragment();
                  //  constraintLayout.setVisibility(View.INVISIBLE);
                    getChildFragmentManager().beginTransaction()
                            .replace(fram, fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                }else {
                    String userId = FirebaseAuth.getInstance().getUid();
                    GeneralFactory.getGeneralFactory(getActivity())
                            .loadSpecUser(userId, new GeneralFactory.SpecificUser() {
                                @Override
                                public void loadSpecUse(WaamUser user) {

                                    Fragment fragment = new TextdisplayFragment();
                                    constraintLayout.setVisibility(View.GONE);

                                    getChildFragmentManager().beginTransaction()
                                            .replace(fram, fragment)
                                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                            .addToBackStack(null)
                                            .commit();
                                }
                            });
                }



                //FragmentManager.OnBackStackChangedListener
                break;


        }

       // getActivity().onBackPressed();

        //super.on
       // getFragmentManager().popBackStackImmediate();


      //  getActivity().finish();
    }




}