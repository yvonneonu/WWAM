package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecordModel {

    @SerializedName("records")
    private List<EduactionRsesult> records = new ArrayList<>();

    public List<EduactionRsesult> getModel() {
        return records;
    }
}
