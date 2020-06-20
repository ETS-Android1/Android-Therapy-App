package com.example.counselor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.models.Conversation;
import com.cometchat.pro.models.Group;
import com.cometchat.pro.models.User;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

import constant.StringContract;
import listeners.OnItemClickListener;
import screen.CometChatConversationListScreen;
import screen.messagelist.CometChatMessageListActivity;

public class Conversations extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView settings, profile,chat;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

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

        View home = findViewById(R.id.goToHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Conversations.this, homeActivity.class);
                startActivity(i);
            }
        });

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


        CometChatConversationListScreen.setItemClickListener(new OnItemClickListener<Conversation>() {
            @Override
            public void OnItemClick(Conversation conversation, int position) {

                Intent intent = new Intent(Conversations.this, CometChatMessageListActivity.class);
                intent.putExtra(StringContract.IntentStrings.TYPE,conversation.getConversationType());
                if (conversation.getConversationType().equals(CometChatConstants.CONVERSATION_TYPE_GROUP))
                {
                    intent.putExtra(StringContract.IntentStrings.NAME,((Group)conversation.getConversationWith()).getName());
                    intent.putExtra(StringContract.IntentStrings.GUID,((Group)conversation.getConversationWith()).getGuid());
                    intent.putExtra(StringContract.IntentStrings.GROUP_OWNER,((Group)conversation.getConversationWith()).getOwner());
                    intent.putExtra(StringContract.IntentStrings.AVATAR,((Group)conversation.getConversationWith()).getIcon());

                }
                else
                {
                    intent.putExtra(StringContract.IntentStrings.NAME,((User)conversation.getConversationWith()).getName());
                    intent.putExtra(StringContract.IntentStrings.UID,((User)conversation.getConversationWith()).getUid());
                    intent.putExtra(StringContract.IntentStrings.AVATAR,((User)conversation.getConversationWith()).getAvatar());
                    intent.putExtra(StringContract.IntentStrings.STATUS,((User)conversation.getConversationWith()).getStatus());
                }
                startActivity(intent);
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
