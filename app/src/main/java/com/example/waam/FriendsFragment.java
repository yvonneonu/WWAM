package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.chat.ConnectycubeRestChatService;
import com.connectycube.chat.ConnectycubeRoster;
import com.connectycube.chat.listeners.RosterListener;
import com.connectycube.chat.listeners.SubscriptionListener;
import com.connectycube.chat.model.ConnectycubeChatDialog;
import com.connectycube.chat.model.ConnectycubeDialogType;
import com.connectycube.chat.model.ConnectycubePresence;
import com.connectycube.core.Consts;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.core.request.RequestGetBuilder;
import com.connectycube.users.model.ConnectycubeUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendsFragment extends Fragment {

    ArrayList<Integer> occupantIds = new ArrayList<Integer>();

    private ConnectycubeChatService chatService;
    int userID = 4134562;
    ConnectycubeChatDialog privateDialog;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ConnectycubeRoster chatRoster;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   int id;
    String pasword;
    private FriendAdapt friendAdapt;
    private List<FriendModel> friendModelList;
    private GeneralFactory generalFactory;
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
        id = getActivity().getIntent().getIntExtra("id", id);

        pasword = getActivity().getIntent().getStringExtra("password");
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


        friendAdapt.friendMover(new FriendAdapt.FriendAptListener() {
            @Override
            public void friendResponder(int position) {
                if(position == 0){
                    final ConnectycubeUser user = new ConnectycubeUser();
                    // user.setId(4152184);

                    user.setId(id);
                    user.setPassword(pasword);
                    chatService.login(user, new EntityCallback() {


                        @Override
                        public void onSuccess(Object o, Bundle bundle) {

                        }

                        @Override
                        public void onError(ResponseException errors) {

                        }
                    });

                    if (chatRoster.contains(userID)) {
                        try {
                            chatRoster.subscribe(userID);
                        } catch (Exception e) {

                        }
                    } else {
                        try {
                            chatRoster.createEntry(userID, null);
                        } catch (Exception e) {

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
        // Inflate the layout for this fragment\
        occupantIds.add(4134562);
        ConnectycubeChatDialog dialog = new ConnectycubeChatDialog();
        dialog.setType(ConnectycubeDialogType.PRIVATE);
        dialog.setOccupantsIds(occupantIds);

//or just use DialogUtils
//ConnectycubeChatDialog dialog = DialogUtils.buildPrivateDialog(recipientId);

        ConnectycubeRestChatService.createChatDialog(dialog).performAsync(new EntityCallback<ConnectycubeChatDialog>() {
            @Override
            public void onSuccess(ConnectycubeChatDialog createdDialog, Bundle params) {

            }

            @Override
            public void onError(ResponseException exception) {

            }
        });

        RequestGetBuilder requestBuilder = new RequestGetBuilder();
        requestBuilder.setLimit(50);
        requestBuilder.setSkip(100);
//requestBuilder.sortAsc(Consts.DIALOG_LAST_MESSAGE_DATE_SENT_FIELD_NAME);

        ConnectycubeRestChatService.getChatDialogs((ConnectycubeDialogType)null, requestBuilder).performAsync(new EntityCallback<ArrayList<ConnectycubeChatDialog>>() {
            @Override
            public void onSuccess(ArrayList<ConnectycubeChatDialog> dialogs, Bundle params) {
                int totalEntries = params.getInt(Consts.TOTAL_ENTRIES);
            }

            @Override
            public void onError(ResponseException exception) {

            }
        });

        ConnectycubeChatDialog dialog1 = new ConnectycubeChatDialog();
        dialog1.setDialogId("5356c64ab35c12bd3b108a41");
        dialog1.setName("Hawaii party");
        dialog1.setPhoto("https://new_photo_url"); // or it can be an ID to some file in Storage module
        dialog1.setDescription("New dialog description");

        ConnectycubeRestChatService.updateChatDialog(dialog1, null).performAsync(new EntityCallback<ConnectycubeChatDialog>() {
            @Override
            public void onSuccess(ConnectycubeChatDialog updatedDialog, Bundle bundle) {

            }

            @Override
            public void onError(ResponseException error) {

            }
        });




        RosterListener rosterListener = new RosterListener() {
            @Override
            public void entriesDeleted(Collection<Integer> userIds) {

            }

            @Override
            public void entriesAdded(Collection<Integer> userIds) {

            }

            @Override
            public void entriesUpdated(Collection<Integer> userIds) {

            }

            @Override
            public void presenceChanged(ConnectycubePresence presence) {

            }
        };

        SubscriptionListener subscriptionListener = new SubscriptionListener() {
            @Override
            public void subscriptionRequested(int userId) {

            }
        };

// Do this after success Chat login
        chatRoster = ConnectycubeChatService.getInstance().getRoster(ConnectycubeRoster.SubscriptionMode.mutual, subscriptionListener);
//        chatRoster.addRosterListener(rosterListener);

        //Collection<ConnectycubeRosterEntry> entries = chatRoster.getEntries();
//        Collection<ConnectycubeRosterEntry> entries = chatRoster.getEntries();
        //сhatRoster.//getEntries();



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