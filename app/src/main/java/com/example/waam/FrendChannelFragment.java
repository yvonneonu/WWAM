package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrendChannelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrendChannelFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FriendAdapt friendAdapt;
    private List<WaamUser> friendModelList;
    private WaamUser waamUser;
    private TextView textView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;

    public FrendChannelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FrendChannelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FrendChannelFragment newInstance(WaamUser user) {
        FrendChannelFragment fragment = new FrendChannelFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,user);
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
        waamUser = (WaamUser) getArguments().getSerializable(ARG_PARAM1);
        GeneralFactory generalFactory = GeneralFactory.getGeneralFactory(getActivity());
        String branchName = FirebaseAuth.getInstance().getUid()+AllUsersActivity.FRIENDS;


        if(waamUser != null){
            String friendbranchName = waamUser.getUid()+AllUsersActivity.FRIENDS;

            generalFactory.loadFriends(friendbranchName, friends -> {
                progressBar.setVisibility(View.GONE);
                friendModelList = friends;
                friendAdapt = new FriendAdapt(friendModelList,getActivity());
                if(friends.size() < 1){

                    textView.setVisibility(View.VISIBLE);

//                    textView1.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    //Friends List is empty
                }else{
                    recyclerView.setVisibility(View.VISIBLE);

//                    textView1.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);

                    linearLayout.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                    recyclerView.setAdapter(friendAdapt);
                }


                //This might crash as we dont understand what is going on.
                friendAdapt.friendMover(position -> {
                    WaamUser user = friendModelList.get(position);
                    Fragment fragment = new ConnectedFriendsFragment(user);
                    if(getActivity() != null){
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragmentcontainer, fragment);
                        ft.commit();
                    }
                    //Intent intent = new Intent(getActivity(),ChatMessage.class);
                    //intent.putExtra(ChatMessage.FRIENDS,user);
                    //startActivity(intent);

                });
            });

        }else{
            generalFactory.loadFriends(branchName, friends -> {
                recyclerView.setVisibility(View.VISIBLE);
                friendModelList = friends;
                progressBar.setVisibility(View.GONE);
                friendAdapt = new FriendAdapt(friendModelList,getActivity());

                if(friends.size() < 1){

//                    textView1.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);

                    linearLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }else{

                    recyclerView.setVisibility(View.VISIBLE);

                    textView.setVisibility(View.GONE);
//                    textView1.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                    recyclerView.setAdapter(friendAdapt);
                }


                //This might crash as we dont understand what is going on.
                friendAdapt.friendMover(position -> {
                    WaamUser user = friendModelList.get(position);
                    Fragment fragment = new ConnectedFriendsFragment(user);
                    if(getActivity() != null){
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragmentcontainer, fragment);
                        ft.commit();
                    }
                    //Intent intent = new Intent(getActivity(),ChatMessage.class);
                    //intent.putExtra(ChatMessage.FRIENDS,user);
                    //startActivity(intent);

                });


            });
        }
        Log.d("FrendsFragment","fragie");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frend_channel, container, false);
        recyclerView = view.findViewById(R.id.recy);
        progressBar = view.findViewById(R.id.progressBar2);
        textView = view.findViewById(R.id.textView107);
//        textView1 = view.findViewById(R.id.textView10);
        linearLayout = view.findViewById(R.id.images);
        /*recyclerView = view.findViewById(R.id.friends_recycler);
        progressBar = view.findViewById(R.id.progressBaring);
        ImageView imageView = view.findViewById(R.id.imageView40);
        imageView.setVisibility(View.GONE);

        GeneralFactory generalFactory = GeneralFactory.getGeneralFactory(getActivity());
        String branchName = FirebaseAuth.getInstance().getUid()+AllUsersActivity.FRIENDS;
        Log.d("FriendsFrag","fragie");
        generalFactory.loadFriends(branchName, friends -> {
            recyclerView.setVisibility(View.VISIBLE);
            friendModelList = friends;
            progressBar.setVisibility(View.GONE);
            friendAdapt = new FriendAdapt(friendModelList,getActivity());
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
            //recyclerView.setAdapter(friendAdapt);
            friendAdapt.friendMover(position -> {
                WaamUser user = friendModelList.get(position);
                Intent intent = new Intent(getActivity(),ChatMessage.class);
                intent.putExtra(ChatMessage.FRIENDS,user);
                startActivity(intent);

            });
        });*/
        return view;
    }
}