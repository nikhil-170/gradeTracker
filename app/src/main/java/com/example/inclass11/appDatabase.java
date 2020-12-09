package com.example.inclass11;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

/*
Assignment  InClass11
InCLass11
Group1C ---Pramukh Nagendra
        ---Nikhil Surya Peteti
 */
@Database(entities = {Course.class},version = 1)
public abstract class appDatabase extends RoomDatabase {
    public abstract courseDao courseDao();
}
