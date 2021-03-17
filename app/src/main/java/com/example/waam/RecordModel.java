package com.example.waam;

import android.widget.ArrayAdapter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecordModel {

    @SerializedName("records")
    private List<EducationModel> records = new ArrayList<>();

    private List<EducationModel> getRecords(){
        return getRecords();
    }
}

