package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connectycube.users.model.ConnectycubeUser;

import java.util.List;

public class AddNewFriends extends AppCompatActivity {
    private SearchView input;
    private List<ConnectycubeUser> friendModelList;
    private FriendAdapt friendAdapt;
    private RecyclerView recyclerView;
    private TextView text, send;
    public static final String FRIENDS = "friends";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_friends);
        input = findViewById(R.id.input);
        recyclerView = findViewById(R.id.friends_recycler);
        text = findViewById(R.id.bacck);
        send = findViewById(R.id.send);




        GeneralFactory generalFactory = GeneralFactory.getGeneralFactory(AddNewFriends.this);

        generalFactory.fetchAllUser(new GeneralFactory.FetchAllConnecty() {
            @Override
            public void fetchConnectyUsers(List<ConnectycubeUser> connectycubeUsers) {
                if (connectycubeUsers.size() > 0){
                    friendAdapt = new FriendAdapt(friendModelList,AddNewFriends.this);
                    //bar.setVisibility(View.GONE);

                    recyclerView.setVisibility(View.VISIBLE);
                    // textView.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new GridLayoutManager(AddNewFriends.this,3));
                    recyclerView.setAdapter(friendAdapt);
                }else {

                }
            }
        });

      /*  generalFactory.fetchAllUser(new GeneralFactory.FetchFriends() {
            @Override
            public void friendsFetcher(List<WaamUser> friends) {
                friendModelList = friends;

                if(friendModelList.size() > 0){
                    friendAdapt = new FriendAdapt(friendModelList,AddNewFriends.this);
                    //bar.setVisibility(View.GONE);

                    recyclerView.setVisibility(View.VISIBLE);
                   // textView.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new GridLayoutManager(AddNewFriends.this,3));
                    recyclerView.setAdapter(friendAdapt);

                    friendAdapt.friendMover(new FriendAdapt.FriendAptListener() {
                        @Override
                        public void friendResponder(int position) {
                            WaamUser user = friendModelList.get(position);
                            if(friendAdapt != null && FirebaseAuth.getInstance().getCurrentUser() != null){
                                String branch = FirebaseAuth.getInstance().getCurrentUser().getUid()+FRIENDS;
                                //generalFactoryInstance.addToFriend(user,branch);

                                Toast.makeText(AddNewFriends.this, "Sent", Toast.LENGTH_LONG).show();

                               /* Fragment fragment = new ConnectedFriendsFragment();
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container_layout,fragment)
                                        .commit();*/
                                /*Intent intent = new Intent(AddNewFriends.this, DrawelayoutActivity.class);
                                intent.putExtra(ProfileFragment.PUT_PROFILE,user);
                                startActivity(intent);
                            }

                        }
                    });
                }else{
                    recyclerView.setVisibility(View.GONE);
                    //bar.setVisibility(View.GONE);
                  //  textView.setVisibility(View.VISIBLE);
                }

            }
        });*/





        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        input.setQueryHint("search..");


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddNewFriends.this, "Sent", Toast.LENGTH_LONG).show();
            }
        });
        input.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
}