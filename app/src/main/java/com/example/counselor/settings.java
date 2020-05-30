package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settings extends AppCompatActivity {
    private Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        log = findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logggout();

            }
        });
    }
    public void logggout(){
        SessionManager n = new SessionManager(this);
        n.logout();


    }
}
