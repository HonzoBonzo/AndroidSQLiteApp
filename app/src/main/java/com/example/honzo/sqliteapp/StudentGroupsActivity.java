package com.example.honzo.sqliteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class StudentGroupsActivity extends AppCompatActivity {
    Student student;
    String studentName;
    TextView studentFullName;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_groups);

        studentName = getIntent().getStringExtra("studentFullName");

        studentFullName = (TextView) findViewById(R.id.studentFullName);
        studentFullName.setText(studentName);

        list = (ListView) findViewById(R.id.groupsList);

    }
}
