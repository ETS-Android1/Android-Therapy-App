package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    private TextView regy,inc;
    private Button bt;
    private EditText user, passs;
    SessionManager sessionManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        sessionManager = new SessionManager(this);

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


                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2094007/logg.php").newBuilder();
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
                        if(response.isSuccessful()){
                            final String respond = response.body().string();
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        getDetails(respond);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        if(getDetails(respond)){
                                            setlay();
                                        }
                                        else{
                                            inc.setText("incorrect login details");
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
        });



    }

    public void setlayout() {
        Intent intent = new Intent(this, type_of_regi.class);
        startActivity(intent);
    }
    private  void setlay(){
        sessionManager.createSession(user.getText().toString());
        Intent intent = new Intent(this, homeActivity.class);
        startActivity(intent);
    }

    public Boolean getDetails(String json) throws JSONException {



        JSONArray ja = new JSONArray(json);




        // store the things in an array;
        String username = user.getText().toString();
        String password = passs.getText().toString();


        boolean TF = false;

            for (int i = 0; i < ja.length(); i++) {

                JSONObject jo = ja.getJSONObject(i);
                if (username.equals(jo.getString("PERSON_USERNAME")) && password.equals(jo.getString("PERSON_PASSWORD"))) {
                    final String type = jo.getString("PERSON_TYPE");
                    TF = true;
                }


            }


        return TF;
    }







}
