package com.example.honzo.sqliteapp;

import java.util.ArrayList;

/**
 * Created by Honzo on 23/11/2017.
 */

public interface IStudentGroupDAO {
    ArrayList<Integer> getStudentGroups(int studentId);
    void insertRelationStudentGroup(int studentID, int groupId);
    void deleteRelationStudentGroup(int studentID, int groupId);
}
