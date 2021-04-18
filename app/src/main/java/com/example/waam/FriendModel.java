package com.example.waam;

public class FriendModel {
    private String firstname;
    private String lastname;
    private int image;

    public FriendModel(String firstname, String lastname, int image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.image = image;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
