package com.example.honzo.sqliteapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

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

        fillTheList();
    }

    private void fillTheList() {
        studentList = dbInteractor.getStudents();
        this.setListItemListener();

        adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, studentList);
        list.setAdapter(adapter);
    }

    private void setListItemListener() {
        list.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Student st = (Student) parent.getItemAtPosition(position);
                    Toast.makeText(StudentListActivity.this, st.toString(), Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(StudentListActivity.this, StudentGroupsActivity.class);
                    i.putExtra("studentFullName", st.toString());
                    i.putExtra("studentId", st.get_id());
                    startActivity(i);
                }
            }
        );

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View arg1, int position, long id) {
                final Student st = (Student) parent.getItemAtPosition(position);
                Toast.makeText(StudentListActivity.this, st.toString(), Toast.LENGTH_LONG).show();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(StudentListActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_student_remove_confirm, null);
                Button mAddBtn = mView.findViewById(R.id.remove_student_button);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                mAddBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbInteractor.deleteStudent(st.get_id());
                        Toast.makeText(StudentListActivity.this, R.string.student_removed_info, Toast.LENGTH_SHORT).show();
                        fillTheList();
                        dialog.dismiss();
                    }
                });

                dialog.show();

                return true;
            }
        });
    }

    public void openNewStudentDialog(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(StudentListActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_student, null);
        final EditText mName = mView.findViewById(R.id.student_name);
        final EditText mLastname = mView.findViewById(R.id.student_lastname);
        Button mAddBtn = mView.findViewById(R.id.add_student_button);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Objects.equals(mName.getText().toString(), "") && !Objects.equals(mLastname.getText().toString(), "")) {
                    dbInteractor.insertStudent(mName.getText().toString(), mLastname.getText().toString());
                    Toast.makeText(StudentListActivity.this, R.string.new_student_added_info, Toast.LENGTH_SHORT).show();
                    mName.setText("");
                    mLastname.setText("");
                    fillTheList();
                } else {
                    Toast.makeText(StudentListActivity.this, R.string.student_data_not_full, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();

    }
}
