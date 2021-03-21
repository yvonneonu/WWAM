package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EthnicityRecordmodel {
    @SerializedName("records")
    private List<EthnicityResult> edurecords = new ArrayList<>();

    public List<EthnicityResult> getEtnicrecords() {
        return edurecords;
    }
}
