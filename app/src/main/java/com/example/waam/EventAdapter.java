package com.example.waam;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    List<EventModel> eventModelList;
    Context context;
    ResponToTouchListener responToTouchListener;

    public EventAdapter(List<EventModel> eventModelList,Context context) {
        this.eventModelList = eventModelList;
        this.context = context;
    }

    public void setOnTouch(ResponToTouchListener responToTouchListener){
        this.responToTouchListener = responToTouchListener;
    }
    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventlayoutspec,parent,false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        EventModel eventModel = eventModelList.get(position);
        holder.title.setText(eventModel.getTitle());
        holder.oldprice.setText(String.valueOf(eventModel.getOldPrice()));
        holder.newprice.setText(String.valueOf(eventModel.getCurrentPrice()));
        holder.oldprice.setPaintFlags(holder.oldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.rating.setText(String.valueOf(eventModel.getRating()));
        holder.ratingBar.setRating(eventModel.getStars());
        Glide.with(context)
                .asBitmap()
                .centerCrop()
                .load(eventModel.getImage())
                .into(holder.imageViewPlace);
    }

    @Override
    public int getItemCount() {
        return eventModelList.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPlace;
        TextView title, oldprice,newprice,rating;
        RatingBar ratingBar;
        MaterialCardView materialCardView;

        public EventHolder(@NonNull View itemView) {
            super(itemView);

            imageViewPlace = itemView.findViewById(R.id.imagebody);
            title = itemView.findViewById(R.id.titleofplace);
            oldprice = itemView.findViewById(R.id.old);
            newprice = itemView.findViewById(R.id.current);
            rating = itemView.findViewById(R.id.value);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            materialCardView = itemView.findViewById(R.id.card);

            materialCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(responToTouchListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            responToTouchListener.touchListener(position);
                        }
                    }
                }
            });
        }
    }


    interface ResponToTouchListener{
        public void touchListener(int position);
    }
}
