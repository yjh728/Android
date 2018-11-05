package com.example.yjh.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
 *  SQLiteOpenHelper是一个抽象类，有两个抽象方法：onCreat()和onUpgrade()，在这两个方法里实现创建，升级数据库的逻辑
 *  SQLiteOpenHelper还有两个非常重要的实例方法：getReadableDatabase()和getWriteableDatabase()
 *  这两个方法都可以用于创建或打开一个现有的数据库(若不存在，则创建，否则直接打开)，并返回一个可对数据库进行读写操作的对象
 *  当数据库不可写的时候，getReadableDatabase()返回的对象将以只读的方式打开，getWriteableDatabase()将抛出异常
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper databaseHelper;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button creat = findViewById(R.id.creat_database);
        Button delete = findViewById(R.id.delete_database);
        Button add_data = findViewById(R.id.add_data);
        Button updata_data = findViewById(R.id.updata_data);
        Button delete_data = findViewById(R.id.delete_data);
        Button query_data = findViewById(R.id.query_data);
        //要想升级数据库，只需要把version的值设置比原来高即可
//        databaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 1);
        databaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 2);
        creat.setOnClickListener(this);
        delete.setOnClickListener(this);
        add_data.setOnClickListener(this);
        updata_data.setOnClickListener(this);
        delete_data.setOnClickListener(this);
        query_data.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.creat_database:
                databaseHelper.getWritableDatabase();
                break;
            case R.id.delete_database:
                if (databaseHelper.deleteDatabase(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "数据库已删除!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add_data:
//                打开数据库并获得SQLiteDatabase对象，该对象可以对数据库内容进行增删改查操作
                SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//                使用ContentValues来对添加的数据进行组装
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "The Da Vinci Code");
                contentValues.put("author", "Dan Brown");
                contentValues.put("price", 16.96);
                contentValues.put("pages", 454);
//                将数据添加到数据库中
                sqLiteDatabase.insert("Book", null, contentValues);
                contentValues.clear();
                contentValues.put("name", "The Lost Symbol");
                contentValues.put("author", "Dan Brown");
                contentValues.put("pages", 510);
                contentValues.put("price", 19.95);
                sqLiteDatabase.insert("Book", null, contentValues);
                break;
            case R.id.updata_data:
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
//                更新数据，第一个参数为表的名字，第二个参数为更新的数据
//                第三个参数和第四个参数指定具体更新哪几行
//                ？是一个占位符，可以通过第四个参数提供的一个字符串数组来作为第三个参数中的每个占位符来执行相应内容
                database.update("Book", values, "name = ?", new String[]{
                        "The Da Vinci Code"});
            case R.id.delete_data:
                SQLiteDatabase database1 = databaseHelper.getWritableDatabase();
//                删除数据，第一个参数为表的名字，第二个参数为删除条件，第三个参数来指定占位符的内容
                database1.delete("Book", "pages > ?", new String[]{"500"});
            case R.id.query_data:
                SQLiteDatabase database2 = databaseHelper.getWritableDatabase();
//                查询数据，第一个参数为表的名字，第二个参数指定查询哪几列，第三、四个参数约束查询某一行或某几行的数据
//                第五个参数指定需要去group by的列,第六个参数用于对group by之后的数据进行进一步过滤
//                第七个参数用于指定查询结果的排序方式
                Cursor cursor = database2.query("Book", null, null,
                        null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "book name is " + name);
                        Log.d(TAG, "book author is " + author);
                        Log.d(TAG, "book pages is " + pages);
                        Log.d(TAG, "book price is " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
        }
    }
}
