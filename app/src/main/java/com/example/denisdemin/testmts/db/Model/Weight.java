package com.example.denisdemin.testmts.db.Model;

import java.util.Date;

public class Weight {
    private int weightValue;

    private String date;

    public Weight(int weightValue, String date) {
        this.weightValue = weightValue;
        this.date = date;
    }

    public int getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(int weightValue) {
        this.weightValue = weightValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
