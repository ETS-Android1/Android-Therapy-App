package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class changepassword extends AppCompatActivity {
    private EditText pass, conpass;
    private Button gopass;
    private TextView make;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        pass = findViewById(R.id.changepassword);
        conpass = findViewById(R.id.confirmchangepassword);
        gopass = findViewById(R.id.btnchangepass);
        make = findViewById(R.id.makesure);
        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail();
        String username = user.get(n.USERNAME);
        gopass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conpass.getText().toString().equals(pass.getText().toString())){
                    // go ahead with the request
                    OkHttpClient client = new OkHttpClient();
                    HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2094007/changepass.php").newBuilder();
                    urlBuilder.addQueryParameter("username", username);
                    urlBuilder.addQueryParameter("password", conpass.getText().toString());


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
                                changepassword.this.runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {

                                        Context context = getApplicationContext();
                                        CharSequence text = "Password changed successfully";
                                        int duration = Toast.LENGTH_LONG;

                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                        //setmain layout
                                        setlay();




                                    }
                                });
                            }
                        }
                    });


                    //then go to main

                }
                else{
                    make.setText("Make sure that your inputs match");
                }
            }
        });




    }
    public void setlay(){

        Intent intent = new Intent(this, homeActivity.class);
        startActivity(intent);

    }
}