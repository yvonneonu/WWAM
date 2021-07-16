package com.example.waam;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DiscoverDrawerLayerout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer1;
    private FirebaseAuth mAuth;
    private  Toolbar toolbar2;
    private PostViewModel postViewModel;
    BottomNavigationView bottomNavigationView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_drawer_layerout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar2 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar2);

        mAuth = FirebaseAuth.getInstance();
        NavigationView navigationView1 = findViewById(R.id.nav_view);
        navigationView1.setNavigationItemSelectedListener(this);

        ConstraintLayout constraintLayout = findViewById(R.id.constr);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        LinearLayout linearLayout = findViewById(R.id.logout);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        drawer1 = findViewById(R.id.drawer1_layout);
        navigationView1.setBackgroundColor(getResources().getColor(R.color.blue));
        navigationView1.setItemTextColor(ColorStateList.valueOf(Color.WHITE));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer1, toolbar2, R.string.open, R.string.close);
       // toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        //toggle.setDrawerArrowDrawable(R.drawable.ic_baseline_menu_24, );

        //toolbar2.setNavigationIcon(R.drawable.ic_baseline_lock_24);

       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView1.getHeaderView(0);
        ImageView imageView = hView.findViewById(R.id.imageView7);
        TextView nav_user = (TextView)hView.findViewById(R.id.textView96);
        TextView viewProfile = hView.findViewById(R.id.textView95);

        boolean post = getIntent().getBooleanExtra("post",false);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralFactory.getGeneralFactory(DiscoverDrawerLayerout.this)
                        .logOut(DiscoverDrawerLayerout.this);
            }
        });
        String uid = FirebaseAuth.getInstance().getUid();
        GeneralFactory.getGeneralFactory(this).loadSpecUser(uid, new GeneralFactory.SpecificUser() {
            @Override
            public void loadSpecUse(WaamUser user) {
                Glide.with(DiscoverDrawerLayerout.this)
                        .asBitmap()
                        .fitCenter()
                        .circleCrop()
                        .load(user.getImageUrl())
                        .into(imageView);


                nav_user.setText(user.getFullname());
            }
        });




        WaamUser waamUser = (WaamUser) getIntent().getSerializableExtra("YvonneSleep");
        drawer1.addDrawerListener(toggle);
        toggle.syncState();

        //toolbar2.setBackgroundColor(getResources().getColor(R.color.black));
        toolbar2.setLogo(R.drawable.topnavlogo);
        toolbar2.setNavigationIcon(R.drawable.ic_baseline_menu_24);
       //Log.d("TAG", "in activity null");
        Fragment fragment;

        if(post){
            fragment = new ViewProfile(post);
        } else if(waamUser != null){
            fragment = new ConnectedFriendsFragment(waamUser);
            bottomNavigationView.getMenu().getItem(-0).setChecked(false);
        }else{
            fragment = new DiscoverFragment();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmentcontainer, fragment);
        ft.commit();


        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ViewProfile();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bottomNavigationView.setVisibility(View.GONE);
                ft.replace(R.id.fragmentcontainer,fragment);
                ft.addToBackStack(null);
                ft.commit();
                drawer1.closeDrawer(GravityCompat.START);
                bottomNavigationView.getMenu().getItem(-0).setChecked(false);


            }
        });

}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Log.d("TAG", "not null");
        switch (item.getItemId()) {
            case R.id.membership:
                fragment = new BecomeAMemberFragment();
                bottomNavigationView.setVisibility(View.VISIBLE);
                break;

            case R.id.notification:
                Intent intent = new Intent(DiscoverDrawerLayerout.this, NotificationActivity.class);
                startActivity(intent);

            case R.id.dailymatch:
                fragment = new FindMtchBlankFragment();
                bottomNavigationView.setVisibility(View.VISIBLE);
                // intent = new Intent(DrawelayoutActivity.this, DailyMatch.class);

                break;

            case R.id.friend:

                fragment = new FriendsFragment();
                // check it out later toolbar2.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.VISIBLE);
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                break;




            case R.id.upcomingevent:
                fragment = new FindMtchBlankFragment();
                Bundle pg = new Bundle();
                pg.putBoolean(FindMtchBlankFragment.ARG_PARAM3, true);
                fragment.setArguments(pg);

                break;

            case R.id.datingagent:
                fragment = new AgentFragment();
                bottomNavigationView.getMenu().getItem(4).setChecked(true);
                break;


            case R.id.discover:

                fragment = new DiscoverFragment();
                item.setIcon(R.drawable.lowernav_explore_icon_active);
                break;

            case R.id.profile:
                fragment = new ProfileFragment();
                Log.d("show", "profile not showing");
                item.setIcon(R.drawable.lowernav_profile_icon_active);
                break;

            case R.id.messages:
                fragment = new MessagesFragment();
                item.setIcon(R.drawable.lowernav_messages_icon_active);
                break;

            case R.id.friends:
                fragment = new FriendsFragment();
                bottomNavigationView.setVisibility(View.VISIBLE);
                item.setIcon(R.drawable.lowernav_friends_icon_active);
                break;


            case R.id.agent:
                fragment = new AgentFragment();
                bottomNavigationView.setVisibility(View.VISIBLE);
                item.setIcon(R.drawable.lowernav_agent_icon_active);
                break;

        }
        if (fragment != null) {
            Log.d("TAG", "not null");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentcontainer, fragment);
            ft.commit();
            drawer1.closeDrawer(GravityCompat.START);
            return true;
        } else {
            Log.d("TAG", "is null");
        }

        return false;

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawer1.isDrawerOpen(GravityCompat.START)) {
            drawer1.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




}
