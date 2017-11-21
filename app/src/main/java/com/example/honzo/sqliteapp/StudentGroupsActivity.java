package com.example.honzo.sqliteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentGroupsActivity extends AppCompatActivity {
    Student student;
    String studentName;
    TextView studentFullName;
    ArrayAdapter<Group> adapter;
    private ArrayList<Group> studentsGroups = new ArrayList<>();
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_groups);

        studentName = getIntent().getStringExtra("studentFullName");

        studentFullName = (TextView) findViewById(R.id.studentFullName);
        studentFullName.setText(studentName);

        list = (ListView) findViewById(R.id.groupsList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentsGroups);
        list.setAdapter(adapter);

        this.fillTheList();
    }

    private void fillTheList() {
        studentsGroups.add(new Group("W 31"));
        studentsGroups.add(new Group("L 01"));
        studentsGroups.add(new Group("C 41"));
        studentsGroups.add(new Group("P 41"));
        studentsGroups.add(new Group("I 3"));
    }
}
