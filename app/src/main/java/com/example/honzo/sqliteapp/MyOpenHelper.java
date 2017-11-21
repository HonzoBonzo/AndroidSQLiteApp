package com.example.honzo.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Honzo on 21/11/2017.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sqliteapp.db";
    public static final String STUDENT_TABLE = "student";
    public static final String GROUP_TABLE = "group";
    public static final String STUDENT_GROUP_TABLE = "student_group";

    public MyOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateStudentTable = "CREATE TABLE " + STUDENT_TABLE + "(" +
                "student_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "student_name TEXT, " +
                "student_lastName" +
                ");";
        String queryCreateGroupTable = "CREATE TABLE " + GROUP_TABLE + "(" +
                "group_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "group_name TEXT" +
                ");";
        String queryCreateStudentGroupTable = "CREATE TABLE " + STUDENT_GROUP_TABLE + "(" +
                "sg_student_id INTEGER, " +
                "sg_group_id INTEGER" +
                ");";

        db.execSQL(queryCreateStudentTable);
        db.execSQL(queryCreateGroupTable);
        db.execSQL(queryCreateStudentGroupTable);
    }

    public void insertStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put("student_name", student.get_name());
        values.put("student_lastName", student.get_lastName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(STUDENT_TABLE, null, values);
        db.close();
    }

    public void deleteStudent(Student student) {
        String query = "DELETE FROM " + STUDENT_TABLE + " WHERE student_id = " + student.get_id() + ";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM " + STUDENT_TABLE + ";";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()) {
            int id = c.getInt(c.getColumnIndex("student_id"));
            String name = c.getString(c.getColumnIndex("student_name"));
            String lastName = c.getString(c.getColumnIndex("student_lastName"));

            students.add(new Student(id, name, lastName));
        }

        return students;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
