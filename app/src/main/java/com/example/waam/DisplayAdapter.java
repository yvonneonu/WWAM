package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.Viewholder> {
    List<DispalyInterest> dispalyInterests;
    Context context;

    public DisplayAdapter(List<DispalyInterest> displayAdapters, Context context){
        this.dispalyInterests = displayAdapters;
        this.context = context;
    }

    @NonNull
    @Override
    public DisplayAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventimage, parent, false);
        return new DisplayAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayAdapter.Viewholder holder, int position) {
        DispalyInterest dispalyInterest = dispalyInterests.get(position);

        Glide.with(context)
                .asBitmap()
                .centerCrop()
                .load(dispalyInterest.getImage())
                .into(holder.imageView);
        holder.textView.setText(dispalyInterest.getName());


    }

    @Override
    public int getItemCount() {
        return dispalyInterests.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.name);

        }
    }
}
