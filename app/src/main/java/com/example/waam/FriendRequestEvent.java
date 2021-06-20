package com.example.waam;

public class FriendRequestEvent extends NotificationActions{
    private String eventType;

    public FriendRequestEvent() {
        this.eventType = "friendRequest";
    }

    @Override
    String getEventType() {
       return eventType;
    }

    @Override
    void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
