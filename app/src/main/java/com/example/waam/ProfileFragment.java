package com.example.waam;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.Random;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String PUT_PROFILE = "PutProfile";
    private Dialog dialog;
    private WaamUser user;
    private View view;
    private ImageView imageView;
    private TextView textView;
    private boolean[] boolcont;
    private FirebaseAuth mAuth;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(WaamUser waamUser) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(PUT_PROFILE, waamUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            user = (WaamUser) getArguments().getSerializable(PUT_PROFILE);
        }

        setHasOptionsMenu(true);
        boolcont = new boolean[]{true,false};

        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.friendrequestdialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animations ;
        Button button = dialog.findViewById(R.id.close);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    private void friendRequest(FriendResponseModel friendResponseModel){
        Call<FriendRequestModel> friendRequestModelCall = ApiClient.getService().getFriendRequest(friendResponseModel, "Bearer "+bigTokeng);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.profile_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int message = R.id.message;
        final int chatbu = R.id.bubble;
        switch (item.getItemId()){

            case message:
                Random rand = new Random();
                int n = rand.nextInt(2);
                if(boolcont[n]){
                    Log.d("Connected","Both of you are friends");
                    Fragment fragment = new ConnectedFriendsFragment();
                    FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentcontainer,fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();
                }else{
                    textView.setText("You must be friends with this user to \n access the chat feature");
                    dialog.show();
                }
                break;
            case chatbu:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        Button friendRequest = view.findViewById(R.id.button11);
        friendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        textView = dialog.findViewById(R.id.textView70);
        imageView = view.findViewById(R.id.imageView31);

        if(user != null){
            Glide.with(this)
                    .asBitmap()
                    .load(user.getImageUrl())
                    .into(imageView);
        }else{
            mAuth = FirebaseAuth.getInstance();
            String uid = mAuth.getUid();
            friendRequest.setVisibility(View.GONE);
            GeneralFactory.getGeneralFactory(getActivity()).loadSpecUser(uid, new GeneralFactory.SpecificUser() {
                @Override
                public void loadSpecUse(WaamUser userpro) {
                    if(isAdded()){
                        Glide.with(getActivity())
                                .asBitmap()
                                .load(userpro.getImageUrl())
                                .into(imageView);
                    }

                }
            });
        }



        friendRequest.setOnClickListener(v -> {
            textView.setText("You have sucessfully sent this user a \n friend request");
            dialog.show();
        });
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Profile");
        HorizontalScrollView horizontalScrollView = view.findViewById(R.id.horizontalScrollView);

        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        return  view;

    }
}