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

import java.util.List;

public class ChatScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static  final int RIGHT = 1;
    private static  final int LEFT = 0;
    private final List<Chat> listOfChats;
    private final Context context;
    private String senderId;
    private String recieverId;

    public ChatScreenAdapter(List<Chat> chatHolder, Context context) {
        this.listOfChats = chatHolder;
        this.context = context;
        senderId = "yvonne";
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == RIGHT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sendersview, parent, false);
            return new Senderview(view);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiversview, parent, false);
        return new ReceiverView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Chat chat = listOfChats.get(position);
        if(chat.getSenderId().equals(senderId)){
            Senderview  senderView = (Senderview) holder;
            senderView.textView.setText(chat.getMessage());
        }else{

            final ReceiverView receiverView = (ReceiverView) holder;
            receiverView.textView.setText(chat.getMessage());
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.profile_img_user)
                    .circleCrop()
                    .into(receiverView.imageView);

        }
       /* if(chat.getSenderId().equals(user.getUid())){
            SenderView senderView = (SenderView) holder;
            senderView.textView.setText(chat.getMessage());
        }else{
            final ReceiverView receiverView = (ReceiverView) holder;
            receiverView.txt.setText(chat.getMessage());
            String receiverpic = chat.getSenderId();
            AgroAppRepo.getInstanceOfAgroApp().loadSpecUser(receiverpic, new AgroAppRepo.SpecificUser() {
                @Override
                public void loadSpecUse(User user) {
                    Glide.with(context)
                            .asBitmap()
                            .placeholder(R.drawable.doctor)
                            .circleCrop()
                            .load(Uri.parse(user.getImage()))
                            .into(receiverView.imageView);
                }
            });*/




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
