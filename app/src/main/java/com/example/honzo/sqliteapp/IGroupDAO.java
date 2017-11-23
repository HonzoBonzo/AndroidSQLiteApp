package com.example.honzo.sqliteapp;

import java.util.ArrayList;

/**
 * Created by Honzo on 23/11/2017.
 */

public interface IGroupDAO {
    ArrayList<Group> getGroups();
    void insertGroup(String groupName);
    void deleteGroup(int groupId);
}
