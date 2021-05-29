package com.example.waam;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class VideoPicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final List<VideoPicModel> videoPicList;
    private final Context context;
    private static  final int VIDEO = 1;
    private static  final int PICTURE = 0;

    public VideoPicAdapter(List<VideoPicModel> videoPicList, Context context) {
        this.videoPicList = videoPicList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        if(viewType == VIDEO){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videoview, parent, false);
            return new VideoPicAdapter.VideoHolder(view);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picview,parent,false);
        return new VideoPicAdapter.PicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VideoPicModel videoPicModel = videoPicList.get(position);

        if(videoPicModel.isVideo()){
            VideoHolder videoHolder = (VideoHolder) holder;

            VideoView videoView = videoHolder.videoView;
            MediaController mediaController= new MediaController(context);
            mediaController.setAnchorView(videoView);

            //specify the location of media file
            Uri uri=Uri.parse(videoPicModel.videoPicUrl);

            //Setting MediaController and URI, then starting the videoView
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();

        }else{
            PicHolder picHolder = (PicHolder) holder;
            Glide.with(context)
                    .asBitmap()
                    .load(videoPicModel.getVideoPicUrl())
                    .into(picHolder.imageView);
        }

    }

    @Override
    public int getItemViewType(int position) {

        if(videoPicList.get(position).isVideo()){
            return VIDEO;
        }
        return PICTURE;
    }

    @Override
    public int getItemCount() {
        return videoPicList.size();
    }

    public static class VideoHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
        }
    }

    public static class PicHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public PicHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView43);
        }
    }


}
