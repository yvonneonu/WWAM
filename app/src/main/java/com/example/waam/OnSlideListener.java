package com.example.waam;

import androidx.recyclerview.widget.RecyclerView;

public interface OnSlideListener<T>{
    void onSliding(RecyclerView.ViewHolder viewHolder, float ratio, int direction);

    void onSlided(RecyclerView.ViewHolder viewHolder, T t, int direction);

    void onClear();

}
