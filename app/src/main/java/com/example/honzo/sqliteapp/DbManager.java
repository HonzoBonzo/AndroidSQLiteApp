package com.example.honzo.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbManager {

    private SQLiteDatabase db;
    private Context context;


    public static final String STUDENT_TABLE = "student";
    public static final String GROUP_TABLE = "grouptbl";
    public static final String STUDENT_GROUP_TABLE = "student_group";

    public DbManager(Context context){
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

        if (c != null && c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex("grouptbl_id"));
                String name = c.getString(c.getColumnIndex("grouptbl_name"));
                groups.add(new Group(id, name));
            } while (c.moveToNext());
        }

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

    public ArrayList<Integer> getStudentGroups(int studentId) {
        ArrayList<Integer> groupsId = new ArrayList<>();
        String query = "SELECT * FROM " + STUDENT_GROUP_TABLE + " WHERE sg_student_id = "+ studentId +";";

        Cursor c = db.rawQuery(query, null);

        if (c != null && c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex("sg_group_id"));
                groupsId.add(id);
            } while (c.moveToNext());
        }

        return groupsId;
    }

    public void insertRelationStudentGroup(int studentID, int groupId) {
        ContentValues values = new ContentValues();
        values.put("sg_student_id", studentID);
        values.put("sg_group_id", groupId);
        db.insert(STUDENT_GROUP_TABLE, null, values);
    }

    public void deleteRelationStudentGroup(int studentID, int groupId) {
        db.delete(STUDENT_GROUP_TABLE, "sg_student_id = "+studentID+" AND sg_group_id = " + groupId + ";", null);
    }
}
