package com.example.waam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private List<Post> postList = new ArrayList<>();
    private PostListener postListener;

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postview, parent, false);
        return new PostHolder(view,postListener) ;
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


    public void deletePost(PostListener postListener){
        this.postListener = postListener;
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public PostHolder(@NonNull View itemView,PostListener postListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.delete);
            textView = itemView.findViewById(R.id.post);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(postListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            postListener.onPostListener(position);
                        }
                    }
                }
            });
        }
    }


    public interface PostListener{
        void onPostListener(int position);
    }
}
