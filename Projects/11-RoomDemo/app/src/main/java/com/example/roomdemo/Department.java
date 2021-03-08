package com.example.roomdemo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Department implements Serializable{
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
}
