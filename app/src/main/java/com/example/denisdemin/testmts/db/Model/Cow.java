package com.example.denisdemin.testmts.db.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;
import org.joda.time.Years;

import java.lang.reflect.Type;
import java.util.List;

@Entity(tableName = "cow")
public class Cow {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name ="breed")
    private String breed;

    @ColumnInfo(name="color")
    private String color;

    @ColumnInfo(name="birthDate")
    private String birthDate;

    @ColumnInfo(name="mother")
    private String mother;

    @ColumnInfo(name = "father")
    private String father;

    @ColumnInfo(name = "weight")
    @TypeConverters(WeightConverter.class)
    private List<Weight> weightList;

    @ColumnInfo(name = "fat")
    @TypeConverters(FatConverter.class)
    private List<Fat> fatList;

    public Cow(int id, String name, String breed, String color, String birthDate, String mother, String father, List<Weight> weightList, List<Fat> fatList) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.birthDate = birthDate;
        this.mother = mother;
        this.father = father;
        this.weightList = weightList;
        this.fatList = fatList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public List<Weight> getWeightList() {
        return weightList;
    }

    public void setWeightList(List<Weight> weightList) {
        this.weightList = weightList;
    }

    public List<Fat> getFatList() {
        return fatList;
    }

    public void setFatList(List<Fat> fatList) {
        this.fatList = fatList;
    }

    public int getAge(){
        String temp = birthDate;
        int day = Integer.valueOf(temp.substring(0,temp.indexOf(".")));
        int month = Integer.valueOf(temp.substring(temp.indexOf(".")+1,temp.lastIndexOf(".")));
        int year = Integer.valueOf(temp.substring(temp.lastIndexOf(".")+1));

        DateTime today = new DateTime();

        DateTime prev = new DateTime(year,month,day,0,0);

        return Math.abs(Years.yearsBetween(today,prev).getYears());
    }

    public int getDayOfMonth(){
        String temp = birthDate;
        return Integer.valueOf(temp.substring(0,temp.indexOf(".")));
    }

    public int getMonthOfYear(){
        String temp = birthDate;
        return Integer.valueOf(temp.substring(temp.indexOf(".")+1,temp.lastIndexOf(".")));
    }

    public int getYear(){
        String temp = birthDate;
        return Integer.valueOf(temp.substring(temp.lastIndexOf(".")+1));
    }
}
