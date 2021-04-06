package com.example.waam;

public class SlideBean {


    private int mItemBg;
    private String mTitle;
    private String mUserIcon;
    private String mUserSay;
    private String mfirstrate;
    private String msecondrating;
    private String numberSrike;

    public SlideBean(int mItemBg, String mTitle, String mUserIcon, String mUserSay, String mfirstrate, String msecondrating, String numberSrike) {
        this.mItemBg = mItemBg;
        this.mTitle = mTitle;
        this.mUserIcon = mUserIcon;
        this.mUserSay = mUserSay;
        this.mfirstrate = mfirstrate;
        this.msecondrating = msecondrating;
        this.numberSrike = numberSrike;
    }

    public int getmItemBg() {
        return mItemBg;
    }

    public void setmItemBg(int mItemBg) {
        this.mItemBg = mItemBg;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmUserIcon() {
        return mUserIcon;
    }

    public void setmUserIcon(String mUserIcon) {
        this.mUserIcon = mUserIcon;
    }

    public String getmUserSay() {
        return mUserSay;
    }

    public void setmUserSay(String mUserSay) {
        this.mUserSay = mUserSay;
    }

    public String getMfirstrate() {
        return mfirstrate;
    }

    public void setMfirstrate(String mfirstrate) {
        this.mfirstrate = mfirstrate;
    }

    public String getMsecondrating() {
        return msecondrating;
    }

    public void setMsecondrating(String msecondrating) {
        this.msecondrating = msecondrating;
    }

    public String getNumberSrike() {
        return numberSrike;
    }

    public void setNumberSrike(String numberSrike) {
        this.numberSrike = numberSrike;
    }
}