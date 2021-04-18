package com.example.waam;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class AgentProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer1;
    LinearLayout dotslayout;
    TextView[] dots;
    private boolean visible;
    private TextView textSeeMore, more, moreDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_profile);

        Toolbar toolbar2 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);

        NavigationView navigationView1 = findViewById(R.id.nav_view);
        navigationView1.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        drawer1 = findViewById(R.id.drawer1_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer1, toolbar2, R.string.open, R.string.close);

        drawer1.addDrawerListener(toggle);
        toggle.syncState();

       toolbar2.setLogo(R.drawable.topnavlogo);
       toolbar2.setNavigationIcon(R.drawable.ic_baseline_menu_24);

        textSeeMore = findViewById(R.id.textView80);
        more = findViewById(R.id.textView78);
        moreDetail = findViewById(R.id.textView79);
        visible = false;


        androidx.viewpager.widget.ViewPager viewPager = findViewById(R.id.viewPager);

        dotslayout = findViewById(R.id.linearLayoutdot);

        AgentProfileAdapter agentProfileAdapter = new AgentProfileAdapter(this);

        viewPager.setAdapter(agentProfileAdapter);

        addDot(0);

        viewPager.addOnPageChangeListener(changeListener);


/*        textSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visible){
                    visible = false;
                    textSeeMore.setText("Read More");
                    more.setVisibility(View.GONE);
                    moreDetail.setVisibility(View.GONE);
                }else{
                    visible = true;
                    textSeeMore.setText("Read Less");
                    more.setVisibility(View.VISIBLE);
                    moreDetail.setVisibility(View.VISIBLE);
                }
            }
        });*/
    }



    androidx.viewpager.widget.ViewPager.OnPageChangeListener changeListener = new androidx.viewpager.widget.ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDot(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void addDot(int position) {
        dots = new TextView[5];

        dotslayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            Log.d("Slider", "Am in dis Onboarding textView");
            dots[i] = new TextView(this);

            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(45);
            dots[i].setTextColor(getResources().getColor(R.color.offwhite));
            dotslayout.addView(dots[i]);


        }

        if(position == 0) {
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
        if (position == 1) {
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
        if (position == 2) {
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
        if (position == 3) {
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
        if (position == 4) {
            dots[position].setTextColor(getResources().getColor(R.color.white));

        }

    }


    public void bookme(View view) {
        Toast.makeText(AgentProfile.this, "Book me", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Log.d("TAG", "not null");
        switch (item.getItemId()) {
            case R.id.membership:
                fragment = new BecomeAMemberFragment();
                finish();

                break;

            case R.id.discover:
                fragment = new DiscoverFragment();
                item.setIcon(R.drawable.lowernav_explore_icon_active);
                finish();
                break;

            case R.id.messages:
                fragment = new MessagesFragment();
                item.setIcon(R.drawable.lowernav_messages_icon_active);
                finish();
                break;

            case R.id.friends:
                fragment = new FriendsFragment();
                item.setIcon(R.drawable.lowernav_friends_icon_active);
                finish();
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
