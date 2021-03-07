package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CompleteProfile extends AppCompatActivity {
    private ImageView firstImage, secondImage,thirdImage, fourthImage, fivethImage, sixthImage, seventhImage, eightImage, ninethImage, photo, gallerysave;;
    private TextView wipe;
    private ImageView image;

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
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"TAG");

                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        Glide.with(CompleteProfile.this)
                                .asBitmap()
                                .load(uri)
                                .into(firstImage);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        secondImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"TAG");

                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        Glide.with(CompleteProfile.this)
                                .asBitmap()
                                .load(uri)
                                .into(secondImage);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        thirdImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        Glide.with(CompleteProfile.this)
                                .asBitmap()
                                .load(uri)
                                .into(thirdImage);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        fourthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        Glide.with(CompleteProfile.this)
                                .asBitmap()
                                .load(uri)
                                .into(fourthImage);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        fivethImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        Glide.with(CompleteProfile.this)
                                .asBitmap()
                                .load(uri)
                                .into(fivethImage);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        sixthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        Glide.with(CompleteProfile.this)
                                .asBitmap()
                                .load(uri)
                                .into(sixthImage);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        seventhImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        Glide.with(CompleteProfile.this)
                                .asBitmap()
                                .load(uri)
                                .into(seventhImage);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        eightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        Glide.with(CompleteProfile.this)
                                .asBitmap()
                                .load(uri)
                                .into(eightImage);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        ninethImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        Glide.with(CompleteProfile.this)
                                .asBitmap()
                                .load(uri)
                                .into(ninethImage);
                        bottomSheet.dismiss();
                    }
                });
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