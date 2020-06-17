package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.Group;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import constant.StringContract;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import screen.messagelist.CometChatMessageScreen;

public class Group_chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        setUp();



    }

    private void setUp() {

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2094007/detail.php").newBuilder();
        urlBuilder.addQueryParameter("username", getIntent().getStringExtra(StringContract.IntentStrings.NAME));

        String url =urlBuilder.build().toString();


        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override

            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    final String respond = response.body().string();
                    Group_chat.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            try {
                                if(processJson(respond).equals("Patient")){
                                    setLayout("Patient");
                                }
                                else{
                                    setLayout("Therapist");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                }
            }
        });

    }

    private String processJson(String json) throws JSONException {

        JSONArray ja  = new JSONArray(json);
        // store the things in an array;

        JSONObject jo = ja.getJSONObject(0);
        String test = jo.getString("message");


        return test;

    }

    private void setLayout(String role) {
        String TAG = "Group_Chat";


        CometChat.joinGroup(getIntent().getStringExtra(StringContract.IntentStrings.GUID), CometChatConstants.GROUP_TYPE_PUBLIC, "", new CometChat.CallbackListener<Group>() {
            @Override
            public void onSuccess(Group joinedGroup) {
                Log.d(TAG, joinedGroup.toString());
            }
            @Override
            public void onError(CometChatException e) {
                Log.d(TAG, "Group joining failed with exception: " + e.getMessage());
            }
        });

        if(role.equals("Therapist")){
            CometChat.updateGroupMemberScope(getIntent().getStringExtra(StringContract.IntentStrings.NAME), getIntent().getStringExtra(StringContract.IntentStrings.GUID), CometChatConstants.SCOPE_ADMIN, new
                    CometChat.CallbackListener<String>() {
                        @Override
                        public void onSuccess(String successMessage) {
                            Log.d(TAG, "User scope updated successfully");
                        }

                        @Override
                        public void onError(CometChatException e) {
                            Log.d(TAG, "User scope update failed with exception: " + e.getMessage());
                        }
                    });
        }
        else{
            System.out.println("nothing");
        }

        Bundle bundle = new Bundle();
        Fragment chatFragment=new CometChatMessageScreen();

        bundle.putString(StringContract.IntentStrings.AVATAR, getIntent().getStringExtra(StringContract.IntentStrings.AVATAR));
        bundle.putString(StringContract.IntentStrings.NAME, getIntent().getStringExtra(StringContract.IntentStrings.NAME));
        bundle.putString(StringContract.IntentStrings.GUID, getIntent().getStringExtra(StringContract.IntentStrings.GUID));
        bundle.putString(StringContract.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_GROUP);
        chatFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.chatFrame,chatFragment).commit();
    }
}
