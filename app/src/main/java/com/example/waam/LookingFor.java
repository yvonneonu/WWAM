package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class LookingFor extends AppCompatActivity {

    private TextView textView;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_for);
        textView = findViewById(R.id.textView34);
        image = findViewById(R.id.imageView12);

        String imageUri = getIntent().getStringExtra("image");


        Glide.with(this)
                .asBitmap()
                .circleCrop()
                .load(Uri.parse(imageUri))
                .into(image);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LookingFor.this, DrawelayoutActivity.class);
                startActivity(intent);
            }
        });
    }
}