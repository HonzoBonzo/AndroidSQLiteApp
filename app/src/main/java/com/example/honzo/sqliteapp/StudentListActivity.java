package com.example.honzo.sqliteapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.honzo.sqliteapp.database.DbInteractor;
import com.example.honzo.sqliteapp.database.MyOpenHelper;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayAdapter<Student> adapter;
    private ListView list;
    private DbInteractor dbInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        dbInteractor = new DbInteractor(this);

        list = (ListView) findViewById(R.id.studentList);

        this.fillTheList();
        this.setListItemListener();

        adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, studentList);
        list.setAdapter(adapter);

    }

    private void fillTheList() {
        studentList = dbInteractor.getStudents();
    }

    private void setListItemListener(){
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Student st = (Student) parent.getItemAtPosition(position);
                        Toast.makeText(StudentListActivity.this, st.toString(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(StudentListActivity.this, StudentGroupsActivity.class);
                        i.putExtra("studentFullName", st.toString());
                        startActivity(i);
                    }
                }
        );
    }
}
