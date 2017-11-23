package com.example.honzo.sqliteapp;

import java.util.ArrayList;

/**
 * Created by Honzo on 23/11/2017.
 */

public interface IStudentDAO {
    ArrayList<Student> getStudents();
    void insertStudent(String studentName, String studentLastName);
    void deleteStudent(int studentId);
}
