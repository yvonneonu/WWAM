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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
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
    private GeneralFactory generalFactory;
    private  Button button;
    private   ImageView videopic, aboutsefl, interests, friend;
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

        setHasOptionsMenu(true);
        generalFactory = GeneralFactory.getGeneralFactory(getActivity());
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.unfriend, menu);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.message:

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connected_friends, container, false);
        videopic = view.findViewById(R.id.videopic);
        aboutsefl = view.findViewById(R.id.aboutsef);
        ImageView profilePic = view.findViewById(R.id.imageView32);
        interests = view.findViewById(R.id.interest);
        friend = view.findViewById(R.id.friends);
        CardView cardView8 = view.findViewById(R.id.cardView8);
        CardView cardView7 = view.findViewById(R.id.cardView7);
        button = view.findViewById(R.id.button15);
        LinearLayout linlayout = view.findViewById(R.id.linear02);
        FrameLayout frameLayout = view.findViewById(R.id.frameLayout9);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Profile");
        cardView7.setOnClickListener(this);
        cardView8.setOnClickListener(this);
        videopic.setOnClickListener(this);
        aboutsefl.setOnClickListener(this);
        interests.setOnClickListener(this);
        friend.setOnClickListener(this);
        button.setOnClickListener(this);



        if(waamUser != null){
            frameLayout.setVisibility(View.VISIBLE);

            Glide.with(requireActivity())
                    .asBitmap()
                    .fitCenter()
                    .circleCrop()
                    .load(waamUser.getImageUrl())
                    .into(profilePic);


            String myId = FirebaseAuth.getInstance().getUid()+AllUsersActivity.FRIENDS;
            generalFactory.loadFriends(myId, new GeneralFactory.FetchFriends() {
                @Override
                public void friendsFetcher(List<WaamUser> friends) {
                    Log.d("SizeOfFriend",""+friends.size());
                    Log.d("IsFriend", ""+waamUser.getUid());
                    for(WaamUser user : friends){


                        Log.d("IAmInFor", ""+waamUser.getUid());
                        if(user.getUid().equals(waamUser.getUid())){
                            Log.d("IAmTrue", ""+waamUser.getUid());
                            linlayout.setVisibility(View.VISIBLE);
                            button.setVisibility(View.GONE);
                        }else{
                            Log.d("IAmFalse", ""+waamUser.getUid());
                            button.setVisibility(View.VISIBLE);
                            linlayout.setVisibility(View.GONE);
                        }
                    }
                }
            });
         /* generalFactory.friendChecker(waamUser.getUid(), new GeneralFactory.CheckFriend() {
              @Override
              public void checkIfFriend(boolean isFriend) {
                  Log.d("IsFriend", ""+isFriend);
                  if(isFriend){

                      linlayout.setVisibility(View.VISIBLE);
                      button.setVisibility(View.GONE);
                  }else{
                      button.setVisibility(View.VISIBLE);
                      linlayout.setVisibility(View.GONE);
                  }
              }
          });*/
        }else{
            frameLayout.setVisibility(View.GONE);
            String userId = FirebaseAuth.getInstance().getUid();
            GeneralFactory.getGeneralFactory(getActivity())
                    .loadSpecUser(userId, new GeneralFactory.SpecificUser() {
                        @Override
                        public void loadSpecUse(WaamUser user) {
                            Glide.with(requireActivity())
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
        final int message = R.id.cardView8;
        final int messageSec = R.id.cardView7;
        final int sendRequest = R.id.button15;
        switch (v.getId()) {
            case vid:
                if (v.getId() == videopic.getId()){

                    if(waamUser != null){
                        videopic.setColorFilter(Color.BLUE);
                        aboutsefl.setColorFilter(Color.TRANSPARENT);
                        interests.setColorFilter(Color.TRANSPARENT);
                        friend.setColorFilter(Color.TRANSPARENT);
                        Fragment fragment = new VideoPicFragment(waamUser);
                        getChildFragmentManager().beginTransaction()
                                .replace(profileFrame, fragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    }else {
                        // if (v.getId() == )
                        String userId = FirebaseAuth.getInstance().getUid();
                        //videopic.setColorFilter(Color.BLUE);
                        // aboutsefl.setColorFilter(Color.TRANSPARENT);
                        //interests.setColorFilter(Color.TRANSPARENT);
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
                }
                // i stopped here planning on sending waam user to the video fragment
                //fragment = VideoPicFragment.newInstance();


                break;
            case aboutsef:
                if (v.getId() == aboutsefl.getId()){

                    videopic.setColorFilter(Color.TRANSPARENT);
                    aboutsefl.setColorFilter(Color.BLUE);
                    interests.setColorFilter(Color.TRANSPARENT);
                    friend.setColorFilter(Color.TRANSPARENT);
                    if(waamUser != null){
                        Fragment fragmentone = AboutMeFragment.newInstance(waamUser);
                        getChildFragmentManager().beginTransaction()
                                .replace(profileFrame, fragmentone)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    }else{
                        String userId = FirebaseAuth.getInstance().getUid();
                       // aboutsefl.setColorFilter(Color.BLUE);
                        //aboutsefl.setColorFilter(Color.TRANSPARENT);

                       // videopic.setColorFilter(Color.TRANSPARENT);
                       // interests.setColorFilter(Color.TRANSPARENT);
                        GeneralFactory.getGeneralFactory(getActivity())
                                .loadSpecUser(userId, user -> {
                                    Fragment fragmentone = AboutMeFragment.newInstance(user);
                                    getChildFragmentManager().beginTransaction()
                                            .replace(profileFrame, fragmentone)
                                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                                });
                    }
                }


                break;
            case interest:
                Fragment fragmentwo = new InterestFragment();
                if (v.getId() == interests.getId()){
                    interests.setColorFilter(Color.BLUE);
                    aboutsefl.setColorFilter(Color.TRANSPARENT);
                    friend.setColorFilter(Color.TRANSPARENT);
                    videopic.setColorFilter(Color.TRANSPARENT);
                    getChildFragmentManager().beginTransaction()
                            .replace(profileFrame, fragmentwo)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                }

                break;
            case friends:

               if (v.getId() == friend.getId()){
                   friend.setColorFilter(Color.BLUE);
                   interests.setColorFilter(Color.TRANSPARENT);
                   aboutsefl.setColorFilter(Color.TRANSPARENT);
                   videopic.setColorFilter(Color.TRANSPARENT);
                   Fragment fragmenthree = FrendChannelFragment.newInstance(waamUser);
                   getChildFragmentManager().beginTransaction()
                           .replace(profileFrame, fragmenthree)
                           .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                           .commit();
               }

                break;

            case message:
            case messageSec:
                if(waamUser != null){
                    Intent intent = new Intent(getActivity(),ChatMessage.class);
                    intent.putExtra(ChatMessage.FRIENDS,waamUser);
                    startActivity(intent);
                }

                break;

            case sendRequest:

                if(waamUser != null){
                    GeneralFactory.getGeneralFactory(getActivity())
                            .sendFriendRequest(button, waamUser);

                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }


}