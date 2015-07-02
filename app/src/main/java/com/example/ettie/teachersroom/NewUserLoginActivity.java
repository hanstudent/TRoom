package com.example.ettie.teachersroom;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class NewUserLoginActivity extends ActionBarActivity {

    private String[] grades;
    private String[] overallSubjects;
    private String teacherName;
    private String teacherEmail;
    private int teacherGrade;
    private String teacherSubject;
    Teacher teacher = new Teacher(); //created teacher object to be used in method
    DBAdapter dbAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*To hide action bar/title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);*/
        setTitle(R.string.tr);
        setContentView(R.layout.activity_login_new_user);

        intent = new Intent(NewUserLoginActivity.this, ListOfEntries.class);

        grades = getResources().getStringArray(R.array.gradesList);
        final Spinner spinnerG = (Spinner)findViewById(R.id.spinnerGrade);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, grades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerG.setAdapter(adapter);
        spinnerG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(LoginActivity.this, "You selected: " + grades[position], Toast.LENGTH_SHORT).show();
                String gradeNum = spinnerG.getSelectedItem().toString();
                teacherGrade = Integer.parseInt(gradeNum.substring(gradeNum.length() - 1));
                teacher.setGrade(teacherGrade);
                //Toast.makeText(NewUserLoginActivity.this, "You selected: " + teacherGrade, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //No implementation
            }
        });

    overallSubjects = getResources().getStringArray(R.array.subjectType);
        final Spinner spinnerST = (Spinner)findViewById(R.id.spinnerSubjectType);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, overallSubjects);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerST.setAdapter(adapter2);
        spinnerST.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(LoginActivity.this, "You selected: " + grades[position], Toast.LENGTH_SHORT).show();
                teacherSubject = spinnerST.getSelectedItem().toString();
                //Toast.makeText(NewUserLoginActivity.this, "You selected: " + teacherSubject, Toast.LENGTH_SHORT).show();
                teacher.setSubjectType(teacherSubject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //No implementation
            }
        });
    }

    public void stayTuned(View view) {
        //we add the String values of the EditTexts for name and email here, in the onClick method
        //see http://stackoverflow.com/questions/18430032/show-string-from-edittext for an explanation why specifically here

        teacherName = ((EditText) findViewById(R.id.etName)).getText().toString();
        teacherEmail = ((EditText) findViewById(R.id.etEmail)).getText().toString();
        teacher.setName(teacherName); //set name of teacher object
        teacher.setEmail(teacherEmail); //set email of teacher object

        //save record to database
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        dbAdapter.insertTeacher(teacher);
        dbAdapter.close();

        //we have to add code to this class and specifically to this method to address storing login info
        //in the meanwhile, we'll allow the activity to move to the ListOfEntries activity
       startActivity(intent);
    }

    //page 322 to retrieve all records, as a test to see that our db is really capturing all entries
    public void getAllTeachers(View view) {
        //Toast.makeText(this, "BH this works", Toast.LENGTH_SHORT).show();
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        Cursor cursor = dbAdapter.getAllTeachers();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id;
            String name;
            String email;
            int grade;
            String subjectType;
            Teacher teacher;
            do {
                id = cursor.getInt(cursor.getColumnIndex(DBAdapter.C_TID));
                name = cursor.getString(cursor.getColumnIndex(DBAdapter.C_TNAME));
                email = cursor.getString(cursor.getColumnIndex(DBAdapter.C_TEMAIL));
                grade = cursor.getInt(cursor.getColumnIndex(DBAdapter.C_TGRADE));
                subjectType = cursor.getString(cursor.getColumnIndex(DBAdapter.C_TSUBJECT_TYPE));
                teacher = new Teacher(name, email, grade, subjectType);
                Toast.makeText(this, "Teacher Info \nID: "+ id + teacher.toString(), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        dbAdapter.close();
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, R.string.BSD, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contact:
                Toast.makeText(this, R.string.contactInfo, Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }
}

//Misc. items:
/* The following were originally in the stayTuned() method:
EditText tName = ((EditText)findViewById(R.id.etName));
EditText tEmail = ((EditText)findViewById(R.id.etEmail));
Toast.makeText(NewUserLoginActivity.this, "Teacher: \n" + teacher.getName() + "\n" +
        teacher.getEmail() + "\n"+
        teacher.getGrade() + "\n" +
        teacher.getSubjectType(), Toast.LENGTH_SHORT).show();*/