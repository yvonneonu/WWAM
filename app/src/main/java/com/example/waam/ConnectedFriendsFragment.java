package com.example.waam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConnectedFriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConnectedFriendsFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConnectedFriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConnectedFriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConnectedFriendsFragment newInstance(String param1, String param2) {
        ConnectedFriendsFragment fragment = new ConnectedFriendsFragment();
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
        Fragment fr =new VideoPicFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.profileframe,fr)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connected_friends, container, false);
        ImageView videopic = view.findViewById(R.id.videopic);
        ImageView aboutsef = view.findViewById(R.id.aboutsef);
        ImageView interest = view.findViewById(R.id.interest);
        ImageView friends = view.findViewById(R.id.friends);

        videopic.setOnClickListener(this);
        aboutsef.setOnClickListener(this);
        interest.setOnClickListener(this);
        friends.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        final int vid = R.id.videopic;
        final  int aboutsef = R.id.aboutsef;
        final int interest = R.id.interest;
        final int friends = R.id.friends;
        final int profileFrame = R.id.profileframe;
        Fragment fragment ;
        switch (v.getId()){
            case vid:
                fragment = new VideoPicFragment();
                break;
            case aboutsef:
                fragment = new AboutMeFragment();
                break;
            case interest:
                fragment = new InterestFragment();
                Log.d("Interest","Showing off");
                break;
            case friends:
                fragment = new FrendChannelFragment();
                Log.d("Friends","Showing off");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        if(fragment != null){
            getChildFragmentManager().beginTransaction()
                    .replace(profileFrame,fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }
}