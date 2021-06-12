package com.example.waam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ConnectedFriendsFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private WaamUser waamUser;
    private static final String REQUEST = "connectedFriends";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConnectedFriendsFragment(WaamUser waamUser) {
        this.waamUser = waamUser;

    }

    public ConnectedFriendsFragment(){

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConnectedFriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if(waamUser != null){
            Fragment fr = new VideoPicFragment(waamUser);
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.add(R.id.profileframe, fr)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }else{
            String userId = FirebaseAuth.getInstance().getUid();
            GeneralFactory.getGeneralFactory(getActivity())
                    .loadSpecUser(userId, new GeneralFactory.SpecificUser() {
                        @Override
                        public void loadSpecUse(WaamUser user) {
                            Fragment fr = new VideoPicFragment(waamUser);
                            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                            ft.add(R.id.profileframe, fr)
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                    .commit();
                        }
                    });
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connected_friends, container, false);
        ImageView videopic = view.findViewById(R.id.videopic);
        ImageView aboutsef = view.findViewById(R.id.aboutsef);
        ImageView profilePic = view.findViewById(R.id.imageView32);
        ImageView interest = view.findViewById(R.id.interest);
        ImageView friends = view.findViewById(R.id.friends);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Profile");
        videopic.setOnClickListener(this);
        aboutsef.setOnClickListener(this);
        interest.setOnClickListener(this);
        friends.setOnClickListener(this);

        if(waamUser != null){
            Glide.with(Objects.requireNonNull(getActivity()))
                    .asBitmap()
                    .fitCenter()
                    .circleCrop()
                    .load(waamUser.getImageUrl())
                    .into(profilePic);
        }else{
            String userId = FirebaseAuth.getInstance().getUid();
            GeneralFactory.getGeneralFactory(getActivity())
                    .loadSpecUser(userId, new GeneralFactory.SpecificUser() {
                        @Override
                        public void loadSpecUse(WaamUser user) {
                            Glide.with(Objects.requireNonNull(getActivity()))
                                    .asBitmap()
                                    .fitCenter()
                                    .load(user.getImageUrl())
                                    .into(profilePic);

                        }
                    });
        }


        return view;
    }

    @Override
    public void onClick(View v) {
        final int vid = R.id.videopic;
        final int aboutsef = R.id.aboutsef;
        final int interest = R.id.interest;
        final int friends = R.id.friends;
        final int profileFrame = R.id.profileframe;
        switch (v.getId()) {
            case vid:
                // i stopped here planning on sending waam user to the video fragment
                //fragment = VideoPicFragment.newInstance();
                if(waamUser != null){
                    Fragment fragment = new VideoPicFragment(waamUser);
                    getChildFragmentManager().beginTransaction()
                            .replace(profileFrame, fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                }else {
                    String userId = FirebaseAuth.getInstance().getUid();
                    GeneralFactory.getGeneralFactory(getActivity())
                            .loadSpecUser(userId, new GeneralFactory.SpecificUser() {
                                @Override
                                public void loadSpecUse(WaamUser user) {
                                    Fragment fragment = new VideoPicFragment(waamUser);
                                    getChildFragmentManager().beginTransaction()
                                            .replace(profileFrame, fragment)
                                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                            .commit();
                                }
                            });
                }

                break;
            case aboutsef:

                if(waamUser != null){
                    Fragment fragmentone = AboutMeFragment.newInstance(waamUser);
                    getChildFragmentManager().beginTransaction()
                            .replace(profileFrame, fragmentone)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                }else{
                    String userId = FirebaseAuth.getInstance().getUid();
                    GeneralFactory.getGeneralFactory(getActivity())
                            .loadSpecUser(userId, new GeneralFactory.SpecificUser() {
                                @Override
                                public void loadSpecUse(WaamUser user) {
                                    Fragment fragmentone = AboutMeFragment.newInstance(user);
                                     getChildFragmentManager().beginTransaction()
                                            .replace(profileFrame, fragmentone)
                                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                            };

                });
                }
                break;
            case interest:
                Fragment fragmentwo = new InterestFragment();
                getChildFragmentManager().beginTransaction()
                        .replace(profileFrame, fragmentwo)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;
            case friends:
                Fragment fragmenthree = new FrendChannelFragment();
                getChildFragmentManager().beginTransaction()
                        .replace(profileFrame, fragmenthree)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }
}