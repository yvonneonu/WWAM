package com.example.waam;

public class matchModel {
    private String imagematch;
    private String name1;

    public matchModel(String imagematch, String name1) {
        this.imagematch = imagematch;
        this.name1 = name1;
    }

    public String getImagematch() {
        return imagematch;
    }

    public void setImagematch(String imagematch) {
        this.imagematch = imagematch;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
}
