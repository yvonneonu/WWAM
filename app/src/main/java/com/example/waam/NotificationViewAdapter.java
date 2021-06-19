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
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class NotificationViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int FRIENDREQUEST = 0;
    private static final int INVITE = 1;
    private static final int FRIENDSACCEPTED = 2;
    private AcceptOrDeny acceptOrDeny;
    private final List<NotificationActions> notificationActionsList;
    private final Context context;

    public NotificationViewAdapter(List<NotificationActions> notificationActionsList, Context context) {
        this.notificationActionsList = notificationActionsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == FRIENDREQUEST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.requestfriendview, parent, false);
            return new FriendRequest(view,acceptOrDeny);
        } else if (viewType == INVITE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendsinvite, parent, false);
            return new FriendInvite(view);
        } else if (viewType == FRIENDSACCEPTED) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendacceptedview, parent, false);
            return new FriendAccepted(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NotificationActions user = notificationActionsList.get(position);
        WaamUser waamUseruser = user.getWaamUser();
        String myId = FirebaseAuth.getInstance().getUid();
        if(user.isInvite()){
            FriendInvite invite = (FriendInvite) holder;

            Glide.with(context)
                    .asBitmap()
                    .circleCrop()
                    .fitCenter()
                    .load(waamUseruser.getImageUrl())
                    .into(invite.imageViewone);

            invite.textView.setText(waamUseruser.getFullname());
            GeneralFactory.getGeneralFactory(context)
                    .loadSpecUser(myId, new GeneralFactory.SpecificUser() {
                        @Override
                        public void loadSpecUse(WaamUser user) {
                            Glide.with(context)
                                    .asBitmap()
                                    .circleCrop()
                                    .fitCenter()
                                    .load(user.getImageUrl())
                                    .into(invite.imageViewone);
                        }
                    });
        }else if(user.isFriendAccepted()){
            FriendAccepted accepted = (FriendAccepted) holder;
            accepted.textViewname.setText(waamUseruser.getFullname());
            Glide.with(context)
                    .asBitmap()
                    .fitCenter()
                    .circleCrop()
                    .load(waamUseruser.getImageUrl())
                    .into(accepted.imageView);
        }else if(user.isFriendRequest()){

            FriendRequest friendRequest = (FriendRequest) holder;
            friendRequest.textView.setText(waamUseruser.getFullname());

        }

    }

    @Override
    public int getItemCount() {
        return notificationActionsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void acceptOrReject(AcceptOrDeny acceptOrDeny){
        this.acceptOrDeny = acceptOrDeny;
    }
    static class FriendInvite extends RecyclerView.ViewHolder {

        ImageView imageViewone, imageViewTwo;
        TextView textView;
        public FriendInvite(@NonNull View itemView) {
            super(itemView);
            imageViewone = itemView.findViewById(R.id.imageView46);
            imageViewTwo = itemView.findViewById(R.id.imageView47);
            textView = itemView.findViewById(R.id.textView110name);
        }
    }

    static class FriendRequest extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView deny, accept;
        public FriendRequest(@NonNull View itemView,AcceptOrDeny acceptOrDeny) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView108name);
            deny = itemView.findViewById(R.id.button16);
            accept = itemView.findViewById(R.id.button17);

            deny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(acceptOrDeny != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            acceptOrDeny.deny(position);
                        }
                    }

                }
            });

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(acceptOrDeny != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            acceptOrDeny.accept(position);
                        }
                    }
                }
            });
        }
    }

    static class FriendAccepted extends RecyclerView.ViewHolder {
        TextView textViewname;
        ImageView imageView;
        public FriendAccepted(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView48);
            textViewname = itemView.findViewById(R.id.textView111name);
        }
    }


    interface AcceptOrDeny{
        void accept(int pos);
        void deny(int pos);
    }
}
