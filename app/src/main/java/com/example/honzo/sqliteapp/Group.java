package com.example.honzo.sqliteapp;

/**
 * Created by Honzo on 21/11/2017.
 */

public class Group {
    private int _id;
    private String _name;

    public Group(String name) {
        this._name = name;
    }

    public Group(int id, String name) {
        this._id = id;
        this._name = name;
    }

    public String toString() {
        return "grupa: " + _name;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }
}
