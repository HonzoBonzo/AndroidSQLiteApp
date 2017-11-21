package com.example.honzo.sqliteapp;

/**
 * Created by Honzo on 21/11/2017.
 */

public class Student {
    private int _id;
    private String _name;
    private String _lastName;


    public Student(String name, String lastName) {
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
}
