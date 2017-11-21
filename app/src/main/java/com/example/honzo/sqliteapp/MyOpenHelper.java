package com.example.honzo.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Honzo on 21/11/2017.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "SqliteAppDB";

    public MyOpenHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
