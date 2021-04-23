package com.example.waam;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DailyMatch extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//reuse
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_daily_match);
        toolbar = findViewById(R.id.tool_bar1);


    }

    public void pressback(View view) {
        finish();
    }
}