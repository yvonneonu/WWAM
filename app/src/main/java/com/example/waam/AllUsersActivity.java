package com.example.waam;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connectycube.users.model.ConnectycubeUser;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class AllUsersActivity extends AppCompatActivity {
    private FriendAdapt friendAdapt;
    private List<ConnectycubeUser> friendModelList;
    private GeneralFactory generalFactoryInstance;
    private ProgressBar bar;
    private WaamUser waamUser;
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

       /* if(friendAdapt != null && FirebaseAuth.getInstance().getCurrentUser() != null){

            PagedRequestBuilder pagedRequestBuilder = new PagedRequestBuilder();
            pagedRequestBuilder.setPage(1);
            pagedRequestBuilder.setPerPage(50);

            ArrayList<String> userTags = new ArrayList<>();
            String User = FirebaseAuth.getInstance().getCurrentUser().getEmail();


            friendModelList.add(waamUser);

           // userTags.add(User);
           // userTags.add(friendModelList.add(waamUser))

            Bundle params = new Bundle();

            ConnectycubeUsers.getUsersByFullName("Marvin Samuel", pagedRequestBuilder, params).performAsync(new EntityCallback<ArrayList<ConnectycubeUser>>() {
                @Override
                public void onSuccess(ArrayList<ConnectycubeUser> users, Bundle args) {

                    Log.d("connecticu", ""+users);

                }

                @Override
                public void onError(ResponseException error) {
                    Log.d("connecticu", ""+error);


                }
            });

        }else {
            Log.d("connecticu", "nouserfound");


        }*/


          /* PagedRequestBuilder pagedRequestBuilder = new PagedRequestBuilder();
        pagedRequestBuilder.setPage(1);
        pagedRequestBuilder.setPerPage(50);

        Bundle params = new Bundle();

        ArrayList<String> usersPhones = new ArrayList<>();
//       String branch = FirebaseAuth.getInstance().getCurrentUser().getEmail();

     //   usersPhones.add(fetchAllWaamUsers.friendsFetcher(allWaamUsers));

        usersPhones.add("drip@gmail.com");


        ConnectycubeUsers.getUsersByEmails(usersPhones, pagedRequestBuilder, params).performAsync(new EntityCallback<ArrayList<ConnectycubeUser>>() {
            @Override
            public void onSuccess(ArrayList<ConnectycubeUser> users, Bundle args) {
                Log.d("AllUsers", "" +users.size());
                friendAdapt = new FriendAdapt(friendModelList,AllUsersActivity.this);
                //bar.setVisibility(View.GONE);

                bar.setVisibility(View.GONE);

                recyclerView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                recyclerView.setLayoutManager(new GridLayoutManager(AllUsersActivity.this,3));
                recyclerView.setAdapter(friendAdapt);

            }

            @Override
            public void onError(ResponseException error) {
                Log.d("AllUsers", ""+error.getMessage());


            }
        });*/



        generalFactoryInstance.fetchAllUser(new GeneralFactory.FetchAllConnecty() {
            @Override
            public void fetchConnectyUsers(List<ConnectycubeUser> connectycubeUsers) {




                friendModelList = connectycubeUsers;

                if(friendModelList.size() > 0){
                    friendAdapt = new FriendAdapt(friendModelList,AllUsersActivity.this);
                    bar.setVisibility(View.GONE);

                    recyclerView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new GridLayoutManager(AllUsersActivity.this,3));
                    recyclerView.setAdapter(friendAdapt);

                    friendAdapt.friendMover(new FriendAdapt.FriendAptListener() {
                        @Override
                        public void friendResponder(int position) {
                           ConnectycubeUser user = friendModelList.get(position);
                            if(friendAdapt != null && FirebaseAuth.getInstance().getCurrentUser() != null){
                                String branch = FirebaseAuth.getInstance().getCurrentUser().getUid()+FRIENDS;
                                //generalFactoryInstance.addToFriend(user,branch);


                                /*Fragment fragment = new ConnectedFriendsFragment();
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container_layout,fragment)
                                        .commit();*/


                                Toast.makeText(AllUsersActivity.this, "Sent", Toast.LENGTH_LONG).show();

                                /*if (friendModelList != null){
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
                                }*/

                              /*  String User = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                ConnectycubeUsers.getUserByEmail(User).performAsync(new EntityCallback<ConnectycubeUser>() {


                                    @Override
                                    public void onSuccess(ConnectycubeUser user, Bundle bundle) {

                                        Log.d("getthisuser", ""+user);

                                    }

                                    @Override
                                    public void onError(ResponseException error) {

                                        Log.d("getthisuser", ""+error.getMessage());


                                    }
                                });*/


                               /* Intent intent = new Intent(AllUsersActivity.this, DrawelayoutActivity.class);
                                intent.putExtra(ProfileFragment.PUT_PROFILE,user);
                                startActivity(intent);*/
                            }

                        }
                    });



                }else{
                    recyclerView.setVisibility(View.GONE);
                    bar.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }



            }

        });


    }

}