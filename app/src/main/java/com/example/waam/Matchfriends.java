package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Matchfriends extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    ImageView view1;
    TextView mfragment;

    FragmentManager fragmentManager = getSupportFragmentManager();
    final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    final MessagesFragment myFragment = new MessagesFragment();

    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchfriends);
        mFragmentManager = getSupportFragmentManager();
        view1 = findViewById(R.id.imageView26);
        //mfragment = findViewById(R.id.textmoo);

       // help();
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Matchfriends.this, DrawelayoutActivity.class);
                intent.putExtra("clicked",true);
                startActivity(intent);
                //myFragment.setArguments(v);
                //fragmentTransaction.add(R.id.frameLayout, myFragment).commit();

                //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
               // fragmentTransaction.replace(R.id.container_layout, new MessagesFragment()).commit();
            }
        });
    }

   // private void help() {


    public void keepswiping(View view) {
        Intent intent = new Intent(Matchfriends.this, MatchFragment.class);
        startActivity(intent);
    }

}