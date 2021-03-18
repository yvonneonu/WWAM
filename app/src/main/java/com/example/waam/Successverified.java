package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class Successverified extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successverified);
        String Fullname = getIntent().getStringExtra("name");
        String token = getIntent().getStringExtra("tokenbearer");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(Successverified.this, LocationDetectActivity.class);
                home.putExtra("name", Fullname);
                home.putExtra("bearer", token);
                Log.d("TAG", "TOKENSHOW3 " +token);
                startActivity(home);
                finish();
            }
            },SPLASH_TIME_OUT);
    }
}