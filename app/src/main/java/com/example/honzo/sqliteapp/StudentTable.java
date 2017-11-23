package com.example.honzo.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Honzo on 21/11/2017.
 */

public class StudentTable implements IStudentDAO {
    public static final String STUDENT_TABLE = "student";
    private Context context;
    private SQLiteDatabase db;

    public StudentTable(Context context){
        this.context = context;
        SQLiteOpenHelper myHelper = new MyOpenHelper(context);
        this.db = myHelper.getWritableDatabase();
    }

    @Override
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM " + STUDENT_TABLE + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        do {
            int id = c.getInt(c.getColumnIndex("student_id"));
            String name = c.getString(c.getColumnIndex("student_name"));
            String lastName = c.getString(c.getColumnIndex("student_lastName"));

            students.add(new Student(id, name, lastName));
        } while (c.moveToNext());

        return students;
    }

    @Override
    public void insertStudent(String studentName, String studentLastName) {
        ContentValues values = new ContentValues();
        values.put("student_name", studentName);
        values.put("student_lastName", studentLastName);
        db.insert(STUDENT_TABLE, null, values);
    }

    @Override
    public void deleteStudent(int studentId) {
        String query = "DELETE FROM " + STUDENT_TABLE + " WHERE student_id = " + studentId + ";";
        db.execSQL(query);
    }

}
