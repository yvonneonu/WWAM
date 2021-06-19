package com.example.waam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RequestFriendAdapter extends RecyclerView.Adapter<RequestFriendAdapter.FriendAdapterHolder> {

    private final List<WaamUser> requestFriendList;
    private Context context;
    private FriendAdderListener friendAdderListener;

    public RequestFriendAdapter(List<WaamUser> requestFriendList, Context context) {
        this.requestFriendList = requestFriendList;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.requestfriendview, parent, false);
        return new FriendAdapterHolder(view,friendAdderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapterHolder holder, int position) {

        WaamUser friend = requestFriendList.get(position);
        holder.name.setText(friend.getFullname());
        Glide.with(context)
                .asBitmap()
                .fitCenter()
                .circleCrop()
                .load(friend.getImageUrl())
                .into(holder.imageView);
    }


    public void friendrequestListAdderOrRemover(FriendAdderListener friendAdderListener){
        this.friendAdderListener = friendAdderListener;
    }

    @Override
    public int getItemCount() {
        return requestFriendList.size();
    }

    public static class FriendAdapterHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Button buttonAccep, buttonDecline;
        TextView name;
        public FriendAdapterHolder(@NonNull View itemView,FriendAdderListener friendAdderListener) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.imageView45);
            buttonAccep = itemView.findViewById(R.id.button17);
            buttonDecline = itemView.findViewById(R.id.button16);
            name = itemView.findViewById(R.id.textView108name);

            buttonAccep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(friendAdderListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            friendAdderListener.addFriend(position);
                        }
                    }
                }
            });

            buttonDecline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(friendAdderListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            friendAdderListener.removeFriend(position);
                        }
                    }
                }
            });


        }
    }


    interface FriendAdderListener{
        void addFriend(int pos);
        void removeFriend(int pos);
    }
}
