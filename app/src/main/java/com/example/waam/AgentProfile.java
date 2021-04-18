package com.example.waam;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AgentProfile extends AppCompatActivity {


    LinearLayout dotslayout;
    TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_profile);



        androidx.viewpager.widget.ViewPager viewPager = findViewById(R.id.viewPager);

        dotslayout = findViewById(R.id.linearLayoutdot);

        AgentProfileAdapter agentProfileAdapter = new AgentProfileAdapter(this);

        viewPager.setAdapter(agentProfileAdapter);

        addDot(0);

        viewPager.addOnPageChangeListener(changeListener);
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


}
