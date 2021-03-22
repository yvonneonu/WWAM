package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChildrenRecordModel {
    @SerializedName("records")
    private List<ChildrenResult> childrenRecords = new ArrayList<>();

    public List<ChildrenResult> getChildrenRecords() {
        return childrenRecords;
    }
}
