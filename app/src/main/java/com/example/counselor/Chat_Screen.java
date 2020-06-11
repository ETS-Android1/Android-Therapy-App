package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.cometchat.pro.constants.CometChatConstants;

import java.util.HashMap;

import constant.StringContract;
import screen.messagelist.CometChatMessageScreen;

public class Chat_Screen extends AppCompatActivity {
    Fragment fragment = new CometChatMessageScreen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__screen);

        if (getIntent()!=null) {
            Bundle bundle = new Bundle();

            bundle.putString(StringContract.IntentStrings.AVATAR, getIntent().getStringExtra(StringContract.IntentStrings.AVATAR));
            bundle.putString(StringContract.IntentStrings.NAME, getIntent().getStringExtra(StringContract.IntentStrings.NAME));
            bundle.putString(StringContract.IntentStrings.TYPE,getIntent().getStringExtra(StringContract.IntentStrings.TYPE));

            if (getIntent().hasExtra(StringContract.IntentStrings.TYPE)&&
                    getIntent().getStringExtra(StringContract.IntentStrings.TYPE).equals(CometChatConstants.RECEIVER_TYPE_USER)) {

                bundle.putString(StringContract.IntentStrings.UID, getIntent().getStringExtra(StringContract.IntentStrings.UID));
                bundle.putString(StringContract.IntentStrings.STATUS, getIntent().getStringExtra(StringContract.IntentStrings.STATUS));

            }else {
                bundle.putString(StringContract.IntentStrings.GUID, getIntent().getStringExtra(StringContract.IntentStrings.GUID));
                bundle.putString(StringContract.IntentStrings.GROUP_OWNER,getIntent().getStringExtra(StringContract.IntentStrings.GROUP_OWNER));
            }
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.chatFrame, fragment).commit();
        }

    }
}
