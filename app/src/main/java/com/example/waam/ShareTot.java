package com.example.waam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class ShareTot extends AppCompatActivity implements View.OnClickListener {
    private ImageView text2, image;
    private ConstraintLayout coonc;
    private TextView name, resizetext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_tot);

        String uid = FirebaseAuth.getInstance().getUid();
        image = findViewById(R.id.imageView);


        coonc = findViewById(R.id.coonc);
        text2 = findViewById(R.id.text2);
        name = findViewById(R.id.textView71);
        resizetext = findViewById(R.id.share);

        GeneralFactory.getGeneralFactory(this).loadSpecUser(uid, new GeneralFactory.SpecificUser() {
            @Override
            public void loadSpecUse(WaamUser user) {
                Glide.with(ShareTot.this)
                        .asBitmap()
                        .fitCenter()
                        .circleCrop()
                        .load(user.getImageUrl())
                        .into(image);

                name.setText(user.getFullname());
            }
        });




      text2.setOnClickListener(this);
        text2.setColorFilter(Color.BLUE);

        resizetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareTot.this, textResize.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClick(View v) {
        final int image = R.id.text2;
        final int totFram = R.id.totFram;

        switch (v.getId()){
            case image:
                text2.setColorFilter(Color.BLUE);

                Fragment fragment = new TextdisplayFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(totFram, fragment);
                fragmentTransaction.commit();

                break;
        }
    }
    public void backtofragment(View view) {
        finish();
    }





}