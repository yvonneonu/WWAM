package com.example.waam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class AllUsersActivity extends AppCompatActivity {
    private FriendAdapt friendAdapt;
    private List<WaamUser> friendModelList;
    private GeneralFactory generalFactoryInstance;
    private ProgressBar bar;
    private TextView textView;
    public static final String FRIENDS = "friends";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alluseractivity);
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
        generalFactoryInstance.fetchAllUser(new GeneralFactory.FetchFriends() {
            @Override
            public void friendsFetcher(List<WaamUser> friends) {
                friendModelList = friends;

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
                            WaamUser user = friendModelList.get(position);
                            if(friendAdapt != null && FirebaseAuth.getInstance().getCurrentUser() != null){
                                String branch = FirebaseAuth.getInstance().getCurrentUser().getUid()+FRIENDS;
                                generalFactoryInstance.addToFriend(user,branch);
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