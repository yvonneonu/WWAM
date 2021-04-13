package com.example.waam;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SearcMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searc_message);
    }

    public void backtologin(View view) {
        Fragment fragment = new MessagesFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.textmoo, fragment);
        ft.commit();
       // Intent intent = new Intent(SearcMessage.this, ChatMessage.class);
      //  startActivity(intent);
    }
}