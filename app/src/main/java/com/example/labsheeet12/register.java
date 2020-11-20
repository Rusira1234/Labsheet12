package com.example.labsheeet12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.labsheeet12.Database.DBHandler;

public class register extends AppCompatActivity {
    EditText UserName,Passsword;
    RadioButton Student,Teacher;
    Button buttonReg;
    String Status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        UserName = findViewById(R.id.editTextPersonName);
        Passsword = findViewById(R.id.editTextPassword);
        Student = findViewById(R.id.radioButtonSt);
        Teacher = findViewById(R.id.radioButtonTea);
        buttonReg = findViewById(R.id.buttonReg);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Student.isChecked()){
                    Status = "Student";
                }
                else{
                    Status = "Teacher";
                }
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long yeps = dbHandler.AddUser(UserName.getText().toString(),Passsword.getText().toString(),Status);
                if(yeps >= 1){
                    Toast.makeText(register.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    }