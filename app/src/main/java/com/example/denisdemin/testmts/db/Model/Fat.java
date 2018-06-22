package com.example.denisdemin.testmts.db.Model;

import java.util.Date;

public class Fat {
    private int fatValue;

    private String date;

    public Fat(int fatValue, String date) {
        this.fatValue = fatValue;
        this.date = date;
    }

    public int getFatValue() {
        return fatValue;
    }

    public void setFatValue(int fatValue) {
        this.fatValue = fatValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
