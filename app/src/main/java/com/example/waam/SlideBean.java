package com.example.waam;

public class SlideBean {


    private int mItemBg;
    private String mTitle;
    private String mUserIcon;
    private String mUserSay;

    public SlideBean(int mItemBg, String mTitle, String mUserIcon, String mUserSay) {
        this.mItemBg = mItemBg;
        this.mTitle = mTitle;
        this.mUserIcon = mUserIcon;
        this.mUserSay = mUserSay;
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
}