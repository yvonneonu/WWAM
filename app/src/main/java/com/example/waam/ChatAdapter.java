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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Viewholder> {
    List<ModelChat> modelChatList;
    Context context;

    public ChatAdapter (List<ModelChat> modelChatList, Context context){
        this.modelChatList = modelChatList;
        this.context = context;

    }
    @NonNull
    @Override
    public ChatAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagefriendslide, parent, false);
        return new ChatAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.Viewholder holder, int position) {
        ModelChat chat = modelChatList.get(position);

        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(chat.getImages())
                .into(holder.imageView);
        holder.name.setText(chat.getNames());
        holder.message.setText(chat.getChatSms());
        holder.time.setText(chat.getTime());
    }

    @Override
    public int getItemCount() {
        return modelChatList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, message, time;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView30);
            name = itemView.findViewById(R.id.textView52);
            message = itemView.findViewById(R.id.textView53);
            time = itemView.findViewById(R.id.tv_ime);
        }
    }
}
