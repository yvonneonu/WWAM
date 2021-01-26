package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewPager extends AppCompatActivity {
    private LinearLayout linearLayout;

    private TextView login;

    LinearLayout dotslayout;
    TextView[] dots;
   // private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        androidx.viewpager.widget.ViewPager viewPager = findViewById(R.id.viewpage1);
        dotslayout = findViewById(R.id.linearLayoutdot);
        Button signin = findViewById(R.id.signhere);
        TextView signup = findViewById(R.id.signup);


        //signin = findViewById(R.id.signhere);

        SliderAdapter sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDot(0);
        viewPager.addOnPageChangeListener(changeListener);
        //ignin.setOnClickListener(v ->SignUphere());


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
    private void SignUphere() {
        Intent intent = new Intent(ViewPager.this, Login.class);
        startActivity(intent);
        finish();
    }

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
        if(dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.blue));
        }
        /*if (dots.length == 0) {
            dots[position].setTextColor(getResources().getColor(R.color.blue));
        } else if (dots.length == 1) {
            dots[position].setTextColor(getResources().getColor(R.color.red));
        } else if (dots.length == 2) {
            dots[position].setTextColor(getResources().getColor(R.color.green));
        } else if (dots.length == 3) {
            dots[position].setTextColor(getResources().getColor(R.color.yellow));
        }else if (dots.length == 4) {
            dots[position].setTextColor(getResources().getColor(R.color.red));

        }*/
    }

}
