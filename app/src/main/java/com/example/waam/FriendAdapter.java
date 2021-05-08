package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewholderFake>{
    List<WaamUser> modelImagesList;
    Context context;
    OnfriendListener onfriendListener;

    public FriendAdapter(List<WaamUser> modelImagesList, Context context) {
        this.modelImagesList = modelImagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewholderFake onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newfriendslide, parent, false);
        return new FriendAdapter.ViewholderFake(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.ViewholderFake holder, int position) {
        WaamUser images = modelImagesList.get(position);

        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(images.getImageUrl())
                .into(holder.imageView);
        holder.textView.setText(images.getFullname());
    }

    @Override
    public int getItemCount() {
        return modelImagesList.size();
    }



    public void onFriendMethod(OnfriendListener onfriendListener){
        this.onfriendListener = onfriendListener;
    }

    public class ViewholderFake extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;

        public ViewholderFake(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView27);
            textView = itemView.findViewById(R.id.namemessa);
            constraintLayout = itemView.findViewById(R.id.imageee);

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onfriendListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            onfriendListener.onFriendClick(position);
                        }
                    }

                }
            });

        }

    }



    interface OnfriendListener{
        void onFriendClick(int position);
    }

}

