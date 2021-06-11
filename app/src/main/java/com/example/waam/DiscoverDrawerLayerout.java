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
import com.google.firebase.auth.FirebaseAuth;

public class DiscoverDrawerLayerout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_drawer_layerout);

        Toolbar toolbar2 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar2);

        mAuth = FirebaseAuth.getInstance();
        NavigationView navigationView1 = findViewById(R.id.nav_view);
        navigationView1.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        drawer1 = findViewById(R.id.drawer1_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer1, toolbar2, R.string.open, R.string.close);
       // toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        //toggle.setDrawerArrowDrawable(R.drawable.ic_baseline_menu_24, );

        //toolbar2.setNavigationIcon(R.drawable.ic_baseline_lock_24);

        drawer1.addDrawerListener(toggle);
        toggle.syncState();

        //toolbar2.setBackgroundColor(getResources().getColor(R.color.black));
        toolbar2.setLogo(R.drawable.topnavlogo);
        toolbar2.setNavigationIcon(R.drawable.ic_baseline_menu_24);
       //Log.d("TAG", "in activity null");
       Fragment fragment = new DiscoverFragment();
       FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
       ft.add(R.id.fragmentcontainer, fragment);
       ft.commit();



}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Log.d("TAG", "not null");
        switch (item.getItemId()) {
            case R.id.membership:
                fragment = new BecomeAMemberFragment();

                break;

            case R.id.discover:

                fragment = new DiscoverFragment();
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
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
