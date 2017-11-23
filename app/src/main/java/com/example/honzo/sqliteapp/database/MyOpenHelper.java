package com.example.honzo.sqliteapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.example.honzo.sqliteapp.Student;

import java.util.ArrayList;

/**
 * Created by Honzo on 21/11/2017.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "sqliteapp.db";
    public static final String STUDENT_TABLE = "student";
    public static final String GROUP_TABLE = "grouptbl";
    public static final String STUDENT_GROUP_TABLE = "student_group";
    SQLiteDatabase db;

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        String queryCreateStudentTable = "CREATE TABLE " + STUDENT_TABLE + "(" +
                "student_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "student_name TEXT, " +
                "student_lastName" +
                ");";
        String queryCreateGroupTable = "CREATE TABLE " + GROUP_TABLE + "(" +
                "grouptbl_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "grouptbl_name TEXT" +
                ");";
        String queryCreateStudentGroupTable = "CREATE TABLE " + STUDENT_GROUP_TABLE + "(" +
                "sg_student_id INTEGER, " +
                "sg_group_id INTEGER" +
                ");";

        db.execSQL(queryCreateStudentTable);
        db.execSQL(queryCreateGroupTable);
        db.execSQL(queryCreateStudentGroupTable);

        insertStudent("Konrad", "Bysiek");
        insertStudent("Agata", "Czerwinska");
        insertStudent("Dorian", "Cekani");
        insertStudent("Vladek", "Czebotarew");
        insertStudent("Michal", "Pazdan");

        insertGroup("W 31");
        insertGroup("L 01");
        insertGroup("C 41");
        insertGroup("P 41");
        insertGroup("I 32");
    }


    public void insertStudent(String studentName, String studentLastName) {
        ContentValues values = new ContentValues();
        values.put("student_name", studentName);
        values.put("student_lastName", studentLastName);
        db.insert(STUDENT_TABLE, null, values);
    }

    public void insertGroup(String groupName) {
        ContentValues values = new ContentValues();
        values.put("grouptbl_name", groupName);
        db.insert(GROUP_TABLE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + GROUP_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_GROUP_TABLE + ";");
        onCreate(db);
    }
}
