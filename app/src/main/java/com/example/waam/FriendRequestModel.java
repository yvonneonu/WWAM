package com.example.waam;

public class FriendRequestModel {
   public String sender_id;
   public String receiver_id;

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public FriendRequestModel(String sender_id, String receiver_id) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;


    }
}
