package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Successverified extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successverified);
        WaamUser user = (WaamUser) getIntent().getSerializableExtra("WaamUser");
        String Fullname = getIntent().getStringExtra("name");
        String token = getIntent().getStringExtra("tokenbearer");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(Successverified.this, LocationDetectActivity.class);
                home.putExtra("name", Fullname);
                Log.d("TAG", ""+Fullname);

                home.putExtra("WaamUser",user);
                if (token != null){
                    home.putExtra("bearer", token);
                }

                Log.d("TAG", "TOKENSHOW3 " +token);
                startActivity(home);
                finish();
            }
            },SPLASH_TIME_OUT);
    }
}