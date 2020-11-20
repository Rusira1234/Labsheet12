package com.example.labsheeet12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Message extends AppCompatActivity {
    TextView SubjectText;
    TextView MessageText;
    Intent intent1;
    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        SubjectText = findViewById(R.id.SubjectText);
        MessageText = findViewById(R.id.MessageText);
        //onNewIntent(getIntent());
        intent1 = getIntent();
        SubjectText.setText(intent1.getStringExtra("Subject"));
        MessageText.setText(intent1.getStringExtra("Message"));

        //intent2 = getIntent();
        //SubjectText.setText(intent2.getStringExtra("Subject2"));
        //MessageText.setText(intent2.getStringExtra("Message2"));
    }
}