package com.example.counselor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.models.User;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

import constant.StringContract;
import listeners.OnItemClickListener;
import screen.CometChatUserListScreen;
import screen.messagelist.CometChatMessageListActivity;
import screen.messagelist.CometChatMessageScreen;

public class UserList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager sessionManager;
    private ImageView settings, profile,chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        sessionManager = new SessionManager(this);
        chat = findViewById(R.id.newchat);
        NavigationView navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);

        sessionManager.checkLogin();
        settings = findViewById(R.id.goToSettings);
        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail();
        String username = user.get(n.USERNAME);



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



        CometChatUserListScreen.setItemClickListener(new OnItemClickListener<User>() {

            @Override
            public void OnItemClick(User user, int position) {

                Intent intent = new Intent(UserList.this, Chat_Screen.class);
                intent.putExtra(StringContract.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_USER);
                intent.putExtra(StringContract.IntentStrings.NAME,user.getName());
                intent.putExtra(StringContract.IntentStrings.UID,user.getUid());
                intent.putExtra(StringContract.IntentStrings.AVATAR,user.getAvatar());
                intent.putExtra(StringContract.IntentStrings.STATUS,user.getStatus());
                startActivity(intent);

            }

            @Override
            public void OnItemLongClick(User var, int position) {
                super.OnItemLongClick(var, position);
            }
        });
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
