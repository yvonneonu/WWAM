package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CompleteProfile extends AppCompatActivity {
    private ImageView firstImage, secondImage,thirdImage, fourthImage, fivethImage, sixthImage, seventhImage, eightImage, ninethImage;
    private TextView wipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        firstImage = findViewById(R.id.imageView);
        secondImage = findViewById(R.id.imageView1);
        thirdImage = findViewById(R.id.imageView2);
        fourthImage = findViewById(R.id.imageView3);
        fivethImage= findViewById(R.id.imageView4);
        sixthImage = findViewById(R.id.imageView5);
        seventhImage = findViewById(R.id.imageView6);
        eightImage = findViewById(R.id.imageView7);
        ninethImage = findViewById(R.id.imageView8);
        wipe = findViewById(R.id.swipe);

        firstImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(CompleteProfile.this);
                View botomSjeetDialogview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_picture,
                       (LinearLayout)findViewById(R.id.picture));
               bottomSheetDialog.setContentView(botomSjeetDialogview);
              bottomSheetDialog.show();

            }
        });

        secondImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(CompleteProfile.this);
                View botomSjeetDialogview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_picture,
                        (LinearLayout)findViewById(R.id.picture));
                bottomSheetDialog.setContentView(botomSjeetDialogview);
                bottomSheetDialog.show();

            }
        });

        thirdImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(CompleteProfile.this);
                View botomSjeetDialogview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_picture,
                        (LinearLayout)findViewById(R.id.picture));
                bottomSheetDialog.setContentView(botomSjeetDialogview);
                bottomSheetDialog.show();

            }
        });

        fourthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(CompleteProfile.this);
                View botomSjeetDialogview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_picture,
                        (LinearLayout)findViewById(R.id.picture));
                bottomSheetDialog.setContentView(botomSjeetDialogview);
                bottomSheetDialog.show();

            }
        });

        fivethImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(CompleteProfile.this);
                View botomSjeetDialogview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_picture,
                        (LinearLayout)findViewById(R.id.picture));
                bottomSheetDialog.setContentView(botomSjeetDialogview);
                bottomSheetDialog.show();

            }
        });

        sixthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(CompleteProfile.this);
                View botomSjeetDialogview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_picture,
                        (LinearLayout)findViewById(R.id.picture));
                bottomSheetDialog.setContentView(botomSjeetDialogview);
                bottomSheetDialog.show();

            }
        });

        seventhImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(CompleteProfile.this);
                View botomSjeetDialogview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_picture,
                        (LinearLayout)findViewById(R.id.picture));
                bottomSheetDialog.setContentView(botomSjeetDialogview);
                bottomSheetDialog.show();

            }
        });

        eightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(CompleteProfile.this);
                View botomSjeetDialogview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_picture,
                        (LinearLayout)findViewById(R.id.picture));
                bottomSheetDialog.setContentView(botomSjeetDialogview);
                bottomSheetDialog.show();

            }
        });

        ninethImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(CompleteProfile.this);
                View botomSjeetDialogview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_picture,
                        (LinearLayout)findViewById(R.id.picture));
                bottomSheetDialog.setContentView(botomSjeetDialogview);
                bottomSheetDialog.show();

            }
        });
        wipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompleteProfile.this, finalProfile.class);
                startActivity(intent);
            }
        });
    }
}