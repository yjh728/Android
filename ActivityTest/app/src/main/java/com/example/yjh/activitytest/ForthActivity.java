package com.example.yjh.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class ForthActivity extends AppCompatActivity {

    public static final String TAG = "ForthActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forth_layout);
        Button button = (Button) findViewById(R.id.button_8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(ForthActivity.this, "test",
                        Toast.LENGTH_SHORT).show();*/
                Intent intent = getIntent();
                Bundle value = intent.getBundleExtra("data");
                String data = value.getString("data_key");
                Log.d(TAG, data);
            }
        });
    }
}
