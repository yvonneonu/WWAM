package com.example.waam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.List;

public class RecentChatsAdapt extends RecyclerView.Adapter<RecentChatsAdapt.RecentView> {
    List<WaamUser> waamUserList;
    Context context;
    GeneralFactory generalFactory;
    HashMap<String, String> lastMessage;
    OnChatListener onChatListener;

    public RecentChatsAdapt(List<WaamUser> waamUserList, Context context) {
        this.waamUserList = waamUserList;
        this.context = context;
        lastMessage = new HashMap<>();
        generalFactory = GeneralFactory.getGeneralFactory(context);
    }

    @NonNull
    @Override
    public RecentView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagechatadapterview,parent,false);
        return new RecentView(view,onChatListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentView holder, int position) {

        WaamUser waamUser = waamUserList.get(position);
        Log.d("sernam",""+position);
        String fullNam = waamUser.getFullname();
        holder.fullName.setText(fullNam);

        String senderId = FirebaseAuth.getInstance().getUid();
        String receiverId = waamUser.getUid();

        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .placeholder(R.drawable.profile_img_user)
                .load(waamUser.getImageUrl())
                .into(holder.imageView);


        generalFactory.loadLastMessage(senderId,receiverId,holder.lastMess);


        if(waamUser.getOnlineStatus().equals("online")){
            //show dat they are online
        }else{
            //They are not online;
        }
    }

    public void chatMethod(OnChatListener onChatListener){
        this.onChatListener = onChatListener;
    }
    @Override
    public int getItemCount() {
        return waamUserList.size();
    }

    public static class RecentView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView fullName;
        TextView lastMess;

        public RecentView(@NonNull View itemView,OnChatListener onChatListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView39);
            fullName = itemView.findViewById(R.id.textView100);
            lastMess = itemView.findViewById(R.id.textView101);

            itemView.setOnClickListener(v -> {
                if(onChatListener != null ){
                    int pos = getAdapterPosition();
                    Log.d("Adapter",""+pos);
                    if(pos != RecyclerView.NO_POSITION){
                        onChatListener.OnChatClick(pos);
                    }
                }
            });

        }
    }

    public interface OnChatListener{
        void OnChatClick(int position);
    }


}
