package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class AllUsersActivity extends AppCompatActivity {
    private FriendAdapt friendAdapt;
    private List<WaamUser> friendModelList;
    private GeneralFactory generalFactoryInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_friends);
        generalFactoryInstance = GeneralFactory.getGeneralFactory();
        generalFactoryInstance.fetchAllUser(new GeneralFactory.FetchFriends() {
            @Override
            public void friendsFetcher(List<WaamUser> friends) {
                friendModelList = friends;
                friendAdapt = new FriendAdapt(friendModelList,AllUsersActivity.this);
                RecyclerView recyclerView = findViewById(R.id.friends_recycler);
                recyclerView.setLayoutManager(new GridLayoutManager(AllUsersActivity.this,3));
                recyclerView.setAdapter(friendAdapt);
            }
        });

        friendAdapt.friendMover(new FriendAdapt.FriendAptListener() {
            @Override
            public void friendResponder(int position) {
                WaamUser user = friendModelList.get(position);
                if(friendAdapt != null && FirebaseAuth.getInstance().getCurrentUser() != null){
                    String branch = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    generalFactoryInstance.addToFriend(user,branch,AllUsersActivity.this);

                }

            }
        });

    }
}