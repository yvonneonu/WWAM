package com.example.waam;

import android.content.Intent;
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
    private BottomNavigationView bottomNavigationView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawelayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String token = getIntent().getStringExtra("toking");
        WaamUser friendsProfile = (WaamUser) getIntent().getSerializableExtra("PutProfile");
        WaamUser videopicfragm = (WaamUser) getIntent().getSerializableExtra("WaamUser");
        boolean clicked = getIntent().getBooleanExtra("clicked", false);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Log.d("TAG", "in activity null");
        Fragment fragmenting;
        if (clicked) {
            fragmenting = new MessagesFragment();
            bottomNavigationView.getMenu().getItem(1).setChecked(true);

            //onNavigationItemSelected(fragment);
            //fragment.setSe(true);
           /* bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem item) {
                    item.setIcon(R.drawable.lowernav_messages_icon_active);
                }
            });(*/
            // bottomNavigationView.setSelectedItemId(R.id.messages);
            // bottomNavigationView.onScreenStateChanged(0);

            //
        } else if (friendsProfile != null) {
            fragmenting = ProfileFragment.newInstance(friendsProfile);
        } else if (videopicfragm != null) {
            fragmenting = new ConnectedFriendsFragment(videopicfragm);
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
            case R.id.membership:
                fragment = new BecomeAMemberFragment();
                break;

            case R.id.friend:
                fragment = new FriendsFragment();

                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                break;

            case R.id.datingagent:
                fragment = new AgentFragment();
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

            case R.id.dailymatch:
                fragment = new FindMtchBlankFragment();

                // intent = new Intent(DrawelayoutActivity.this, DailyMatch.class);

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
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentcontainer, fragment);
            ft.commit();
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else {
            Log.d("TAG", "is null");
            startActivity(intent);


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