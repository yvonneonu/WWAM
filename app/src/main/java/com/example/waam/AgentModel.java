package com.example.waam;

import java.io.Serializable;

public class AgentModel implements Serializable {
    private int image;
    private String name;
    private String rating;
    private String rating1;
    private int image1;
    private String name1;
    private String rating2;
    private String rating3;

    public AgentModel(int image, String name, String rating, String rating1, int image1, String name1, String rating2, String rating3) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.rating1 = rating1;
        this.image1 = image1;
        this.name1 = name1;
        this.rating2 = rating2;
        this.rating3 = rating3;
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

    public void setRating1(String rating1) {
        this.rating1 = rating1;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getRating2() {
        return rating2;
    }

    public void setRating2(String rating2) {
        this.rating2 = rating2;
    }

    public String getRating3() {
        return rating3;
    }

    public void setRating3(String rating3) {
        this.rating3 = rating3;
    }
}
