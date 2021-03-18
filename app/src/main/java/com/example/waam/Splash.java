package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Bundle bundle = getIntent().getExtras();
       // bundle.getString("token");
        String phonenumber = getIntent().getStringExtra("phonenumber");
    //    String token = getIntent().getStringExtra("token");
        String token = bundle.getString("token");
        Log.d("TAG", "TOKENSHOW " +token);
        String Fullname = getIntent().getStringExtra("name");



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent home = new Intent(Splash.this, Verfy2.class);
               //home.putExtras(bundle);
                home.putExtra("number", phonenumber);
                home.putExtra("token", token);
                home.putExtra("name", Fullname);
               startActivity(home);
               finish();
            }
        },SPLASH_TIME_OUT);
    }
}