package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PoliticsRecordModel {
    @SerializedName("records")
    private List<PoliticsResult> politicsRecords = new ArrayList<>();

    public List<PoliticsResult> getPoliticsModel() {
        return politicsRecords;
    }
}
