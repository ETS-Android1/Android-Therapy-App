package com.example.counselor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class profile extends AppCompatActivity {

    private TextView userna, probs , cuser, cpass;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        userna = findViewById(R.id.userdet);

        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail();
        String username = user.get(n.USERNAME);
        userna.setText(username);
        cuser = findViewById(R.id.userdlet);
        cpass = findViewById(R.id.userdet4);
        cuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setlayoutusern();
            }
        });
        cpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setlayoutpass();
            }
        });

    }
    public void setlayoutusern() {
        Intent intent = new Intent(this, changeusername.class);
        startActivity(intent);
    }
    public void setlayoutpass() {
        Intent intent = new Intent(this, changepassword.class);
        startActivity(intent);
    }




}
