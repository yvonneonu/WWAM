package com.example.waam;

public class EventUserPost {
    private String user_id;
    private String event_id;

    public EventUserPost(String user_id, String event_id) {
        this.user_id = user_id;
        this.event_id = event_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }
}
