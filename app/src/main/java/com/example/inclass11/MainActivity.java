package com.example.inclass11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
/*
Assignment  InClass11
InCLass11
Group1C ---Pramukh Nagendra
        ---Nikhil Surya Peteti
 */
public class MainActivity extends AppCompatActivity implements gradeScreenFragment.gradesScreenInterface {

    appDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = Room.databaseBuilder(this, appDatabase.class, "course.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build();


        getSupportFragmentManager().beginTransaction().add(R.id.contentViewId, new gradeScreenFragment(db)).commit();
    }

    @Override
    public void callAddCourseFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentViewId, new addCourseFragment(db)).addToBackStack(null).commit();
    }
}