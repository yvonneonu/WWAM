package com.example.waam;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ShareTot extends AppCompatActivity implements View.OnClickListener {
    private ImageView text2;
    private ConstraintLayout coonc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_tot);

        coonc = findViewById(R.id.coonc);
        text2 = findViewById(R.id.text2);

      text2.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        final int image = R.id.text2;
        final int fram = R.id.fram1;

        switch (v.getId()){
            case image:
                text2.setColorFilter(Color.BLUE);

                Fragment fragment = new TextdisplayFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(fram, fragment);
                fragmentTransaction.commit();

                break;
        }
    }
    public void backtofragment(View view) {
        finish();
    }




}