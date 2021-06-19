package com.example.waam;

public class NotificationActions {
    private boolean invite;
    private boolean friendRequest;
    private boolean friendAccepted;
    private WaamUser waamUser;

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

    public WaamUser getWaamUser() {
        return waamUser;
    }

    public void setWaamUser(WaamUser waamUser) {
        this.waamUser = waamUser;
    }
}
