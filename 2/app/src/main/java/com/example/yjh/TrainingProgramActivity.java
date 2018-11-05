package com.example.yjh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class TrainingProgramActivity extends AppCompatActivity {

    private RecyclerView courseRecyclerView;

    private RecyclerView semesterRecyclerView;

    private List<String> semesterList = new ArrayList<>();

    private List<Course> courseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_program);
        courseRecyclerView = findViewById(R.id.course_recycler_view);
        semesterRecyclerView = findViewById(R.id.semester_recycler_view);
        initCourseList();
        initSemesterList();
        CourseAdapter courseAdapter = new CourseAdapter(courseList);
        SemesterAdapter semesterAdapter = new SemesterAdapter(semesterList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        courseRecyclerView.setLayoutManager(layoutManager1);
        courseRecyclerView.setAdapter(courseAdapter);
        semesterRecyclerView.setLayoutManager(layoutManager);
        semesterRecyclerView.setAdapter(semesterAdapter);
    }

    private void initCourseList() {
        Course course = new Course("C语言程序设计II", "JS100031",
                "3.0", "3.0-0.0", "校考");
        courseList.add(course);
        Course course1 = new Course("大学体育", "JS100031",
                "3.0", "3.0-0.0", "校考");
        courseList.add(course1);
        Course course2 = new Course("毛概", "JS100031",
                "3.0", "3.0-0.0", "校考");
        courseList.add(course2);
        Course course3 = new Course("工图", "JS100031",
                "3.0", "3.0-0.0", "校考");
        courseList.add(course3);
        Course course4 = new Course("认识实习", "JS100031",
                "3.0", "3.0-0.0", "校考");
        courseList.add(course4);
        Course course5 = new Course("高数", "JS100031",
                "3.0", "3.0-0.0", "校考");
        courseList.add(course5);
    }

    private void initSemesterList() {
        semesterList.add("第一学期");
        semesterList.add("第二学期");
        semesterList.add("第三学期");
        semesterList.add("第四学期");
        semesterList.add("第五学期");
        semesterList.add("第六学期");
        semesterList.add("第七学期");
        semesterList.add("第八学期");
    }
}
