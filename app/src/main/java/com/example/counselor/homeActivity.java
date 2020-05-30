package com.example.counselor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class homeActivity extends AppCompatActivity {
    SessionManager sessionManager;
    Button logout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        logout = findViewById(R.id.button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }

            });
        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.USERNAME);
        logout.setText(username);







    }

}
