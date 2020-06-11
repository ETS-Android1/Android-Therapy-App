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

        log = findViewById(R.id.button);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();

//                if (CometChat.getLoggedInUser()==null){
//                    CometChat.login(authToken, new CometChat.CallbackListener<User>()
//
//                    @Override
//                    public void onSuccess(User user) {
//                        Log.d(TAG, "Login Successful : " + user.toString());
//                    }
//                    @Override
//                    public void onError(CometChatException e) {
//                        Log.d(TAG, "Login failed with exception: " + e.getMessage());
//                    }
//                    );
//                }
//                else {
//                    // user already logged-in perform your action
//                }


            }
            });
    }

}
