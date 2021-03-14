package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecordModel {

    @SerializedName("record")
    private ArrayList<EduactionRsesult> model = new ArrayList<>();

    public ArrayList<EduactionRsesult> getModel() {
        return model;
    }
}
