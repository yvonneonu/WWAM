package com.example.waam;

public class EventResult {

    private int id;
    private String title;
    private String photo;
    private String short_description;

    public EventResult(int id, String title, String photo, String short_description) {
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.short_description = short_description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }
}
