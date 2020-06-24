package com.example.counselor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class profile extends AppCompatActivity {
    SessionManager s ;

    private TextView userna, probs , logout, cpass;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        userna = findViewById(R.id.userdet);

        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail();
        String username = user.get(n.USERNAME);
        userna.setText(username);
        probs = findViewById(R.id.userdet3);
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2094007/problem.php").newBuilder();
        urlBuilder.addQueryParameter("username",username) ;



        String url =urlBuilder.build().toString();


        Request request = new Request.Builder()
                .url(url)
                .build();
        // inc.setText("not too far");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override

            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    final String respond = response.body().string();
                    profile.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            try {
                                getpr(respond);
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                String ppp= getpr(respond);
                                if(ppp.equals("family")){
                                    probs.setText("Family");
                                }
                                else if(ppp.equals("academics")){
                                    probs.setText("Academics");
                                }
                                else if(ppp.equals("romance")){
                                    probs.setText("Romance");
                                }
                                else{
                                    //mental
                                    probs.setText("mental");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                }
            }
        });

        SessionManager sessionManage = new SessionManager(profile.this);
        logout = findViewById(R.id.userdlet);
        cpass = findViewById(R.id.userdet4);
        s = new SessionManager(profile.this);
        s.checkLogin();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s.logout2();


            }
        });
        cpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setlayoutpass();
            }
        });

    }
    public void setlayoutusern() {
        Intent intent = new Intent(this, changeusername.class);
        startActivity(intent);
    }
    public void setlayoutpass() {
        Intent intent = new Intent(this, changepassword.class);
        startActivity(intent);
    }
    public String getpr(String json ) throws JSONException {
        JSONArray ja = new JSONArray(json);
        JSONObject jo = ja.getJSONObject(0);
        String p = jo.getString("PAT_PROBLEM");
        return p;
    }




}
