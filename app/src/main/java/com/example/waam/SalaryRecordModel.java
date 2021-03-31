package com.example.waam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SalaryRecordModel {

    @SerializedName("records")
    private List<SalaryResult> salaryResults = new ArrayList<>();

    public List<SalaryResult> getSalaryResults() {
        return salaryResults;
    }
}
