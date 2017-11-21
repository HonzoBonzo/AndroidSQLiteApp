package com.example.honzo.sqliteapp;

import java.util.ArrayList;

/**
 * Created by Honzo on 21/11/2017.
 */

public class Student {
    private int _id;
    private String _name;
    private String _lastName;
    private ArrayList<Group> groups = new ArrayList<Group>();


    public Student(int id, String name, String lastName) {
        this._id = id;
        this._name = name;
        this._lastName = lastName;
    }

    public Student() {
        this._id = 0;
        this._name = "";
        this._lastName = "";
    }

    public String toString() {
        return _name + ' ' + _lastName + " (" + _id +")";
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_lastName() {
        return _lastName;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }
}
