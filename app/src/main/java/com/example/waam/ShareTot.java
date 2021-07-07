package com.example.waam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ShareTot extends AppCompatActivity implements View.OnClickListener {
    private ImageView text2, image;
    private ConstraintLayout coonc;
    private TextView name, resizetext;
    private TextInputEditText typeText;
    private String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_tot);

        String uid = FirebaseAuth.getInstance().getUid();
        image = findViewById(R.id.imageView);
        typeText = findViewById(R.id.typeText);
        String more;


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


        //text = Objects.requireNonNull(typeText.getText()).toString();


      text2.setOnClickListener(this);
        text2.setColorFilter(Color.BLUE);

        resizetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeText != null){
                    text = Objects.requireNonNull(typeText.getText()).toString();

                    Intent intent = new Intent(ShareTot.this, textResize.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("typedText", text);
                    intent.putExtras(bundle);
                    Log.d("please diplay", text);
                    startActivity(intent);
                }
                else {
                    Log.d("please diplay", "did not show");

                }

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