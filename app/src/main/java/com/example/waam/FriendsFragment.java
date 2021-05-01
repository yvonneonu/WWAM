package com.example.waam;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.chat.ConnectycubeRoster;
import com.connectycube.chat.listeners.SubscriptionListener;
import com.connectycube.users.model.ConnectycubeUser;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ConnectycubeUser connectycubeUser;
    private FriendAdapt friendAdapt;
    private List<FriendModel> friendModelList;
    private GeneralFactory generalFactory;
    private int userId = 0;
    private ConnectycubeRoster chatRoster;
    private final int userID = 4134562;
    public FriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendsFragment newInstance(String param1, String param2) {
        FriendsFragment fragment = new FriendsFragment();
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
        setHasOptionsMenu(true);
        generalFactory = GeneralFactory.getGeneralFactory();
        friendModelList = generalFactory.getFriendModelList();
        int addFriend = R.drawable.add_new_friend_icon;
        FriendModel friendAdder = new FriendModel("Add Friend","250+ Nearby",addFriend);
        friendModelList.add(0,friendAdder);
        friendAdapt = new FriendAdapt(friendModelList,getActivity());

        SubscriptionListener subscriptionListener = new SubscriptionListener() {
            @Override
            public void subscriptionRequested(int userId) {

            }
        };



        friendAdapt.friendMover(new FriendAdapt.FriendAptListener() {
            @Override
            public void friendResponder(int position) {
                if(position == 0){
                    userId = SessionManager.getSessionManager(getActivity()).getConnectyUser();
                    Log.d("userId",""+userId);
                    chatRoster = ConnectycubeChatService.getInstance().getRoster(ConnectycubeRoster.SubscriptionMode.mutual, subscriptionListener);

                    if(userId != -1){
                        if (chatRoster.contains(userID)) {
                            try {
                                chatRoster.subscribe(userID);
                                Toast.makeText(getActivity(),"Request sent to ",Toast.LENGTH_SHORT)
                                        .show();
                            } catch (Exception e) {
                                Log.d("Error",e.getMessage());
                            }
                        } else {
                            try {
                                chatRoster.createEntry(userID, null);
                            } catch (Exception e) {

                            }
                        }
                    }
                    Log.d("AddFriend","You clicked Add");
                }else{
                    Log.d("Chat","Move to Chat");
                }
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.friendsmenu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("TAG", "QueryTextSubmit: " + s);

                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                friendAdapt.getFilter().filter(s);
                Log.d("TAG", "QueryTextChange: " + s);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int search = R.id.menu_item_search;
        final int invite = R.id.inv;
        switch (item.getItemId()){
            case search:
                break;
            case invite:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, "Join me to use the wwam app");
                i.putExtra(Intent.EXTRA_SUBJECT, "Share with");
                i = Intent.createChooser(i,"Send to a friend");
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.friends_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(friendAdapt);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Friends");
        return view;
    }


}