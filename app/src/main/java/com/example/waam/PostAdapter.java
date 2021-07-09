package com.example.waam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private List<Post> postList = new ArrayList<>();

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postview, parent, false);
        return new PostHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post post = postList.get(position);
        holder.textView.setText(post.getText());
    }

    public void setPost(List<Post> posts){
        this.postList = posts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.post);
        }
    }
}
