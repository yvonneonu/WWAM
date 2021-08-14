package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.model.ConnectycubeUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import timber.log.Timber;

public class AllUsersActivity extends AppCompatActivity {
    private ConnectiAdapeter friendAdapt;
    private List<ConnectycubeUser> friendModelList;
    private GeneralFactory generalFactoryInstance;
    private ProgressBar bar;
    private ConnectycubeUser waamUser;
    private TextView textView;
    public static final String FRIENDS = "friends";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alluseractivity);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        generalFactoryInstance = GeneralFactory.getGeneralFactory(this);
        RecyclerView recyclerView = findViewById(R.id.friends_recycler);
        textView = findViewById(R.id.textView99);
        bar = findViewById(R.id.progressBarng);
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Add Friends");
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        generalFactoryInstance.fetchAllUser1(new GeneralFactory.FetchAllConnecty() {
            @Override
            public void fetchConnectyUsers(List<ConnectycubeUser> connectycubeUsers) {
                friendModelList = connectycubeUsers;
             if (friendModelList.size() > 0){
                    friendAdapt = new ConnectiAdapeter(friendModelList, AllUsersActivity.this);
                    bar.setVisibility(View.GONE);

                    recyclerView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new GridLayoutManager(AllUsersActivity.this, 3));
                    recyclerView.setAdapter(friendAdapt);
                    friendAdapt.friendMover1(new ConnectiAdapeter.FriendAptListener1() {
                        @Override
                        public void friendResponder1(int position) {
                            ConnectycubeUser user = friendModelList.get(position);
                            Intent intent = new Intent(AllUsersActivity.this, ChatMessage.class);
                            intent.putExtra("userId",user.getId());
                            startActivity(intent);
                            /*ArrayList<Integer> occupantIds = new ArrayList<Integer>();
                            occupantIds.add(user.getId());

                            ConnectycubeChatDialog dialog = new ConnectycubeChatDialog();
                            dialog.setType(ConnectycubeDialogType.PRIVATE);
                            dialog.setOccupantsIds(occupantIds);


                            ConnectycubeRestChatService.createChatDialog(dialog).performAsync(new EntityCallback<ConnectycubeChatDialog>() {
                                @Override
                                public void onSuccess(ConnectycubeChatDialog createdDialog, Bundle params) {
                                    Log.d("Timber",createdDialog.getDialogId());

                                }

                                @Override
                                public void onError(ResponseException exception) {
                                    Timber.d("%s", exception.getMessage());
                                }
                            });*/

                            /*generalFactoryInstance.loadSpecUserFromConnec(user.getEmail());
                            int addUser = user.getId();

                            Log.d("getid", ""+addUser);

                            RosterListener rosterListener = new RosterListener() {
                                @Override
                                public void entriesDeleted(Collection<Integer> userIds) {
                                    Log.d("taaag2", ""+userIds.toString());



                                }

                                @Override
                                public void entriesAdded(Collection<Integer> userIds) {
                                    Log.d("taaag", ""+userIds.toString());


                                }

                                @Override
                                public void entriesUpdated(Collection<Integer> userIds) {
                                    Log.d("taaag1", ""+userIds.toString());


                                }

                                @Override
                                public void presenceChanged(ConnectycubePresence presence) {

                                }
                            };

                            SubscriptionListener subscriptionListener = new SubscriptionListener() {
                                @Override
                                public void subscriptionRequested(int userId) {

                                    Log.d("taaag5", ""+userId);


                                }
                            };


                            Log.d("boningyourface", ""+ConnectycubeRoster.SubscriptionMode.mutual);
                            Log.d("boningyourfac", ""+subscriptionListener);

                            ConnectycubeRoster chatRoster = ConnectycubeChatService.getInstance().getRoster(ConnectycubeRoster.SubscriptionMode.mutual, subscriptionListener);


                            Log.d("logthis", ""+chatRoster.toString());


                                chatRoster.addRosterListener(rosterListener);
                                //  int userID = 34;

                            //08100642038aA@
                                if (chatRoster.contains(addUser)) {
                                    try {
                                        chatRoster.subscribe(addUser);
                                        Log.d("gre", ""+chatRoster.getEntries());

                                            Intent intent = new Intent(AllUsersActivity.this, ChatMessage.class);
                                            intent.putExtra(ChatMessage.FRIENDS, user);
                                            startActivity(intent);


                                    } catch (Exception e) {
                                        Log.d("gre", ""+e.getMessage());


                                    }
                                } else {
                                    try {
                                        chatRoster.createEntry(addUser, null);
                                    } catch (Exception e) {
                                        Log.d("gre12", ""+e.getMessage());



                                    }
                                }*/




                           /* Intent intent = new Intent(AllUsersActivity.this, ChatMessage.class);
                            intent.putExtra(ChatMessage.FRIENDS, user);
                            startActivity(intent);*/
                           /* if (friendAdapt != null && FirebaseAuth.getInstance().getCurrentUser() != null) {
                                String branch = FirebaseAuth.getInstance().getCurrentUser().getUid() + FRIENDS;

                                if (friendModelList != null) {
                                    String myId = FirebaseAuth.getInstance().getUid() + AllUsersActivity.FRIENDS;
                                    generalFactoryInstance.loadFriends(myId, new GeneralFactory.FetchFriends() {
                                        @Override
                                        public void friendsFetcher(List<WaamUser> friends) {
                                            Log.d("SizeOfFriend", "" + friends.size());
//                                            Log.d("IsFriend", ""+waamUser.getUid());
                                            for (WaamUser user : friends) {


                                                Log.d("IAmInFor", "" + waamUser.getUid());
                                                if (user.getUid().equals(waamUser.getUid())) {
                                                    //  Log.d("IAmTrue", ""+waamUser.getUid());
                                                    Intent intent = new Intent(AllUsersActivity.this, ChatMessage.class);
                                                    intent.putExtra(ChatMessage.FRIENDS, waamUser);
                                                    startActivity(intent);
                                                } else {
                                                    // Log.d("IAmFalse", ""+waamUser.getUid());
                                                    // button.setVisibility(View.VISIBLE);
                                                    //linlayout.setVisibility(View.GONE);
                                                }
                                            }
                                        }
                                    });
                                } else {
                                    Log.d("yessoo", "" + friendModelList);
                                }

                            }*/

                        }
                    });


                }else {
                 recyclerView.setVisibility(View.GONE);
                 bar.setVisibility(View.GONE);
                 textView.setVisibility(View.VISIBLE);
             }


            }
        });



       /* generalFactoryInstance.fetchAllUser(new GeneralFactory.FetchAllConnecty() {
            @Override
            public void fetchConnectyUsers(List<ConnectycubeUser> connectycubeUsers) {
                friendModelList = connectycubeUsers;

                if(friendModelList.size() > 0){
                    friendAdapt = new ConnectiAdapeter(friendModelList,AllUsersActivity.this);
                    bar.setVisibility(View.GONE);

                    recyclerView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new GridLayoutManager(AllUsersActivity.this,3));
                    recyclerView.setAdapter(friendAdapt);

                  /*friendAdapt.friendMover(new FriendAdapt.FriendAptListener() {
                        @Override
                        public void friendResponder(int position) {
                           ConnectycubeUser user = friendModelList.get(position);
                            if(friendAdapt != null && FirebaseAuth.getInstance().getCurrentUser() != null){
                                String branch = FirebaseAuth.getInstance().getCurrentUser().getUid()+FRIENDS;
                                //generalFactoryInstance.addToFriend(user,branch);


                                Fragment fragment = new ConnectedFriendsFragment();
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container_layout,fragment)
                                        .commit();


                              //  Toast.makeText(AllUsersActivity.this, "Sent", Toast.LENGTH_LONG).show();

                                if (friendModelList != null){
                                    String myId = FirebaseAuth.getInstance().getUid()+AllUsersActivity.FRIENDS;
                                    generalFactoryInstance.loadFriends(myId, new GeneralFactory.FetchFriends() {
                                        @Override
                                        public void friendsFetcher(List<WaamUser> friends) {
                                            Log.d("SizeOfFriend",""+friends.size());
//                                            Log.d("IsFriend", ""+waamUser.getUid());
                                            for(WaamUser user : friends){


                                                Log.d("IAmInFor", ""+waamUser.getUid());
                                                if(user.getUid().equals(waamUser.getUid())){
                                                  //  Log.d("IAmTrue", ""+waamUser.getUid());
                                                    Intent intent = new Intent(AllUsersActivity.this,ChatMessage.class);
                                                    intent.putExtra(ChatMessage.FRIENDS,waamUser);
                                                    startActivity(intent);
                                                }else{
                                                   // Log.d("IAmFalse", ""+waamUser.getUid());
                                                    // button.setVisibility(View.VISIBLE);
                                                    //linlayout.setVisibility(View.GONE);
                                                }
                                            }
                                        }
                                    });
                                }else {
                                    Log.d("yessoo", ""+friendModelList);
                                }

                                Intent intent = new Intent(AllUsersActivity.this, DrawelayoutActivity.class);
                                intent.putExtra(ProfileFragment.PUT_PROFILE,user);
                                startActivity(intent);
                            }

                        }
                    });



                }else{
                    recyclerView.setVisibility(View.GONE);
                    bar.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }


            }

        });*/


    }

}