package com.example.waam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int FRIENDREQUEST = 0;
    private static final int INVITE = 1;
    private static final int FRIENDSACCEPTED = 2;

    private List<WaamUser> waamUserList;

    public NotificationViewAdapter(List<WaamUser> waamUserList) {
        this.waamUserList = waamUserList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == FRIENDREQUEST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.requestfriendview, parent, false);
            return new FriendRequest(view);
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
        WaamUser user = waamUserList.get(position);


    }

    @Override
    public int getItemCount() {
        return waamUserList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    static class FriendInvite extends RecyclerView.ViewHolder {

        public FriendInvite(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class FriendRequest extends RecyclerView.ViewHolder {
        public FriendRequest(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class FriendAccepted extends RecyclerView.ViewHolder {
        public FriendAccepted(@NonNull View itemView) {
            super(itemView);
        }
    }
}
