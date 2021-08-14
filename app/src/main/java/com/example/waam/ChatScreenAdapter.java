package com.example.waam;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.connectycube.chat.model.ConnectycubeChatMessage;
import com.connectycube.users.model.ConnectycubeUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ChatScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static  final int RIGHT = 1;
    private static  final int LEFT = 0;
    private final List<ConnectycubeChatMessage> listOfChats;
    private final Context context;
    private final String receiversPic;

    public ChatScreenAdapter(List<ConnectycubeChatMessage> chatHolder, Context context, String receiversPic) {
        listOfChats = chatHolder;
        this.context = context;
        this.receiversPic = receiversPic;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if(viewType == RIGHT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sendersview,parent,false);
            return new Senderview(view);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiversview,parent,false);
        return new ReceiverView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ConnectycubeChatMessage chat = listOfChats.get(position);
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ConnectycubeUser connectycubeUser = new ConnectycubeUser();
        if(connectycubeUser != null){

            if(chat.getSenderId().equals(connectycubeUser.getId())){
                Senderview senderView = (Senderview) holder;
                //senderView.textView.setText(chat.getMessage());

                Glide.with(context)
                        .asBitmap()
                        .load(R.drawable.like_save_icon)
                        .into(senderView.emoji);
            }else{
                final ReceiverView receiverView = (ReceiverView) holder;
              //  receiverView.textView.setText(chat.getMessage());
                Glide.with(context)
                        .asBitmap()
                        .placeholder(R.drawable.profile_img_user)
                        .circleCrop()
                        .load(receiversPic)
                        .into(receiverView.imageView);

                Glide.with(context)
                        .asBitmap()
                        .load(R.drawable.like_save_icon)
                        .into(receiverView.emojiButton);
            }

        }


    }

    @Override
    public int getItemViewType(int position) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        if(listOfChats.get(position).getSenderId().equals(user.getUid())){
            return RIGHT;
        }
        return LEFT;

    }

    @Override
    public int getItemCount() {
        return listOfChats.size();
    }


    public static class Senderview extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        ImageView emoji;

        public Senderview(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.senderText);
            imageView = itemView.findViewById(R.id.circleimage1);
            emoji = itemView.findViewById(R.id.emojiButton);

        }
    }

    public static class ReceiverView extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        ImageView emojiButton;

        public ReceiverView(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.circleimage);
            textView = itemView.findViewById(R.id.receiverText);
            emojiButton = itemView.findViewById(R.id.like);

        }
    }
}
