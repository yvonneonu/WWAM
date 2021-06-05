package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationHolder> {

    private final List<Location> locationList;
    private final Context context;
    private LocationListener locationListener;

    public LocationAdapter(List<Location> locationList, Context context) {
        this.locationList = locationList;
        this.context = context;
    }

    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationspec, parent, false);

        return new LocationHolder(view);
    }


    public void onLocationListener(LocationListener locationListener) {
        this.locationListener = locationListener;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {

        Location location = locationList.get(position);
        holder.textView.setText(location.getName());
        Glide.with(context)
                .asBitmap()
                .centerInside()
                .load(location.getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public LocationHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.name);
            LinearLayout linearLayout = itemView.findViewById(R.id.lin);

            linearLayout.setOnClickListener(v -> {
                if (locationListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        locationListener.selectLocation(position);
                    }
                }
            });

        }
    }

    interface LocationListener {
        void selectLocation(int pos);
    }
}
