package com.example.waam;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        public EventHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
