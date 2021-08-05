package com.example.waam;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewProfile extends Fragment implements View.OnClickListener {
    private TextView textView;
    private ImageView imageView, aboutsefl, interes, frien;
    private BottomNavigationView bottomNavigationView;


    private boolean post;

    private WaamUser waamUser;
    private  String media = "";


    private ImageView test;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private FirebaseAuth mAuth;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ViewProfile(WaamUser waamUser) {
        this.waamUser = waamUser;
    }

    public ViewProfile() {
        // Required empty public constructor
    }

    //this constructor is to be used if the post is coming from TextDisplay.
    public ViewProfile(boolean post) {
        this.post = post;
    }


    public ViewProfile(String media){
        this.media = media;
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


        if (getActivity() != null) {
            bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
//            bottomNavigationView.setVisibility(View.GONE);
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
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);
        textView = view.findViewById(R.id.textView71);
        imageView = view.findViewById(R.id.imageView);
        test = view.findViewById(R.id.text);
        aboutsefl = view.findViewById(R.id.aboutsef);
        interes = view.findViewById(R.id.interest);
        frien = view.findViewById(R.id.friends);

        Fragment fragment;
        //If post is supplied you have to show dis fragment
        if (post) {
            fragment = new TextdisplayFragment();
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.containing, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }else if(media.equals(shareMedia.FROMMEDIA)){
            fragment = new MediaPostFragment();
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.containing, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
        else {
            fragment = new ViewProfileDefaultFragment();
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.containing, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }



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
        test.setOnClickListener(this);
        aboutsefl.setOnClickListener(this);
        interes.setOnClickListener(this);
        frien.setOnClickListener(this);
        setHasOptionsMenu(true);
        return view;

    }


    @Override
    public void onClick(View v) {
        final int tet = R.id.text;
        final int fram = R.id.fram1;
        final int aboutsef = R.id.aboutsef;
        final int inter = R.id.interest;
        final int friend = R.id.friends;



        switch (v.getId()){
            case tet:
                if (v.getId() == tet) {
                    test.setColorFilter(Color.BLUE);
                    aboutsefl.setColorFilter(Color.TRANSPARENT);
                    interes.setColorFilter(Color.TRANSPARENT);
                    frien.setColorFilter(Color.TRANSPARENT);

                    if (waamUser != null) {
                        Fragment fragment = new TextdisplayFragment();
                        //  constraintLayout.setVisibility(View.INVISIBLE);
                        getChildFragmentManager().beginTransaction()
                                .replace(fram, fragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    } else {


                        String userId = FirebaseAuth.getInstance().getUid();
                        GeneralFactory.getGeneralFactory(getActivity())
                                .loadSpecUser(userId, new GeneralFactory.SpecificUser() {
                                    @Override
                                    public void loadSpecUse(WaamUser user) {

                                        Fragment fragment = new TextdisplayFragment();
                                        //constraintLayout.setVisibility(View.GONE);

                                        getChildFragmentManager().beginTransaction()
                                                .replace(R.id.containing, fragment)
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                                .addToBackStack(null)

                                                .commit();

                                    }

                                });
                    }
                }


                break;
            case aboutsef:
                if (v.getId() == aboutsefl.getId()){
                    test.setColorFilter(Color.TRANSPARENT);
                    aboutsefl.setColorFilter(Color.BLUE);
                    interes.setColorFilter(Color.TRANSPARENT);
                    frien.setColorFilter(Color.TRANSPARENT);



                    if (waamUser != null) {
                        Fragment fragment = new SpinMatch();
                        //  constraintLayout.setVisibility(View.INVISIBLE);
                        getChildFragmentManager().beginTransaction()
                                .replace(fram, fragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    } else {

                        String userId = FirebaseAuth.getInstance().getUid();
                        GeneralFactory.getGeneralFactory(getActivity())
                                .loadSpecUser(userId, new GeneralFactory.SpecificUser() {
                                    @Override
                                    public void loadSpecUse(WaamUser user) {

                                        Fragment fragment = new SpinMatch();
                                        //constraintLayout.setVisibility(View.GONE);

                                        getChildFragmentManager().beginTransaction()
                                                .replace(R.id.containing, fragment)
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                                .addToBackStack(null)

                                                .commit();


                                    }

                                });
                    }
                }
                break;
            case inter:
                if (v.getId() == interes.getId()){
                    test.setColorFilter(Color.TRANSPARENT);
                    aboutsefl.setColorFilter(Color.TRANSPARENT);
                    interes.setColorFilter(Color.BLUE);
                    frien.setColorFilter(Color.TRANSPARENT);


                    if (waamUser != null) {
                        Fragment fragment = new LookingDetails();
                        //  constraintLayout.setVisibility(View.INVISIBLE);
                        getChildFragmentManager().beginTransaction()
                                .replace(fram, fragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    } else {

                        String userId = FirebaseAuth.getInstance().getUid();
                        GeneralFactory.getGeneralFactory(getActivity())
                                .loadSpecUser(userId, new GeneralFactory.SpecificUser() {
                                    @Override
                                    public void loadSpecUse(WaamUser user) {

                                        Fragment fragment = new LookingDetails();
                                        //constraintLayout.setVisibility(View.GONE);

                                        getChildFragmentManager().beginTransaction()
                                                .replace(R.id.containing, fragment)
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                                .addToBackStack(null)

                                                .commit();


                                    }

                                });
                    }
                }
                break;

            case friend:
                if (v.getId() == friend){
                    test.setColorFilter(Color.TRANSPARENT);
                    aboutsefl.setColorFilter(Color.TRANSPARENT);
                    interes.setColorFilter(Color.TRANSPARENT);
                    frien.setColorFilter(Color.BLUE);


                    if (waamUser != null){
                        Fragment fragment = new ViewAddFriend();
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

                                        Fragment fragment = new ViewAddFriend();
                                        //constraintLayout.setVisibility(View.GONE);

                                        getChildFragmentManager().beginTransaction()
                                                .replace(R.id.containing, fragment)
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                                .addToBackStack(null)

                                                .commit();


                                    }

                                });
                    }
                }
                break;
        }


    }


}