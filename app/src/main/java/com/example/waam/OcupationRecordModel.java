package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OcupationRecordModel {
    @SerializedName("records")
    private List<OcupationResult> occupationRecords = new ArrayList<>();
    public List<OcupationResult> getOccupationRecords(){
        return occupationRecords;
    }

}
