package com.example.denisdemin.testmts.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.denisdemin.testmts.db.Model.Cow;

@Database(entities = {Cow.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME="app_db";

    public abstract CowDao getCowDao();
}
