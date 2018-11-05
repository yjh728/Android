package com.example.yjh.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TitleLayout extends LinearLayout implements View.OnClickListener{

//    private EditText editText;
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        Button button1 = (Button) findViewById(R.id.title_back);
        Button button2 = (Button) findViewById(R.id.title_edit);
//        editText = (EditText) findViewById(R.id.chart_input);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                ((Activity)getContext()).finish();
                break;
            case R.id.title_edit:
//                String input = editText.getText().toString();
                Toast.makeText(getContext(), "yjh728", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
