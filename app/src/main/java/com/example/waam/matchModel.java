package com.example.waam;

public class matchModel {
    private int imagematch;
    private String name1;
    private String state;

    public matchModel(int imagematch, String name1, String state) {
        this.imagematch = imagematch;
        this.name1 = name1;
        this.state = state;
    }

    public int getImagematch() {
        return imagematch;
    }

    public void setImagematch(int imagematch) {
        this.imagematch = imagematch;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
