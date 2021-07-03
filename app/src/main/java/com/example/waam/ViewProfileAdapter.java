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

public class ViewProfileAdapter extends RecyclerView.Adapter<ViewProfileAdapter.ViewHolder> {

    private List<Location> locations;
    private Context context;
    SearchListener searchFriendsListener;

    public ViewProfileAdapter(List<Location> locations, Context context){
        this.locations = locations;
        this.context = context;

    }
    @NonNull
    @Override
    public ViewProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.latestmatche, parent, false);

        return new ViewProfileAdapter.ViewHolder(view);
    }

    public void onLocationListerner(SearchListener searchFriendsListener){
        this.searchFriendsListener = searchFriendsListener;

    }
    @Override
    public void onBindViewHolder(@NonNull ViewProfileAdapter.ViewHolder holder, int position) {

        Location location = locations.get(position);
        holder.textView.setText(location.getName());
        Glide.with(context)
                .asBitmap()
                .fitCenter()
                .circleCrop()
                .load(location.getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.name);

          LinearLayout linearLayout = itemView.findViewById(R.id.lin);

            linearLayout.setOnClickListener(v -> {
                if (searchFriendsListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        searchFriendsListener.selectMatch(position);
                    }
                }
            });
        }
    }
    interface SearchListener{
        void selectMatch(int pos);
    }
}
