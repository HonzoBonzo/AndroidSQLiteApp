package com.example.honzo.sqliteapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.honzo.sqliteapp.Student;

import java.util.ArrayList;

public class DbInteractor {

    private SQLiteDatabase db;
    private Context context;


    public static final String STUDENT_TABLE = "student";

    public DbInteractor(Context context){
        this.context = context;
        SQLiteOpenHelper sqLiteOpenHelper = new MyOpenHelper(context);
        this.db = sqLiteOpenHelper.getWritableDatabase();

    }


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

    public void insertStudent(String studentName, String studentLastName) {
        ContentValues values = new ContentValues();
        values.put("student_name", studentName);
        values.put("student_lastName", studentLastName);
        db.insert(STUDENT_TABLE, null, values);
    }

}
