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

    private StudentTable studentTable;
    private GroupTable groupTable;

    public DbManager(Context context){
        this.context = context;
        SQLiteOpenHelper sqLiteOpenHelper = new MyOpenHelper(context);
        this.db = sqLiteOpenHelper.getWritableDatabase();

        studentTable = new StudentTable(context);
        groupTable = new GroupTable(context);
    }


    public ArrayList<Student> getStudents() {
        return studentTable.getStudents();
    }

    public void insertStudent(String studentName, String studentLastName) {
        studentTable.insertStudent(studentName, studentLastName);
    }

    public void deleteStudent(int studentId) {
        studentTable.deleteStudent(studentId);
    }

    public ArrayList<Group> getGroups() {
        return groupTable.getGroups();
    }

    public void insertGroup(String groupName) {
        groupTable.insertGroup(groupName);
    }

    public void deleteGroup(int groupId) {
        groupTable.deleteGroup(groupId);
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
