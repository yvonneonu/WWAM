package com.example.waam;

public class NotificationActions extends WaamUser {
    boolean invite;
    boolean friendRequest;
    boolean friendAccepted;

    public boolean isInvite() {
        return invite;
    }

    public void setInvite(boolean invite) {
        this.invite = invite;
    }

    public boolean isFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(boolean friendRequest) {
        this.friendRequest = friendRequest;
    }

    public boolean isFriendAccepted() {
        return friendAccepted;
    }

    public void setFriendAccepted(boolean friendAccepted) {
        this.friendAccepted = friendAccepted;
    }
}
