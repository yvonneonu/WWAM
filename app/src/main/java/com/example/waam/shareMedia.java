package com.example.waam;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class shareMedia extends AppCompatActivity {
    private ImageView imageView;
   private String image;
   private TextView goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_media);

        imageView = findViewById(R.id.textView12);
        goback =findViewById(R.id.go);


       String uri = getIntent().getStringExtra("image");

        Glide.with(shareMedia.this)
                .asBitmap()
                .load(Uri.parse(uri))
                .into(imageView);

      goback.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });



    }
}