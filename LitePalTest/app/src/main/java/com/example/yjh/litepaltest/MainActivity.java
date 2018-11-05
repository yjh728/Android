package com.example.yjh.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button creatDatabase = findViewById(R.id.creat_database);
        creatDatabase.setOnClickListener(this);
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(this);
        Button updateData = findViewById(R.id.updata_data);
        updateData.setOnClickListener(this);
        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(this);
        Button quaryData = findViewById(R.id.query_data);
        quaryData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.creat_database:
                LitePal.getDatabase();
                break;
            case R.id.add_data:
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
                break;
            case R.id.updata_data:
                /*Book book1 = new Book();
                book1.setName("The Lost Symbol");
                book1.setAuthor("Dan Brown");
                book1.setPrice(19.95);
                book1.setPages(510);
                book1.setPress("Unknow");
                book1.save();
                book1.setPrice(10.99);
                book1.save();*/
                Book book1 = new Book();
                book1.setPrice(15.95);
                book1.setPress("Anchor");
                book1.updateAll("name = ? and author = ?", "The Lost Symbol",
                        "Dan Brown");
                break;
            case R.id.delete_data:
                DataSupport.deleteAll(Book.class,"author = ?", "DanBrown");
                break;
            case R.id.query_data:
                List<Book> list = DataSupport.findAll(Book.class);
                for (Book book2 : list) {
                    Log.d(TAG, "book name is " + book2.getName());
                    Log.d(TAG, "book author is " + book2.getAuthor());
                    Log.d(TAG, "book pages is " + book2.getPages());
                    Log.d(TAG, "book price is " + book2.getPrice());
                }
                break;
        }
    }
}
