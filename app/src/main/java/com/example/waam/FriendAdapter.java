package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.Viewholder>{
    List<ModelImages> modelImagesList;
    Context context;

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
        holder.imageView.setImageResource(images.getImage());
        holder.textView.setText(images.getName());
    }

    @Override
    public int getItemCount() {
        return modelImagesList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView27);
            textView = itemView.findViewById(R.id.namemessa);

        }
    }
}

