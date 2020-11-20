
package com.example.labsheeet12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labsheeet12.Database.DBHandler;

public class Teacher extends AppCompatActivity {
    EditText SubName;
    EditText editTextMultiLine;
    TextView TeacherNameYeah;
    Button Add;
    String User = "Teacher";
    Intent TeacherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        SubName = findViewById(R.id.editTextPersonName2);
        editTextMultiLine = findViewById(R.id.editTextMultiLine);
        Add = findViewById(R.id.button2);
        TeacherNameYeah = findViewById(R.id.textView2);
        TeacherName = getIntent();
        TeacherNameYeah.setText(TeacherName.getStringExtra("Teacher"));


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long result = dbHandler.AddMessage(TeacherNameYeah.getText().toString(),SubName.getText().toString(),editTextMultiLine.getText().toString());
                if(result >= 1){
                    Toast.makeText(Teacher.this, "Message successfuly added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}