package com.example.counselor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.shimmer.ShimmerFrameLayout;;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.UsersRequest;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.cometchat.pro.uikit.CometChatUserList;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import constant.StringContract;
import listeners.OnItemClickListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import screen.CometChatUserListScreen;
import utils.FontUtils;

public class UserList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager sessionManager;
    private ImageView settings, profile,chat;
    UsersRequest usersRequest;
    private CometChatUserList rvUserList;
    String username;
    private TextView title;
    private ShimmerFrameLayout shimmerFrameLayout;
    private RelativeLayout rlSearchBox;
    private LinearLayout noUserLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        rvUserList = findViewById(com.cometchat.pro.uikit.R.id.rv_user_list);
        rlSearchBox= findViewById(com.cometchat.pro.uikit.R.id.rl_search_box);
        shimmerFrameLayout= findViewById(com.cometchat.pro.uikit.R.id.shimmer_layout);
        title = findViewById(com.cometchat.pro.uikit.R.id.tv_title);
        title.setText("Connect");
        title.setTypeface(FontUtils.getInstance(this).getTypeFace(FontUtils.robotoMedium));
        noUserLayout = findViewById(com.cometchat.pro.uikit.R.id.no_user_layout);
        rvUserList.clear();


        sessionManager = new SessionManager(this);
        chat = findViewById(R.id.newchat);
        NavigationView navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);

        sessionManager.checkLogin();
        settings = findViewById(R.id.goToSettings);
        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail();
        username = user.get(n.USERNAME);

        GetContacts();



        profile = findViewById(R.id.profile);

        View home = findViewById(R.id.goToHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserList.this, homeActivity.class);
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

        });
    }

    private void GetContacts() {

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2094007/contact.php").newBuilder();
        urlBuilder.addQueryParameter("username",username);


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
                    UserList.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            try {
                                processJson(respond);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.therapistprofile:
                setlayouttherapistdet();

                break;
            case R.id.myprofile:
                setlayoutpro();
                break;
            case R.id.policy:
                setlayou();
                break;



        }


        return true;
    }
    public void setlayou() {
        Intent intent = new Intent(this, privacy.class);
        startActivity(intent);
    }
    public void setlayouttherapistdet(){
        Intent intent = new Intent(this, therapistDetails.class);
        startActivity(intent);
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

    private void getUser(String person) {
        String TAG = "UserList";
        usersRequest = new UsersRequest.UsersRequestBuilder()
                .setLimit(1)
                .setSearchKeyword(person)
                .build();

        usersRequest.fetchNext(new CometChat.CallbackListener<List<User>>() {
            @Override
            public void onSuccess(List <User> list) {
                Log.d(TAG, "User list received: " + list.size());
                stopHideShimmer();
                rvUserList.add(list.get(0));

                if (list.size()==0) {
                    noUserLayout.setVisibility(View.VISIBLE);
                    rvUserList.setVisibility(View.GONE);
                } else {
                    rvUserList.setVisibility(View.VISIBLE);
                    noUserLayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void onError(CometChatException e) {
                Log.d(TAG, "User list fetching failed with exception: " + e.getMessage());
            }
        });
    }

    private void processJson (String json) throws JSONException {
        JSONArray ja  = new JSONArray(json);
        // store the things in an array;


        for(int i=0; i<ja.length(); i++){
            JSONObject jo = ja.getJSONObject(i);
            String person = jo.getString("username");
            if(person.equals("null")){
                stopHideShimmer();
                noUserLayout.setVisibility(View.VISIBLE);
                rvUserList.setVisibility(View.GONE);
            }
            else{
                getUser(person);
            }
        }

    }

    private void stopHideShimmer() {
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        rlSearchBox.setVisibility(View.VISIBLE);

    }

}
