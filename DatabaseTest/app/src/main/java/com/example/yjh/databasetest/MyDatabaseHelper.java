package com.example.yjh.databasetest;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/*
    Book表的建表语句
        create table Book (
            id integer primary key autoincrement，
            author text,
            price real,
            pages integer,
            name text)
    integer表示整型，text表示文本类型，real表示浮点型
    使用primary key将id设为主键
    用autoincrement关键字表示id列是自增长的
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String  CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            +"price real, "
            + "pages integer, "
            + "name text)";
    public static final String CREATE_CATEGORY = "create table Catregory ("
            +"id integer primary key autoincrement, "
            +"category_name text, "
            +"category_code integer)";
    private Context mContext;
    private String name;

//    一般使用这个参数少的构造方法
//    第一个参数是Context
//    第二个参数是数据库名
//    第三个参数是Cursor，一般传入null
//    第四个参数表示版本号
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
        this.name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
//        Toast.makeText(mContext, "数据库创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Book");
        sqLiteDatabase.execSQL("drop table if exists Category");
        onCreate(sqLiteDatabase);
    }

    //删除数据库
    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(name);
    }
}
