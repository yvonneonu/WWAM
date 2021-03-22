package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DrinkRecordModel {
    @SerializedName("records")
    private List<DrinkResult> drinkRecords = new ArrayList<>();

    public List<DrinkResult> getDrinkRecords() {
        return drinkRecords;
    }
}
