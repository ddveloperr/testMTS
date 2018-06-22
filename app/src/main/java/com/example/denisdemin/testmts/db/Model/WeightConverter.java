package com.example.denisdemin.testmts.db.Model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class WeightConverter {

    @TypeConverter
    public String fromWeight(List<Weight> weightList){
        Gson gson = new Gson();

        return gson.toJson(weightList);
    }

    @TypeConverter
    public List<Weight> toWeight (String data){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weight>>(){}.getType();
        return gson.fromJson(data,type);
    }
}
