package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

public class NoeventAdapter extends RecyclerView.Adapter<NoeventAdapter.Viewholder> {
    Context context;

    public NoeventAdapter(Context context){
        this.context = context;

    }
    @NonNull
    @Override
    public NoeventAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noeventimage, parent, false);
        return new NoeventAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoeventAdapter.Viewholder holder, int position) {

        //holder.cardView.getCardBackgroundColor();
       // holder.imageView.getDrawable();
        holder.textView.getText();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        ImageView imageView;
        TextView textView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.test);


        }
    }
}
