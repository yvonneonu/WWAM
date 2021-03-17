package com.example.waam;

import android.widget.ArrayAdapter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecordModel {

    @SerializedName("records")
    private List<EducationModel> records = new ArrayList<>();

    public List<EducationModel> getRecords(){
        return records;

    }
}

