package com.example.inclass11;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
/*
Assignment  InClass11
InCLass11
Group1C ---Pramukh Nagendra
        ---Nikhil Surya Peteti
 */

public class gradeScreenFragment extends Fragment {
    final String TAG = "demo";
    appDatabase db;
    List<Course> courseList;
    ListView listView;
    TextView gpa, totalCreditHours;
    long totalHours, totalGradepoints, GPA;
    gradeScreenAdapter adapter;

    public gradeScreenFragment(appDatabase database) {
        this.db = database;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grade_screen, container, false);

        listView= view.findViewById(R.id.listViewID);
        courseList= db.courseDao().getAll();
        gpa = view.findViewById(R.id.gpaId);
        totalCreditHours = view.findViewById(R.id.totalCreditHoursId);

        adapter = new gradeScreenAdapter(getContext(), R.layout.course_card_view, courseList, new gradeScreenAdapter.adapterListener() {
            @Override
            public void deleteCourse(Course course) {

                db.courseDao().delete(course);
                courseList = db.courseDao().getAll();
                gpa.setText("GPA: "+ calGPA());
                totalCreditHours.setText("Hours: " + calHours());
                adapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        gpa.setText("GPA: "+ calGPA());
        totalCreditHours.setText("Hours: "+calHours());

        Log.d(TAG, "onCreateView: "+ courseList);
        return view;
    }

    gradesScreenInterface plistener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        plistener = (gradesScreenInterface) context;
    }

    public interface gradesScreenInterface{

        void callAddCourseFragment();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.addIcon){
            plistener.callAddCourseFragment();
        }
        return super.onOptionsItemSelected(item);
    }
    public String calHours(){

        long totalHours = 0;

        for (Course course:courseList) {
            totalHours = totalHours + course.creditHours;
        }
        return  String.valueOf(totalHours);
    }

    public String calGPA(){
        Double GPA;
        long totalHours = Long.valueOf(calHours());
        long totalGradepoints = 0;


        for (Course course:courseList) {
                totalGradepoints +=  course.creditHours * course.gradePoint;
        }
        if(totalHours == 0){
             GPA = Double.valueOf(totalGradepoints/1);
        }else{
             GPA = Double.valueOf(totalGradepoints/totalHours);
        }
        return  String.valueOf(GPA);
    }
}