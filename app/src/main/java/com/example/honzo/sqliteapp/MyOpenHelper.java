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
    public static final String GROUP_TABLE = "grouptbl";
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

        insertStudent(new Student(1, "Konrad", "Bysiek"));
        insertStudent(new Student(2, "Agata", "Czerwinska"));
        insertStudent(new Student(3, "Dorian", "Cekani"));
        insertStudent(new Student(4, "Vladek", "Czebotarew"));
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
        db.close();
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

        db.close();
        return students;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + GROUP_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_GROUP_TABLE + ";");
        onCreate(db);
    }
}
