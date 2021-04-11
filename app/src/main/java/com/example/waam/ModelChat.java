package com.example.waam;

public class ModelChat {
    private int images;
    private String names;
    private String chatSms;
    private String time;

    public ModelChat(int images, String names, String chatSms, String time) {
        this.images = images;
        this.names = names;
        this.chatSms = chatSms;
        this.time = time;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getChatSms() {
        return chatSms;
    }

    public void setChatSms(String chatSms) {
        this.chatSms = chatSms;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
