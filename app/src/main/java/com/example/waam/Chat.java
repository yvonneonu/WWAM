package com.example.waam;

import java.io.Serializable;

public class Chat implements Serializable {
    private String message;
    private String chatId;
    private String senderId;
    private String receiverId;
    private String senderEmoji;
    private String receiverEmoji;

    public Chat(String message, String chatId, String senderId, String receiverId) {
        this.message = message;
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
