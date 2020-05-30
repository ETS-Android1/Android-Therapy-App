package com.example.counselor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class homeActivity extends AppCompatActivity {
    SessionManager sessionManager;
    Button logout;
    private Button chat;
    private TextView helping,us;
    private ImageView settings, profile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home2);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        settings = findViewById(R.id.goToSettings);
        us = findViewById(R.id.username);
        helping = findViewById(R.id.helpingyou);
        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail();
        String username = user.get(n.USERNAME);
        us.setText(username);

        profile = findViewById(R.id.profile);
        chat = findViewById(R.id.goToChat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayout();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayoutpro();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayoutse();
            }
        });





        /*HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.USERNAME);
        logout.setText(username);
*/






    }
    public void setlayout() {
        Intent intent = new Intent(this, chat.class);
        startActivity(intent);
    }
    public void setlayoutse() {
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }
    public void setlayoutpro() {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

}
