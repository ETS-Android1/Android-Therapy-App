package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

public class therapistDetails extends AppCompatActivity {
    TextView u, f, l, i,s, e,six;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_details);
        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail();
        String theirTherapist = user.get(n.USERNAME);
        u = findViewById(R.id.hunaam);
        f = findViewById(R.id.hfnaam);
        l = findViewById(R.id.textView21);
        i = findViewById(R.id.textView20);
        s = findViewById(R.id.textView19);
        e = findViewById(R.id.hmail);
        six = findViewById(R.id.textView6);


        String isPatient = user.get(n.isPatient);
        if(isPatient.equals("Patient")){
            six.setText("More about your Therapist");

        }
        else{
            six.setText("My Counselling profile");
        }



        //u = findViewById(R.id.hunaam);
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2094007/final.php").newBuilder();
        urlBuilder.addQueryParameter("username",theirTherapist) ;



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
                    therapistDetails.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            try {
                                processJson(respond);
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }


                        }
                    });
                }
            }
        });





    }
    public void processJson (String json) throws JSONException {
        JSONArray ja  = new JSONArray(json);
        // store the things in an array;


//        boolean TF = false;
        JSONObject jo = ja.getJSONObject(0);
        String Husername  = jo.getString("PER_USERNAME");
        String Hfirstname  = jo.getString("THER_FNAME");
        String Hlastname  = jo.getString("THER_LNAME");
        String Hemail  = jo.getString("THER_EMAIL");
        String Hidno  = jo.getString("THER_ID_NO");
        String Hproblem  = jo.getString("THER_PROBLEM");
        u.setText(Husername);
        f.setText(Hfirstname);
        l.setText(Hlastname);
        i.setText(Hidno);
        s.setText(Hproblem);
        e.setText(Hemail);



    //    return test;


    }


}