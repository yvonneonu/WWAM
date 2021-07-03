package com.example.waam.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waam.Location;
import com.example.waam.R;

import java.util.List;

public class ViewEventAdapter extends RecyclerView.Adapter<ViewEventAdapter.ViewHolder> {

    private List<Location> eventList;
    private Context context;
    EventSearchListener eventSearchListener;

    public ViewEventAdapter (List<Location> eventList, Context context){
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewevent, parent, false);
        return new  ViewEventAdapter.ViewHolder(view);
    }

    public void onEventListerner(EventSearchListener eventSearchListener){
        this.eventSearchListener = eventSearchListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewEventAdapter.ViewHolder holder, int position) {

        Location location = eventList.get(position);
        holder.textView.setText(location.getName());
        Glide.with(context)
                .asBitmap()
              //  .fitCenter(
                .load(location.getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image1);
            textView = itemView.findViewById(R.id.name1);
            CardView linearLayout = itemView.findViewById(R.id.lin1);

            linearLayout.setOnClickListener(v -> {
                if (eventSearchListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        eventSearchListener.eventMatch(position);
                    }
                }
            });

        }
    }
    interface EventSearchListener{
        void eventMatch(int pos);
    }
}
