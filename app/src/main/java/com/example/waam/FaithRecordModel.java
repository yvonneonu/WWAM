package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FaithRecordModel {

    @SerializedName("records")
    private List<FaithResult> faithRecords = new ArrayList<>();

    public List<FaithResult> getFaithRecords() {
        return faithRecords;
    }
}
