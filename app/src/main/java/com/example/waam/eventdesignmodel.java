package com.example.waam;

public class eventdesignmodel {
    private int image;
    private String infor;
    private String tag;
    private String newtage;
    private String rate;
    private String newrate;

    public eventdesignmodel(int image, String infor, String tag, String newtage, String rate, String newrate) {
        this.image = image;
        this.infor = infor;
        this.tag = tag;
        this.newtage = newtage;
        this.rate = rate;
        this.newrate = newrate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNewtage() {
        return newtage;
    }

    public void setNewtage(String newtage) {
        this.newtage = newtage;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNewrate() {
        return newrate;
    }

    public void setNewrate(String newrate) {
        this.newrate = newrate;
    }
}
