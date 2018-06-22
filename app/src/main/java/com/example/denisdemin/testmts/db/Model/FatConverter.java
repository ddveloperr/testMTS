package com.example.denisdemin.testmts.db.Model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FatConverter {
    @TypeConverter
    public String fromFat(List<Fat> fatList){
        Gson gson = new Gson();

        return gson.toJson(fatList);
    }

    @TypeConverter
    public List<Fat> toFat (String data){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Fat>>(){}.getType();
        return gson.fromJson(data,type);
    }
}
