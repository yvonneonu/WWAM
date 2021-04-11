package com.example.waam;

public class GetImageResponse {
    private String picture;

    public GetImageResponse(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
