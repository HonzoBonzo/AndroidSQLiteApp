package com.example.honzo.sqliteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {

    private ArrayList<String> arrayList = new ArrayList<String>(5);
    private ArrayList<Student> studentList = new ArrayList<Student>(5);
    private ArrayAdapter<Student> adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        list = (ListView) findViewById(R.id.studentList);

        adapter = new ArrayAdapter<Student>(this, android.R.layout.select_dialog_item, studentList);
        list.setAdapter(adapter);

        this.fillTheList();
        this.setListItemListener();

    }

    private void fillTheList() {
        studentList.add(new Student("Konrad", "Bysiek"));
        studentList.add(new Student("Agata", "Czerwinska"));
        studentList.add(new Student("Dorian", "Cekani"));
        studentList.add(new Student("Vladek", "Czebotarew"));
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
