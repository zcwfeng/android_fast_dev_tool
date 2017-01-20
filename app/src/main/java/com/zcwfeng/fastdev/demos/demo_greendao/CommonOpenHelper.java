package com.zcwfeng.fastdev.demos.demo_greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CommonOpenHelper extends SQLiteOpenHelper {

    private static CommonOpenHelper helper;

    public static CommonOpenHelper getInstance(Context context) {
        if (helper == null) {
            helper = new CommonOpenHelper(context, "common.db", null, 1);
        }
        return helper;
    }

    private CommonOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建love表
        db.execSQL("create table love(" +
                "id integer primary key autoincrement, " +
                "name varchar, " +
                "price varchar, " +
                "sell_num integer, " +
                "image_url varchar, " +
                "address varchar" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}