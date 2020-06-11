package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cometchat.pro.core.AppSettings;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.counselor.Constants.appID;
import static com.example.counselor.Constants.authKey;
import static com.example.counselor.Constants.region;

public class MainActivity extends AppCompatActivity {
    private TextView regy,inc;
    private Button bt;
    private EditText user, passs;
    SessionManager sessionManager;
    private static final String TAG = "MainActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        sessionManager = new SessionManager(this);

        AppSettings appSettings=new AppSettings.AppSettingsBuilder().subscribePresenceForAllUsers().setRegion(region).build();

        CometChat.init(this, appID,appSettings, new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String successMessage) {
                Log.d(TAG, "Initialization completed successfully");
            }
            @Override
            public void onError(CometChatException e) {
                Log.d(TAG, "Initialization failed with exception: " + e.getMessage());
            }
        });

        user = findViewById(R.id.username_login);
        passs = findViewById(R.id.password_login);
        regy = this.findViewById(R.id.register_txt);
        bt = findViewById(R.id.login_btn);
        inc = findViewById(R.id.incorrect);


        regy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayout();


            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OkHttpClient client = new OkHttpClient();


                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2094007/log.php").newBuilder();
                urlBuilder.addQueryParameter("username", user.getText().toString());
                String url = urlBuilder.build().toString();
                //String url = "https://lamp.ms.wits.ac.za/home/s2094007/logg.php";


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

                        final String responseData = response.body().string();
                        JSONArray result = null;

                        if(responseData!= null) {
                            try {
                                result = new JSONArray(responseData);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            System.out.println("nothing");
                        }

                        final JSONArray finalResult = result;
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {


                                    if(finalResult != null){
                                        JSONObject a = finalResult.getJSONObject(0);

                                        if(a.getString("message")=="success"){
                                            setlay();
                                        }
                                        else {
                                            inc.setText("incorrect login details");
                                        }
                                    }
                                    else{
                                        inc.setText("Something went wrong, try again later!");
                                    }




                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });

                    }
                });
            }
        });



    }

    public void setlayout() {
        Intent intent = new Intent(this, type_of_regi.class);
        startActivity(intent);
    }
    private  void setlay(){
        sessionManager.createSession(user.getText().toString());
        LoginToCometChat();
    }


    void LoginToCometChat(){
        String UID = user.getText().toString(); // Replace with the UID of the user to login
        String TAG = "MainActivity";


        CometChat.login(UID, authKey, new CometChat.CallbackListener<User>() {

            @Override
            public void onSuccess(User user) {
                Log.d(TAG, "Login Successful : " + user.toString());
                Intent intent = new Intent(MainActivity.this, homeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(CometChatException e) {
                Log.d(TAG, "Login failed with exception: " + e.getMessage());
            }
        });

    }




}
