package com.example.honzo.sqliteapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.honzo.sqliteapp.Student;

import java.util.ArrayList;

/**
 * Created by mjbor on 11/22/2017.
 */

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

        while(c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("student_id"));
            String name = c.getString(c.getColumnIndex("student_name"));
            String lastName = c.getString(c.getColumnIndex("student_lastName"));

            students.add(new Student(id, name, lastName));
        }

/*        students.add(new Student(121233, "Konrad", "Bysiek"));
        students.add(new Student(221233, "Agata", "Czerwinska"));
        students.add(new Student(3112323, "Dorian", "Cekani"));
        students.add(new Student(1212334, "Vladek", "Czebotarew"));*/

        return students;
    }

}
