package com.example.yjh.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String newId;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addData = findViewById(R.id.add_data);
        Button deleteData = findViewById(R.id.delete_data);
        Button updateData = findViewById(R.id.update_data);
        Button quaryData = findViewById(R.id.quary_data);
        addData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
        updateData.setOnClickListener(this);
        quaryData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_data:
                Uri uri = Uri.parse("content://com.example.yjh.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("price", 19.99);
                values.put("pages", 520);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                break;
            case R.id.delete_data:
                Uri uri1 = Uri.parse("content://com.example.yjh.databasetest.provider/book/" + newId);
                getContentResolver().delete(uri1, null, null);
                break;
            case R.id.update_data:
                Uri uri2 = Uri.parse("content://com.example.yjh.databasetest.provider/book");
                ContentValues values1 = new ContentValues();
                values1.put("price", 20.99);
                getContentResolver().update(uri2, values1, "id = ?", new String[]{newId});
                break;
            case R.id.quary_data:
                Uri uri3 = Uri.parse("content://com.example.yjh.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uri3, null, null,
                        null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Log.d(TAG, "book name is " + cursor.getString(cursor.getColumnIndex("name")));
                        Log.d(TAG, "book author is " + cursor.getString(cursor.getColumnIndex("author")));
                        Log.d(TAG, "book price is " + cursor.getDouble(cursor.getColumnIndex("price")));
                        Log.d(TAG, "book pages is " + cursor.getInt(cursor.getColumnIndex("pages")));
                    }
                }
        }
    }
}
