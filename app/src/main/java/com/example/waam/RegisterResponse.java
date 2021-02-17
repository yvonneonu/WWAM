package com.example.waam;

public class RegisterResponse {

    private int id;
    private String fullname;
    private String email;
    private String zipcode;
    private String gender;
    private String seeking;
    private String birth_date;
    private String password;
    private String password_confirmation ;

    public RegisterResponse(int id, String fullname, String email, String zipcode, String gender, String seeking, String birth_date, String password, String password_confirmation) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.zipcode = zipcode;
        this.gender = gender;
        this.seeking = seeking;
        this.birth_date = birth_date;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSeeking() {
        return seeking;
    }

    public void setSeeking(String seeking) {
        this.seeking = seeking;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
}

