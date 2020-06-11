package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.models.User;

import constant.StringContract;
import listeners.OnItemClickListener;
import screen.CometChatUserListScreen;
import screen.messagelist.CometChatMessageListActivity;
import screen.messagelist.CometChatMessageScreen;

public class UserList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);



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
}
