package com.example.waam;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class FullVideoScreen extends AppCompatActivity {

    SimpleExoPlayer exoPlayer;
    ProgressBar progressBar;
    SimpleExoPlayerView exoPlayerView;

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

    public void clear(View view) {
        finish();
    }
}