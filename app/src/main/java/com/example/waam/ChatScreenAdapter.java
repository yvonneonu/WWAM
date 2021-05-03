package com.example.waam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.connectycube.chat.ConnectycubeRestChatService;
import com.connectycube.chat.model.ConnectycubeChatDialog;
import com.connectycube.chat.model.ConnectycubeDialogType;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.exception.ResponseException;

import java.util.ArrayList;
import java.util.List;

public class ChatScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static  final int RIGHT = 1;
    private static  final int LEFT = 0;
    private final List<Chat> listOfChats;
    private final Context context;
    private String senderId;
    private String recieverId;
    ConnectycubeChatDialog privateDialog;


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


        ArrayList<Integer> occupantIds = new ArrayList<Integer>();
        occupantIds.add(4134562);

        ConnectycubeChatDialog dialog = new ConnectycubeChatDialog();
        dialog.setType(ConnectycubeDialogType.PRIVATE);
        dialog.setOccupantsIds(occupantIds);

        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(dialog != null){

            if(chat.getSenderId().equals(dialog.getDialogId())){
                Senderview senderView = (Senderview) holder;
                senderView.textView.setText(chat.getMessage());
            }else{
                final ReceiverView receiverView = (ReceiverView) holder;
                receiverView.textView.setText(chat.getMessage());
                Glide.with(context)
                        .asBitmap()
                        .placeholder(R.drawable.profile_img_user)
                        .circleCrop()
                        .load(receiverView.imageView)
                        .into(receiverView.imageView);

            }

        }



//or just use DialogUtils
//ConnectycubeChatDialog dialog = DialogUtils.buildPrivateDialog(recipientId);

        ConnectycubeRestChatService.createChatDialog(dialog).performAsync(new EntityCallback<ConnectycubeChatDialog>() {
            @Override
            public void onSuccess(ConnectycubeChatDialog createdDialog, Bundle params) {

            }

            @Override
            public void onError(ResponseException exception) {

            }
        });

        // Provide chat connection configuration


       /* }else{

            final ReceiverView receiverView = (ReceiverView) holder;
            receiverView.textView.setText(chat.getMessage());
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.profile_img_user)
                    .circleCrop()
                    .into(receiverView.imageView);*/

       // }
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
