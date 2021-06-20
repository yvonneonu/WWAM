package com.example.waam;

public class FriendInviteEvent extends NotificationActions {

    private final String eventType;

    public FriendInviteEvent() {
        this.eventType = "friendinvite";
    }

    @Override
    String getEventType() {
        return eventType;
    }

    @Override
    void setEventType(String eventType) {

    }
}
