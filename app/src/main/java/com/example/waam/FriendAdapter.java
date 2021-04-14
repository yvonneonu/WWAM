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

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.Viewholder>{
    List<ModelImages> modelImagesList;
    Context context;
    OnfriendListener onfriendListener;

    public FriendAdapter(List<ModelImages> modelImagesList, Context context) {
        this.modelImagesList = modelImagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newfriendslide, parent, false);
        return new FriendAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.Viewholder holder, int position) {
        ModelImages images = modelImagesList.get(position);

        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(images.getImage())
                .into(holder.imageView);
        holder.textView.setText(images.getName());
    }

    @Override
    public int getItemCount() {
        return modelImagesList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;

        public Viewholder(@NonNull View itemView) {
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
                            onfriendListener.OnFriendClick(position);
                        }
                    }
               //     if (onFriendListerne)
                }
            });

        }

    }
    public interface OnfriendListener{
        void OnFriendClick(int poaition);
    }
    public void OnFriendMethod(OnfriendListener onfriendListener){
        this.onfriendListener = onfriendListener;
    }
}

