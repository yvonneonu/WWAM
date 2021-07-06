package com.example.waam;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class textResize extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_resize);

        Intent intent = getIntent();
        String str = intent.getStringExtra("typedText");


        //receiver_msg.setText(str);
    }
}