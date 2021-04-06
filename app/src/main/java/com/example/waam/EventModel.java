package com.example.waam;

public class EventModel {
    private String title;
    private int stars;
    private int rating;
    private int currentPrice;
    private int oldPrice;
    private int image;

    public EventModel(String title, int image, int rating, int oldPrice, int currentPrice){
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.oldPrice = oldPrice;
        this.currentPrice = currentPrice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }
}
