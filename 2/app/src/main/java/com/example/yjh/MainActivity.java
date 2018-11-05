package com.example.yjh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView pyjh;  //培养计划,设置监听器用

    TextView grxx;  //个人信息,设置监听用

    private PersonalInformation information;    //个人信息对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pyjh = findViewById(R.id.pyjh);
        grxx = findViewById(R.id.grxx);
        pyjh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TrainingProgramActivity.class);
                startActivity(intent);
            }
        });
        grxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initInformation();
                MyDialog myDialog = new MyDialog(MainActivity.this, information);
                myDialog.show();
            }
        });
    }

    private void initInformation() {
        information = new PersonalInformation();
        information.setName("杨佳豪");
        information.setId("04173174");
        information.setBanji("软件1706");
        information.setCollege("计算机学院");
        information.setProfession("软件工程");
        information.setEducation("本科");
        information.setStartYear("2017");
    }

}
