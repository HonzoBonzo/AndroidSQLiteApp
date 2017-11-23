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
    private StudentTable studentTable;
    private GroupTable groupTable;
    private StudentGroupTable studentGroupTable;

    public DbManager(Context context){
        this.context = context;
        SQLiteOpenHelper sqLiteOpenHelper = new MyOpenHelper(context);
        this.db = sqLiteOpenHelper.getWritableDatabase();

        studentTable = new StudentTable(context);
        groupTable = new GroupTable(context);
        studentGroupTable = new StudentGroupTable(context);
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
        return studentGroupTable.getStudentGroups(studentId);
    }

    public void insertRelationStudentGroup(int studentID, int groupId) {
        studentGroupTable.insertRelationStudentGroup(studentID, groupId);
    }

    public void deleteRelationStudentGroup(int studentID, int groupId) {
        studentGroupTable.deleteRelationStudentGroup(studentID, groupId);
    }
}
