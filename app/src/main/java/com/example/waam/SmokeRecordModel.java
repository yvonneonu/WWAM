package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SmokeRecordModel {
    @SerializedName("records")
    private List<SmokeResult> smokeRecord = new ArrayList<>();

    public List<SmokeResult> getSmokeRecord() {
        return smokeRecord;
    }
}
