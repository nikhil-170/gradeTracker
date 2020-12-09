package com.example.inclass11;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/*
Assignment  InClass11
InCLass11
Group1C ---Pramukh Nagendra
        ---Nikhil Surya Peteti
 */
public class addCourseFragment extends Fragment {


    appDatabase db;
    Course course;
    final String TAG = "demo";

    public addCourseFragment(appDatabase database) {
        this.db = database;
        // Required empty public constructor
    }

    TextView cancelTextView;
    EditText courseNameET, courseNumberET, creditHoursET;
    Button submit;
    RadioGroup courseGrades;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_course, container, false);

        getActivity().setTitle(getResources().getString(R.string.addcourse));

        cancelTextView = view.findViewById(R.id.cancelTV);

        courseNameET=view.findViewById(R.id.courseNameET);
        courseNumberET=view.findViewById(R.id.courseNumEditTextId);
        creditHoursET=view.findViewById(R.id.creditHoursET);
        submit = view.findViewById(R.id.submitButtonId);
        courseGrades = view.findViewById(R.id.gradesRadioGrpId);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String courseName = String.valueOf(courseNameET.getText());
                    String courseNum = String.valueOf(courseNumberET.getText());
                    long creditHours = Integer.parseInt(String.valueOf(creditHoursET.getText()));
                    int checkedId = courseGrades.getCheckedRadioButtonId();


                    if(courseName.isEmpty()){
                        AlertUtils.showOKDialog(getContext(),getResources().getString(R.string.Error),getResources().getString(R.string.entercoursename));
                    }else if(courseNum.isEmpty()){
                        AlertUtils.showOKDialog(getContext(),getResources().getString(R.string.Error),getResources().getString(R.string.entercoursenum));
                    }else if(creditHours> 0) {
                        if(checkedId == R.id.rbAId){
                            db.courseDao().insertall(new Course(courseNum, courseName, "A", (long) 4.0, creditHours));
                        }else if(checkedId == R.id.rbBId){
                            db.courseDao().insertall(new Course(courseNum, courseName, "B", (long) 3.0, creditHours));
                        }else if(checkedId == R.id.rbCId){
                            db.courseDao().insertall(new Course(courseNum, courseName, "C", (long) 2.0,creditHours));
                        }else if(checkedId==R.id.rbDId){
                            db.courseDao().insertall(new Course(courseNum, courseName, "D", (long) 1.0,creditHours));
                        }else if(checkedId == R.id.rbFId){
                            db.courseDao().insertall(new Course(courseNum, courseName, "F", (long) 0.0, creditHours));
                        }
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentViewId, new gradeScreenFragment(db)).commit();
                    }

                }catch (Exception e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }
    
}