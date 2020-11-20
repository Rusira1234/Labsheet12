package com.example.labsheeet12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.labsheeet12.Database.DBHandler;

import java.util.ArrayList;

public class Student extends AppCompatActivity {
    TextView StudentUserName;
    ListView StudentListView;
    ArrayList Infomation;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        StudentUserName = findViewById(R.id.StudentUserName);
        StudentListView = findViewById(R.id.StudentListView);
        DBHandler dbHandler = new DBHandler(getApplicationContext());
        Infomation = dbHandler.GetMessages();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,Infomation);
        StudentListView.setAdapter(arrayAdapter);

        StudentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Subject = StudentListView.getItemAtPosition(i).toString();
                String Message = StudentListView.getItemAtPosition(i+1).toString();
                //String Message = StudentListView.getItemAtPosition(l).toString();
                Intent intent = new Intent(getApplicationContext(),Message.class);
                intent.putExtra("Subject",Subject);
                intent.putExtra("Message",Message);
                startActivity(intent);
            }
        });

    }
}