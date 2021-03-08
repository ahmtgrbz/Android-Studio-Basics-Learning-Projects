package com.example.roomdemo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Department.class, Course.class},
        version = 1)
public abstract class UniversityDB extends RoomDatabase {
    public abstract UniversityDAO getDao();
}

