package com.example.waam;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SearcMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searc_message);
    }

    public void back(View view) {
      finish();
       // Intent intent = new Intent(SearcMessage.this, ChatMessage.class);
      //  startActivity(intent);
    }
}