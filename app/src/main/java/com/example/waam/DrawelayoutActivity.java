package com.example.waam;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DrawelayoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private BottomNavigationView bottomNavigationView;
    private Intent intent;
    private FirebaseAuth mAuth;

    String uid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawelayout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        String token = getIntent().getStringExtra("toking");
        WaamUser friendsProfile = (WaamUser) getIntent().getSerializableExtra("PutProfile");
        WaamUser videopicfragm = (WaamUser) getIntent().getSerializableExtra("WaamUser");
        boolean clicked = getIntent().getBooleanExtra("clicked", false);

        boolean post = getIntent().getBooleanExtra("post",false);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        LinearLayout linearLayout = findViewById(R.id.logout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);

        //mAuth = FirebaseAuth.getInstance();

        navigationView.setBackgroundColor(getResources().getColor(R.color.blue));

        navigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));


        navigationView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE));
        View hView =  navigationView.getHeaderView(0);
        ImageView imageView = hView.findViewById(R.id.imageView7);
        TextView nav_user = hView.findViewById(R.id.textView96);
        TextView viewProfile = hView.findViewById(R.id.textView95);





        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralFactory.getGeneralFactory(DrawelayoutActivity.this)
                        .logOut(DrawelayoutActivity.this);
            }
        });
        String uid = FirebaseAuth.getInstance().getUid();
        GeneralFactory.getGeneralFactory(this).loadSpecUser(uid, new GeneralFactory.SpecificUser() {
            @Override
            public void loadSpecUse(WaamUser user) {
                Glide.with(DrawelayoutActivity.this)
                        .asBitmap()
                        .fitCenter()
                        .circleCrop()
                        .load(user.getImageUrl())
                        .into(imageView);



                nav_user.setText(user.getFullname());
            }
        });

        WaamUser waamUser = (WaamUser) getIntent().getSerializableExtra("YvonneSleep");
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //toolbar.setLogo(R.drawable.topnavlogo);
       // toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        Log.d("TAG", "in activity null");
        Fragment fragmenting;
        if(post){
            fragmenting = new ViewProfile(post);
        } else if(waamUser != null){
            fragmenting = new ConnectedFriendsFragment(waamUser);
            bottomNavigationView.getMenu().getItem(-0).setChecked(false);
       /* } else if (videopicfragm != null) {
            fragmenting = new ConnectedFriendsFragment(videopicfragm);*/
          //  bottomNavigationView.getMenu().getItem(2).setChecked(true);
        } else {
            fragmenting = new ExploreFragment();

            // GeneralFactory.getGeneralFactory(this).loadSpecUser();

        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmentcontainer, fragmenting);
        ft.addToBackStack(null);
        ft.commit();

        /*Fragment fragment1 = new MessagesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentcontainer, fragment1);
        fragmentTransaction.commit();*/

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ViewProfile();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bottomNavigationView.setVisibility(View.GONE);
                ft.replace(R.id.fragmentcontainer,fragment);
                ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);
                //bottomNavigationView.getMenu().getItem(-0).setChecked(false);


            }
        });
        bottomNavigationView.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //updateNavigationBarState();
    }

    
    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Log.d("TAG", "not null");

        switch (item.getItemId()) {
           /* case R.id.membership:
                fragment = new BecomeAMemberFragment();
                break;*/

            case R.id.notification:
                Intent intent = new Intent(DrawelayoutActivity.this, NotificationActivity.class);
                startActivity(intent);


                break;
            case R.id.dailymatch:
                fragment = new FindMtchBlankFragment();

                // intent = new Intent(DrawelayoutActivity.this, DailyMatch.class);

                break;

            case R.id.friend:
                fragment = new FriendsFragment();

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


            case R.id.explore:
                fragment = new ExploreFragment();
                item.setIcon(R.drawable.lowernav_explore_icon_active);
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




            case R.id.profile:
                //fragment = new ProfileFragment();
               fragment = new NewAgentProfileFragment();
                Log.d("view", "profile page");
                bottomNavigationView.getMenu().getItem(4).setChecked(true);

                item.setIcon(R.drawable.lowernav_profile_icon_active);
                break;

            case R.id.agent:
                fragment = new AgentFragment();
                item.setIcon(R.drawable.lowernav_agent_icon_active);
                break;

        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentcontainer, fragment);
            ft.addToBackStack(null);
            ft.commit();
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else {
            Log.d("TAG", "is null");



        }

        return false;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

}