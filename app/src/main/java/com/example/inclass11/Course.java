package com.example.inclass11;

/*
Assignment  InClass11
InCLass11
Group1C ---Pramukh Nagendra
        ---Nikhil Surya Peteti
 */
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coursesTable")
public class Course {


    @PrimaryKey(autoGenerate = true)
    public int _id;

    @ColumnInfo
    public String courseNumber;

    @ColumnInfo
    public String courseName;

    @ColumnInfo
    public String courseGrade;

    @ColumnInfo
    public long creditHours;

    @ColumnInfo
    public long gradePoint;


    public Course(String courseNumber, String courseName, String courseGrade,long gradePoint, long creditHours) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseGrade = courseGrade;
        this.creditHours = creditHours;
        this.gradePoint = gradePoint;
    }

    @Override
    public String toString() {
        return "Course{" +
                "_id=" + _id +
                ", courseNumber='" + courseNumber + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseGrade='" + courseGrade + '\'' +
                ", creditHours=" + creditHours +
                '}';
    }
}
