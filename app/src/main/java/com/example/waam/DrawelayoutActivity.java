package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class DrawelayoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawelayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String token = getIntent().getStringExtra("toking");
        boolean clicked = getIntent().getBooleanExtra("clicked",false);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Log.d("TAG","in activity null");
        Fragment fragment;
        if(clicked){
            fragment = new MessagesFragment();

        }else{
            fragment = new ExploreFragment();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmentcontainer,fragment);
        ft.commit();


        /*Fragment fragment1 = new MessagesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentcontainer, fragment1);
        fragmentTransaction.commit();*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Log.d("TAG","not null");

        switch (item.getItemId()){
            case R.id.membership:
                fragment = new BecomeAMemberFragment();
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
                item.setIcon(R.drawable.lowernav_friends_icon_active);
                break;

            case R.id.profile:
                fragment = new ProfileFragment();
                item.setIcon(R.drawable.lowernav_profile_icon_active);
                break;

            case R.id.agent:
                fragment = new AgentFragment();
                item.setIcon(R.drawable.lowernav_agent_icon_active);
                break;
        }
        if(fragment != null){
            Log.d("TAG","not null");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentcontainer,fragment);
            ft.commit();
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }else{
            Log.d("TAG","is null");
        }

        return false;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}