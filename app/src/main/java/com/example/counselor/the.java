package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class the extends AppCompatActivity {
    private ImageView gender;
    private RelativeLayout prof;
    private LinearLayout sc;
    private TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the);
        prof = findViewById(R.id.profileLay);
        name = findViewById(R.id.nameInProfile);
        sc = findViewById(R.id.relscroll);

        // send req for names
        OkHttpClient client = new OkHttpClient();


        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2094007/getP.php").newBuilder();
        //urlBuilder.addQueryParameter("username", user.getText().toString());

       // SessionManager n = new SessionManager(this);
        //HashMap<String, String> user = n.getUserDetail();

       // String username = user.get(n.PAT_THE_ID);
        urlBuilder.addQueryParameter("therapist", "Family");





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
                    the.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                getDetails(respond);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                ArrayList<String> upUser = getDetails(respond);
                                    for(int i = 0 ; i < upUser.size() ; i ++){
                                    View user = getLayoutInflater().inflate(R.layout.activity_the,prof, false);
                                    TextView t = (TextView) user.findViewById(R.id.nameInProfile);
                                    String u = upUser.get(i);
                                    t.setText(u);

                                    sc.addView(user);

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
    public ArrayList getDetails(String json) throws JSONException {
        ArrayList<String> ussers = new ArrayList<>();


        JSONArray ja = new JSONArray(json);




        // store the things in an array;



        boolean TF = false;

        for (int i = 0; i < ja.length(); i++) {

            JSONObject jo = ja.getJSONObject(i);
            String element = jo.getString("PERSON_USERNAME");
            ussers.add(element);



        }


        return ussers;
    }


}
