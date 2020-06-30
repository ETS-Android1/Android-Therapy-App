package com.example.counselor;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.models.User;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import constant.StringContract;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class homeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager sessionManager;
    Button logout;
    private ViewFlipper viewFlipper;
    private DrawerLayout draw;
    //private View toggle;

    private TextView helping,us, oops;
    private ImageView settings, profile,chat, family, academics, romance, mental,toggle;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home2);
        sessionManager = new SessionManager(this);
        chat = findViewById(R.id.newchat);
        String typee = "Therapis";

            NavigationView navigationView = findViewById(R.id.nav);
            navigationView.setNavigationItemSelectedListener(this);





        sessionManager.checkLogin();
        settings = findViewById(R.id.goToSettings);
        us = findViewById(R.id.username);
//        toggle = findViewById(R.id.contacts);


        helping = findViewById(R.id.helpingyou);
        SessionManager n = new SessionManager(this);
        HashMap<String, String> user = n.getUserDetail();


        String isPatient = user.get(n.isPatient);
       if(isPatient.equals("Patient")){
            helping.setText("Let us motivate you with these, Stay motivated");

        }
        else{
            helping.setText("Here are some of the wise words you can transfer to you Patients");
        }

//        string will either be "Therapist" or "Patient", all letters exactly

        String username = user.get(n.USERNAME);
        us.setText(username);


        academics = findViewById(R.id.academics);
        academics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGroup("academics");
            }
        });

        family = findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGroup("family");
            }
        });

        romance = findViewById(R.id.romance);
        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGroup("romance");
            }
        });

        mental = findViewById(R.id.mental);
        mental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGroup("mental");
            }
        });



        profile = findViewById(R.id.profile);
        int academicsimage[] = {R.drawable.academic1,R.drawable.academic2,R.drawable.academic3,R.drawable.academic4,R.drawable.academic5,R.drawable.academic6,R.drawable.academic7, R.drawable.academic8};
        int mentalimage[] = {R.drawable.mental1,R.drawable.mental2,R.drawable.mental3,R.drawable.mental4,R.drawable.mental5,R.drawable.mental6,R.drawable.mental7,R.drawable.mental8,R.drawable.mental9};
        int familyimage[] = {R.drawable.family1,R.drawable.family2,R.drawable.family3,R.drawable.family4,R.drawable.family5,R.drawable.family6};
        int romanceimage[] = {R.drawable.romance1,R.drawable.romance2,R.drawable.romance3,R.drawable.romance4,R.drawable.romance5,R.drawable.romance6,R.drawable.romance7};
        int elseimage[] = {R.drawable.else1,R.drawable.else2,R.drawable.else3,R.drawable.else4};
        viewFlipper = findViewById(R.id.theflip);


       /* for(int i =0 ; i < academicsimage.length; i ++){
            flipper(academicsimage[i]);
        }*/

       if(isPatient.equals("Patient")){
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
                    homeActivity.this.runOnUiThread(new Runnable() {

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
                                    for(int i =0 ; i < familyimage.length; i ++) {
                                        flipper(familyimage[i]);
                                    }
                                }
                                else if(ppp.equals("academics")){
                                    for(int i =0 ; i < academicsimage.length; i ++) {
                                        flipper(academicsimage[i]);
                                    }
                                }
                                else if(ppp.equals("romance")){
                                    for(int i =0 ; i < romanceimage.length; i ++){
                                        flipper(romanceimage[i]);}
                                }
                                else{
                                    for(int i =0 ; i < mentalimage.length; i ++){
                                        flipper(mentalimage[i]);}
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                }
            }
        });}
       else{
           for(int i =0 ; i < elseimage.length; i ++) {
               flipper(elseimage[i]);
           }
           // just display some quotes

       }



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

        View home = findViewById(R.id.goToHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homeActivity.this, homeActivity.class);
                startActivity(i);
            }
        });





        /*HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(sessionManager.USERNAME);
        logout.setText(username);
*/






    }

    private void setGroup(String guid) {

        User user = CometChat.getLoggedInUser();

        Intent intent = new Intent(this, Group_chat.class);
        intent.putExtra(StringContract.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_USER);
        intent.putExtra(StringContract.IntentStrings.NAME,user.getName());
        intent.putExtra(StringContract.IntentStrings.GUID,guid);
        intent.putExtra(StringContract.IntentStrings.AVATAR,user.getAvatar());
        startActivity(intent);

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
    public void setlayouttherapistdet(){
        Intent intent = new Intent(this, therapistDetails.class);
        startActivity(intent);
    }

    public void setlayou() {
        Intent intent = new Intent(this, privacy.class);
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
    public void flipper(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(600000);
        viewFlipper.startFlipping();
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);



    }
    public String getpr(String json ) throws JSONException {
        JSONArray ja = new JSONArray(json);
        JSONObject jo = ja.getJSONObject(0);
        String p = jo.getString("PAT_PROBLEM");
        return p;
    }

}
