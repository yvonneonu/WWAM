package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserRecordModel {
    @SerializedName("records")
    private List<UserResult> userResults = new ArrayList<>();

    public List<UserResult> getUserResults(){
        return userResults;
    }
}

