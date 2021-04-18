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

public class FriendAdapt extends RecyclerView.Adapter<FriendAdapt.FriendHolder> {
    private final List<FriendModel> friendsContainer;
    private final Context context;
    public FriendAdapt(List<FriendModel> friendsContainer, Context context) {
        this.friendsContainer = friendsContainer;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendholderview,parent,false);
        return new FriendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        FriendModel friendModel = friendsContainer.get(position);
        holder.firstname.setText(friendModel.getFirstname());
        holder.lastname.setText(friendModel.getLastname());
        Glide.with(context)
                .asBitmap()
                .load(friendModel.getImage())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return friendsContainer.size();
    }

    public static class FriendHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView firstname, lastname;
        public FriendHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView35);
            firstname = itemView.findViewById(R.id.textView84);
            lastname = itemView.findViewById(R.id.textView87);
        }
    }
}
