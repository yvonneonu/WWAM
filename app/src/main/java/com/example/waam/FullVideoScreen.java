package com.example.waam;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class FullVideoScreen extends AppCompatActivity {

    SimpleExoPlayer exoPlayer;
    ProgressBar progressBar;
    SimpleExoPlayerView exoPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //progressBar = findViewById(R.id.progress);
        VideoPicModel videoPicModel = (VideoPicModel) getIntent().getSerializableExtra("picvic");
        if (videoPicModel.isVideo()) {

            setContentView(R.layout.activity_full_video_screen);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//            exoPlayerView = findViewById(R.id.videoView2);
//            progressBar = findViewById(R.id.progress_bar);
//
//            try {
//            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
//
//            exoPlayer = ExoPlayerFactory.newSimpleInstance(FullVideoScreen.this, trackSelector);
//            ExoPlayer.EventListener eventListener = new ExoPlayer.EventListener() {
//                @Override
//                public void onTimelineChanged(Timeline timeline, Object manifest) {
//
//                }
//
//                @Override
//                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
//
//                }
//
//                @Override
//                public void onLoadingChanged(boolean isLoading) {
//
//                }
//
//                @Override
//                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//
//                    if (playbackState == ExoPlayer.STATE_BUFFERING){
//                        progressBar.setVisibility(View.VISIBLE);
//                    } else {
//                        progressBar.setVisibility(View.GONE);
//                    }
//                }
//
//                @Override
//                public void onPlayerError(ExoPlaybackException error) {
//
//                }
//
//                @Override
//                public void onPositionDiscontinuity() {
//
//                }
//
//                @Override
//                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
//
//                }
//            };
//            Uri videouri = Uri.parse(videoPicModel.getVideoPicUrl());
//            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
//            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
//            MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
//
//            exoPlayerView.setPlayer(exoPlayer);
//
//            exoPlayer.prepare(mediaSource);
//
//
//            exoPlayer.setPlayWhenReady(true);
//        }catch (Exception e){
//                Log.d("TAG", "Error : " + e.toString());
//            }
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

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            PhotoView imageView = findViewById(R.id.imageView75);
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