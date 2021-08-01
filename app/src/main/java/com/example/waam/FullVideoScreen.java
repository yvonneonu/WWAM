package com.example.waam;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FullVideoScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VideoPicModel videoPicModel = (VideoPicModel) getIntent().getSerializableExtra("picvic");
        if(videoPicModel.isVideo()){
            setContentView(R.layout.activity_full_video_screen);
            VideoView videoView = findViewById(R.id.videoView2);
            MediaController mediaController= new MediaController(this);
            mediaController.setAnchorView(videoView);
            //specify the location of media file
            Uri uri=Uri.parse(videoPicModel.getVideoPicUrl());

            //Setting MediaController and URI, then starting the videoView
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }else{
            setContentView(R.layout.showfullpic);
            ImageView imageView = findViewById(R.id.imageView75);
            Glide.with(this)
                    .asBitmap()
                    .load(videoPicModel.getVideoPicUrl())
                    .into(imageView);
        }

    }
}