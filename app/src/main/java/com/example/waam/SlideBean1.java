package com.example.waam;

public class SlideBean1 {
    private int image;
    private String nameNage;
    private String location;

    public SlideBean1(int image, String nameNage, String location) {
        this.image = image;
        this.nameNage = nameNage;
        this.location = location;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameNage() {
        return nameNage;
    }

    public void setNameNage(String nameNage) {
        this.nameNage = nameNage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
