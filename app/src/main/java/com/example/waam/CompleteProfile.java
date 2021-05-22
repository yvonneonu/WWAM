package com.example.waam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class CompleteProfile extends AppCompatActivity {
    private ImageView firstImage, secondImage,thirdImage, fourthImage, fivethImage, sixthImage, seventhImage, eightImage, ninethImage, photo, gallerysave;;
    private TextView wipe, name;
    private ImageView image, imagefirst, imagesecond, imagethird, imagefourth, imagefifth, imagesixth, imageseveth, imageeight, profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        String imageUri = getIntent().getStringExtra("getProfilePics");


//        Log.d("Complete",imageUri);
        String Fullname = getIntent().getStringExtra("name");
        String tired = getIntent().getStringExtra("token");

        firstImage = findViewById(R.id.imageView0);
        secondImage = findViewById(R.id.imageView1);
        thirdImage = findViewById(R.id.imageView2);
        fourthImage = findViewById(R.id.imageView3);
        fivethImage= findViewById(R.id.imageView4);
        sixthImage = findViewById(R.id.imageView5);
        seventhImage = findViewById(R.id.imageView6);
        eightImage = findViewById(R.id.imageView7);
        ninethImage = findViewById(R.id.imageView8);
        image = findViewById(R.id.image);
        profile = findViewById(R.id.imageView12);
        imagefirst = findViewById(R.id.image1);
        imagesecond = findViewById(R.id.image2);
        imagethird = findViewById(R.id.image3);
        imagefourth = findViewById(R.id.image4);
        imagefifth = findViewById(R.id.image5);
        imagesixth = findViewById(R.id.image6);
        imageseveth = findViewById(R.id.image7);
        imageeight = findViewById(R.id.image8);
        wipe = findViewById(R.id.swipe);
        name = findViewById(R.id.textView19);


        if (imageUri != null) {
            Glide.with(this)
                    .asBitmap()
                    .circleCrop()
                    .load(Uri.parse(imageUri))
                    .into(profile);
        }
       // name.setText(Fullname);
        name.setText(SharedPref.getInstance(this).getStoredName());


        image.setOnClickListener(new View.OnClickListener() {
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
                                .into(image);
                        firstImage.setVisibility(View.GONE);
                        LinearLayout linearLayout = findViewById(R.id.linearLayout);
                        linearLayout.setVisibility(View.GONE);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        imagefirst.setOnClickListener(new View.OnClickListener() {
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
                                .into(imagefirst);
                        Log.d("Bed", "mad");
                        secondImage.setVisibility(View.GONE);
                        Log.d("Bedy", "mad");
                        LinearLayout linearLayout1 = findViewById(R.id.linearLayout1);
                        TextView view1 = findViewById(R.id.text1);
                        view1.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.INVISIBLE);
                        Log.d("Bedm", "mad");
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        imagesecond.setOnClickListener(new View.OnClickListener() {
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
                                .into(imagesecond);
                        thirdImage.setVisibility(View.GONE);
                        LinearLayout linearLayout = findViewById(R.id.linearLayout2);
                        linearLayout.setVisibility(View.GONE);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        imagethird.setOnClickListener(new View.OnClickListener() {
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
                                .into(imagethird);
                       fourthImage.setVisibility(View.GONE);
                        LinearLayout linearLayout = findViewById(R.id.linearLayout3);
                        linearLayout.setVisibility(View.GONE);
                        bottomSheet.dismiss();

                    }
                });
            }
        });

        imagefourth.setOnClickListener(new View.OnClickListener() {
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
                                .into(imagefourth);
                        fivethImage.setVisibility(View.GONE);
                        LinearLayout linearLayout = findViewById(R.id.linearLayout4);
                        linearLayout.setVisibility(View.GONE);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

       imagefifth.setOnClickListener(new View.OnClickListener() {
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
                                .into(imagefifth);
                        bottomSheet.dismiss();
                        sixthImage.setVisibility(View.GONE);
                        LinearLayout linearLayout = findViewById(R.id.linearLayout5);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        });

        imagesixth.setOnClickListener(new View.OnClickListener() {
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
                                .into(imagesixth);

                        seventhImage.setVisibility(View.GONE);
                        LinearLayout linearLayout = findViewById(R.id.linearLayout6);
                        linearLayout.setVisibility(View.GONE);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        imageseveth.setOnClickListener(new View.OnClickListener() {
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
                                .into(imageseveth);
                        eightImage.setVisibility(View.GONE);
                        LinearLayout linearLayout = findViewById(R.id.linearLayout7);
                        linearLayout.setVisibility(View.GONE);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

       imageeight.setOnClickListener(new View.OnClickListener() {
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
                                .into(imageeight);
                        ninethImage.setVisibility(View.GONE);
                        LinearLayout linearLayout = findViewById(R.id.linearLayout8);
                        linearLayout.setVisibility(View.GONE);
                        bottomSheet.dismiss();
                    }
                });
            }
        });

        wipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompleteProfile.this, finalProfile.class);
                if (imageUri != null){
                    intent.putExtra("image", imageUri.toString());
                }

                if (tired != null){
                    intent.putExtra("everytoken", tired);
                }

                intent.putExtra("name", Fullname);
                Log.d("TAG", ""+Fullname);

                Log.d("TAG", "TOKENSHOW7 " +tired);
                startActivity(intent);
            }
        });

    }
}