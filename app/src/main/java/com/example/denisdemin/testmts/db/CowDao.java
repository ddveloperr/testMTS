package com.example.denisdemin.testmts.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.denisdemin.testmts.db.Model.Cow;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface CowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Cow> cows);

    @Insert
    void insertAll(Cow... cows);

    @Update
    void update(Cow cow);

    @Query("SELECT * FROM cow")
    List<Cow> getAllCows();

    @Query("SELECT * FROM cow WHERE Cow.id = :id")
    Cow getCow(int id);
}
