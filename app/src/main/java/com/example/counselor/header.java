package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class header extends AppCompatActivity {
    TextView thename;
    ImageView gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        thename = findViewById(R.id.headerUser);
        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail();
        String username = user.get(n.USERNAME);
        thename.setText(username);




    }
}