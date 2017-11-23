package com.example.honzo.sqliteapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.honzo.sqliteapp.Group;
import com.example.honzo.sqliteapp.Student;

import java.util.ArrayList;

public class DbInteractor {

    private SQLiteDatabase db;
    private Context context;


    public static final String STUDENT_TABLE = "student";
    public static final String GROUP_TABLE = "grouptbl";

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

    public void deleteStudent(int studentId) {
        String query = "DELETE FROM " + STUDENT_TABLE + " WHERE student_id = " + studentId + ";";
        db.execSQL(query);
    }

    public ArrayList<Group> getGroups() {
        ArrayList<Group> groups = new ArrayList<>();
        String query = "SELECT * FROM " + GROUP_TABLE + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        do {
            int id = c.getInt(c.getColumnIndex("grouptbl_id"));
            String name = c.getString(c.getColumnIndex("grouptbl_name"));
            groups.add(new Group(id, name));
        } while (c.moveToNext());

        return groups;
    }

    public void insertGroup(String groupName) {
        ContentValues values = new ContentValues();
        values.put("grouptbl_name", groupName);
        db.insert(GROUP_TABLE, null, values);
    }

    public void deleteGroup(int groupId) {
        String query = "DELETE FROM " + GROUP_TABLE + " WHERE grouptbl_id = " + groupId + ";";
        db.execSQL(query);
    }
}
