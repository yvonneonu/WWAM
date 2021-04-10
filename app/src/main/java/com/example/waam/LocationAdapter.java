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

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationHolder>{

    List<Location> locationList;
    Context context;

    public LocationAdapter(List<Location> locationList, Context context){
        this.locationList = locationList;
        this.context = context;
    }
    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationspec,parent,false);

        return new LocationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {

        Location location = locationList.get(position);
        holder.textView.setText(location.getName());
        Glide.with(context)
                .asBitmap()
                .centerInside()
                .load(location.getImageUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        public LocationHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.name);
        }
    }
}
