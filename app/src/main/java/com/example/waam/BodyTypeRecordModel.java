package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BodyTypeRecordModel {
    @SerializedName("records")
    private List<BodyTypeResult> bodyTypeRecord = new ArrayList<>();

    public List<BodyTypeResult> getBodyTypeRecord() {
        return bodyTypeRecord;
    }
}
