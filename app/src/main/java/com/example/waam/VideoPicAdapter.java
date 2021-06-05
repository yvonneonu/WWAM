package com.example.waam;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
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
    private MediaListener mediaListener;

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
            return new VideoPicAdapter.VideoHolder(view,mediaListener);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picview,parent,false);
        return new VideoPicAdapter.PicHolder(view,mediaListener);
    }


    public void showPicVid(MediaListener mediaListener){
        this.mediaListener = mediaListener;
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
            Log.d("Damn","Y dis shit");
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
        ImageView imageView;
        public VideoHolder(@NonNull View itemView, MediaListener mediaListener) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            imageView = itemView.findViewById(R.id.imageView44);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaListener != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            mediaListener.mediaListener(pos);
                        }
                    }
                }
            });
        }
    }

    public static class PicHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public PicHolder(@NonNull View itemView, MediaListener mediaListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView43);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaListener != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            mediaListener.mediaListener(pos);
                        }
                    }
                }
            });
        }
    }

    public interface MediaListener{
        void mediaListener(int position);
    }

}
