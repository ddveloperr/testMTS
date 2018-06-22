package com.example.denisdemin.testmts.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefHandler {

    private SharedPreferences prefs;

    private String FIRST_RUN = "first_run";

    public PrefHandler(Context context) {
        prefs = context.getSharedPreferences("APP_NAME",Context.MODE_PRIVATE);
    }

    public Boolean getFirstLaunch(){
        return prefs.getBoolean(FIRST_RUN,true);
    }

    public void setFirstLaunch(Boolean flag){
        prefs.edit().putBoolean(FIRST_RUN,false).apply();
    }
}
