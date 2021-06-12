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

public class EventDisplayAdapter extends RecyclerView.Adapter<EventDisplayAdapter.Viewholder> {
    List<UserResult> userResults;
    Context context;

    public EventDisplayAdapter(List<UserResult> userResults, Context context){
        this.userResults = userResults;
        this.context = context;

    }
    @NonNull
    @Override
    public EventDisplayAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventimage, parent);
        return new EventDisplayAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventDisplayAdapter.Viewholder holder, int position) {

        UserResult userResult = userResults.get(position);
        Glide.with(context)
                .asBitmap()
                .centerCrop()
                .load(userResult.getPhoto())
                .into(holder.imageView);
        holder.textView.setText(userResult.getTitle());
    }

    @Override
    public int getItemCount() {
        return userResults.size();
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
