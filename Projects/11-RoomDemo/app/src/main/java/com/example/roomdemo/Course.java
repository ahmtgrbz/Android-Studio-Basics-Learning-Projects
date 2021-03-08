package com.example.roomdemo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Department.class,
        parentColumns = "id", childColumns = "deptId"))
public class Course {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String code;
    public String name;
    public long deptId;
}

