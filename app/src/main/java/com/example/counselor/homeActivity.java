package com.example.counselor;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.models.User;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

import constant.StringContract;

public class homeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager sessionManager;

    private TextView helping,us;
    private ImageView settings, profile,chat, academics, family, mental, romance;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home2);
        sessionManager = new SessionManager(this);
        chat = findViewById(R.id.newchat);
        NavigationView navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);

        sessionManager.checkLogin();
        settings = findViewById(R.id.goToSettings);
        us = findViewById(R.id.username);
        helping = findViewById(R.id.helpingyou);
        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail()
                ;
//        String isPatient = user.get(n.isPatient);
//        string will either be "Therapist" or "Patient", all letters exactly

        String username = user.get(n.USERNAME);
        us.setText(username);

        academics = findViewById(R.id.academics);
        academics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGroup("academics");
            }
        });

        family = findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGroup("family");
            }
        });

        romance = findViewById(R.id.romance);
        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGroup("romance");
            }
        });

        mental = findViewById(R.id.mental);
        mental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGroup("mental");
            }
        });



        profile = findViewById(R.id.profile);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayout();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayoutUsers();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayoutse();
            }
        });

        View home = findViewById(R.id.goToHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homeActivity.this, homeActivity.class);
                startActivity(i);
            }
        });





        /*HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.USERNAME);
        logout.setText(username);
*/






    }

    private void setGroup(String guid) {

        User user = CometChat.getLoggedInUser();

        Intent intent = new Intent(this, Group_chat.class);
        intent.putExtra(StringContract.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_USER);
        intent.putExtra(StringContract.IntentStrings.NAME,user.getName());
        intent.putExtra(StringContract.IntentStrings.GUID,guid);
        intent.putExtra(StringContract.IntentStrings.AVATAR,user.getAvatar());
        startActivity(intent);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.userList:
                setlayout();
                break;



        }


        return true;
    }

    public void setlayout() {
        Intent intent = new Intent(this, Conversations.class);
        startActivity(intent);
    }
    public void setlayoutUsers() {
        Intent intent = new Intent(this, UserList.class);
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
