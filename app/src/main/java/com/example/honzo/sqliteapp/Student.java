package com.example.honzo.sqliteapp;

/**
 * Created by Honzo on 21/11/2017.
 */

public class Student {
    private String name;
    private String lastName;


    public Student(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Student() {
        this.name = "";
        this.lastName = "";
    }

    public String toString() {
        return name + ' ' + lastName + ":)";
    }
}
