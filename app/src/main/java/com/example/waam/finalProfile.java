package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class finalProfile extends AppCompatActivity {

    private TextView textView;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_profile);

        String imageUri = getIntent().getStringExtra("image");
        image = findViewById(R.id.imageView12);


       Glide.with(this)
                .asBitmap()
                .circleCrop()
                .load(Uri.parse(imageUri))
                .into(image);


        textView = findViewById(R.id.textView34);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalProfile.this, LookingFor.class);
                intent.putExtra("images", imageUri.toString());
                startActivity(intent);
            }
        });
    }
}