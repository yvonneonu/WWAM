package com.example.waam;

public class RequestFriend {
    private String receiverId;
    private String senderId;
    private boolean accepted;
    private WaamUser user;

    public RequestFriend() {
    }

    public RequestFriend(String senderId,WaamUser user) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.user = user;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public WaamUser getUser() {
        return user;
    }

    public void setUser(WaamUser user) {
        this.user = user;
    }
}
