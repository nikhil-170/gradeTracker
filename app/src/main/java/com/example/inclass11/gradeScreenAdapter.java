package com.example.inclass11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;


/*
Assignment  InClass11
InCLass11
Group1C ---Pramukh Nagendra
        ---Nikhil Surya Peteti
 */
public class gradeScreenAdapter extends ArrayAdapter<Course> {

    List<Course> courseList;
    adapterListener pListener;
    public gradeScreenAdapter(@NonNull Context context, int resource, @NonNull List<Course> objects, adapterListener pListener) {
        super(context, resource, objects);
        this.pListener = pListener;
        this.courseList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.course_card_view, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Course course = courseList.get(position);

        viewHolder.courseNumTV.setText(course.courseNumber+"");
        viewHolder.creditHoursTV.setText(course.creditHours+" Credits Hours");
        viewHolder.courseNameTV.setText(course.courseName);
        viewHolder.courseGradeTV.setText(course.courseGrade);

        viewHolder.rubbishBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseList.remove(course);
                pListener.deleteCourse(course);
            }
        });


        return convertView;
    }

    public interface adapterListener{
        void deleteCourse(Course course);

    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    public static class ViewHolder {
        TextView courseNameTV, courseNumTV, creditHoursTV, courseGradeTV;
        ImageView rubbishBin;


        ViewHolder(View view) {
            courseNumTV = view.findViewById(R.id.courseNumId);
            courseGradeTV = view.findViewById(R.id.courseGradeId);
            courseNameTV = view.findViewById(R.id.courseNameId);
            creditHoursTV = view.findViewById(R.id.creditHoursId);
            rubbishBin = view.findViewById(R.id.rubbishId);
        }
    }
}
