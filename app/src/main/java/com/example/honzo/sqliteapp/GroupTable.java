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

public class GroupTable implements IGroupDAO {
    public static final String GROUP_TABLE = "grouptbl";
    private Context context;
    private SQLiteDatabase db;

    public GroupTable(Context context){
        this.context = context;
        SQLiteOpenHelper myHelper = new MyOpenHelper(context);
        this.db = myHelper.getWritableDatabase();
    }


    @Override
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

    @Override
    public void insertGroup(String groupName) {
        ContentValues values = new ContentValues();
        values.put("grouptbl_name", groupName);
        db.insert(GROUP_TABLE, null, values);
    }

    @Override
    public void deleteGroup(int groupId) {
        String query = "DELETE FROM " + GROUP_TABLE + " WHERE grouptbl_id = " + groupId + ";";
        db.execSQL(query);
    }
}
