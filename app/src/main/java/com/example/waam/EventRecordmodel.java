package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventRecordmodel {
    @SerializedName("records")
    private List<EventResult> evenRecord = new ArrayList<>();

    public List<EventResult> getEvenRecord(){
        return evenRecord;
    }
}
