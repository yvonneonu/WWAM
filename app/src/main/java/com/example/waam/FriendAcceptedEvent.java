package com.example.waam;

public class FriendAcceptedEvent extends NotificationActions {
    private final String eventType;

    public FriendAcceptedEvent() {
        this.eventType = "friendAccepted";
    }

    @Override
    String getEventType() {
        return eventType;
    }

    @Override
    void setEventType(String eventType) {

    }
}
