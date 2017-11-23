package com.example.honzo.sqliteapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class StudentGroupsActivity extends AppCompatActivity {
    String studentName, studentLastName;
    TextView studentFullName;
    int studentId;
    ArrayAdapter<Group> adapter;
    private ArrayList<Group> allGroups = new ArrayList<>();
    private ArrayList<Integer> studentGroups = new ArrayList<>();
    private ListView list;
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_groups);
        dbManager = new DbManager(this);

        studentName = getIntent().getStringExtra("studentName");
        studentLastName = getIntent().getStringExtra("studentLastName");
        studentId = getIntent().getIntExtra("studentId", 0);

        studentFullName = (TextView) findViewById(R.id.studentFullName);
        studentFullName.setText(studentName);

        list = (ListView) findViewById(R.id.groupsList);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.fillTheList();


    }

    private void fillTheList() {
        allGroups = dbManager.getGroups();
        studentGroups = dbManager.getStudentGroups(studentId);
        setListItemListener();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, allGroups);
        list.setAdapter(adapter);

        int i = 0;
        for (Group group: allGroups) {
            if (studentGroups.contains(group.get_id())) {
                list.setItemChecked(i, true);
            } else {
                list.setItemChecked(i, false);
            }
            i++;
        }

    }

    private void setListItemListener() {
        list.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Group group = (Group) parent.getItemAtPosition(position);

                    SparseBooleanArray checked = list.getCheckedItemPositions();
                    if (checked.get(position)) {
                        dbManager.insertRelationStudentGroup(studentId, group.get_id());
                    } else {
                        dbManager.deleteRelationStudentGroup(studentId, group.get_id());
                    }
                }
            }
        );

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View arg1, int position, long id) {
                final Group group = (Group) parent.getItemAtPosition(position);
                Toast.makeText(StudentGroupsActivity.this, group.toString(), Toast.LENGTH_LONG).show();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(StudentGroupsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_group_remove_confirm, null);
                Button mAddBtn = mView.findViewById(R.id.remove_group_button);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                mAddBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbManager.deleteGroup(group.get_id());
                        Toast.makeText(StudentGroupsActivity.this, "Group has been deleted.", Toast.LENGTH_SHORT).show();
                        fillTheList();
                        dialog.dismiss();
                    }
                });

                dialog.show();

                return true;
            }
        });
    }

    public void openNewGroupDialog(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(StudentGroupsActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_group_new, null);
        final EditText mName = mView.findViewById(R.id.group_name);
        Button mAddBtn = mView.findViewById(R.id.add_group_btn);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Objects.equals(mName.getText().toString(), "")) {
                    dbManager.insertGroup(mName.getText().toString());
                    Toast.makeText(StudentGroupsActivity.this, "Group "+mName.getText().toString()+ " has been added.", Toast.LENGTH_SHORT).show();
                    fillTheList();
                    dialog.dismiss();
                } else {
                    Toast.makeText(StudentGroupsActivity.this, R.string.new_group_warn_empty, Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    public void openEditStudentDialog(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(StudentGroupsActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_student_edit, null);
        final EditText mName = mView.findViewById(R.id.student_name);
        final EditText mLastname = mView.findViewById(R.id.student_lastname);
        Button mAddBtn = mView.findViewById(R.id.submit_edit_student_button);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        mName.setText(studentName);
        mLastname.setText(studentLastName);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Objects.equals(mName.getText().toString(), "") && !Objects.equals(mLastname.getText().toString(), "")) {
                    dbManager.updateStudent(mName.getText().toString(), mLastname.getText().toString(), studentId);
                    Toast.makeText(StudentGroupsActivity.this, "Student has been updated.", Toast.LENGTH_SHORT).show();
                    studentFullName.setText(mName.getText().toString());
                    dialog.dismiss();
                } else {
                    Toast.makeText(StudentGroupsActivity.this, R.string.student_data_not_full, Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();

    }
}
