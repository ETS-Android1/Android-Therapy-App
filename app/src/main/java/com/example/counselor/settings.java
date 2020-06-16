package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;

public class settings extends AppCompatActivity {
    private Button log;
    SessionManager sessionManager;
    private String authToken = "AUTH_TOKEN";
    private String TAG = "settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        log = findViewById(R.id.button);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();


            }
            });
    }

}
