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

public class StudentGroupTable implements IStudentGroupDAO {
    public static final String STUDENT_GROUP_TABLE = "student_group";
    private Context context;
    private SQLiteDatabase db;

    public StudentGroupTable(Context context){
        this.context = context;
        SQLiteOpenHelper myHelper = new MyOpenHelper(context);
        this.db = myHelper.getWritableDatabase();
    }

    @Override
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

    @Override
    public void insertRelationStudentGroup(int studentID, int groupId) {
        ContentValues values = new ContentValues();
        values.put("sg_student_id", studentID);
        values.put("sg_group_id", groupId);
        db.insert(STUDENT_GROUP_TABLE, null, values);
    }

    @Override
    public void deleteRelationStudentGroup(int studentID, int groupId) {
        db.delete(STUDENT_GROUP_TABLE, "sg_student_id = "+studentID+" AND sg_group_id = " + groupId + ";", null);
    }
}
