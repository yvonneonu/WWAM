package com.example.waam;

import java.io.Serializable;

public class AgentModel implements Serializable {
    private int image;
    private String name;
    private String rating;
    private String rating1;


    public AgentModel(int image, String name, String rating, String rating1) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.rating1 = rating1;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating1() {
        return rating1;
    }


}
