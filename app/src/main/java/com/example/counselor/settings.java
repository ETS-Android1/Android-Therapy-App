package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;

import java.util.HashMap;

public class settings extends AppCompatActivity {
    private Button log;
    SessionManager sessionManager;
    private String authToken = "AUTH_TOKEN";
    private String TAG = "settings";
    private TextView insta, facebook , email,dell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        dell = findViewById(R.id.deleteacc);
        insta = findViewById(R.id.insta);
        facebook = findViewById(R.id.insta3);
        facebook.setMovementMethod(LinkMovementMethod.getInstance());
        insta.setMovementMethod(LinkMovementMethod.getInstance());
        log = findViewById(R.id.button);
        PHPRequest phpRequest = new PHPRequest();
        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail();
        String username = user.get(n.USERNAME);
        ContentValues values = new ContentValues();
        values.put("username", username);

        dell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               phpRequest.RequestWithParameters(settings.this,"dele.php", values );
                sessionManager.logout();
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();


            }
            });
    }

}
