package com.example.waam;

public abstract class NotificationActions {
    private String eventType;
    private String senderId;
    private String receiverId;
    private WaamUser waamUser;
    private String notificationId;
    abstract String getEventType();

    abstract void setEventType(String eventType);

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
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

    public WaamUser getWaamUser() {
        return waamUser;
    }

    public void setWaamUser(WaamUser waamUser) {
        this.waamUser = waamUser;
    }
}
