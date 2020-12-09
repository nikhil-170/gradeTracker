package com.example.inclass11;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/*
Assignment  InClass11
InCLass11
Group1C ---Pramukh Nagendra
        ---Nikhil Surya Peteti
 */
@Dao
public interface courseDao {


    @Insert
    void insertall(Course... courses);

    @Delete
    void delete(Course course);


    @Query("SELECT * FROM coursesTable")
    List<Course> getAll();

    @Query("SELECT * FROM coursesTable WHERE _id = :id LIMIT 1")
    Course findById(int id);

    @Update
    void update(Course course);
}
