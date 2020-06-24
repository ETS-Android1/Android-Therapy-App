package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;

import static com.example.counselor.Constants.authKey;
import static java.lang.Integer.parseInt;

public class select_problemT extends AppCompatActivity {
    private CheckBox fam,aca, rm,ada, mn;
    private Button ap;
    private TextView tx5;
    SessionManager sessionManager;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_problem_t);

        sessionManager = new SessionManager(this);
        mn = findViewById(R.id.ment);
        loading = findViewById(R.id.progressBar);
        fam = findViewById(R.id.family);
        aca = findViewById(R.id.academics);
        rm = findViewById(R.id.RM);
        ap = findViewById(R.id.goOn);
        ada= findViewById(R.id.others);
        tx5 = findViewById(R.id.textView5);
        register_therapist r = new register_therapist();


        ap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ap.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                register_therapist r = new register_therapist();


                ContentValues therapistValues = new ContentValues();
                PHPRequest p2 = new PHPRequest();
                therapistValues.put("username", r.us);
                therapistValues.put("password", r.pa);
                therapistValues.put("type", "Therapist");
                therapistValues.put("name", r.first);
                therapistValues.put("surname", r.last);
                therapistValues.put("id_no", getIntent().getStringExtra("id_no"));
                therapistValues.put("email", r.Mail);
                therapistValues.put("noOfPatients", "0");
                therapistValues.put("problem", check());
                p2.RequestWithParameters(select_problemT.this, "reg.php", therapistValues);

                sessionManager.createSession(r.us, "Therapist");

                createChatUser();

                setlayout();









            }
        });








    }

    public String check(){
        String longString="";
        if(fam.isChecked()){
            longString = longString + "family ";
        }
        if(aca.isChecked()){
            longString = longString + "academics ";
        }

        if(rm.isChecked()){
            longString = longString + "romantic_relationships ";
        }
        if(ada.isChecked()){
            longString = longString + "other ";
        }
        if(mn.isChecked()){
            longString = longString + "mental ";
        }


        return longString;
    }
    public void setlayout(){
        LoginToCometChat();
    }


    void createChatUser(){
        User user = new User();
        user.setUid(getIntent().getStringExtra("username")); // Replace with the UID for the user to be created
        user.setName(getIntent().getStringExtra("username")); // Replace with the name of the user

        CometChat.createUser(user, authKey, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                Log.d("createUser", user.toString());
            }

            @Override
            public void onError(CometChatException e) {
                Log.e("createUser", e.getMessage());
            }
        });
    }

    void LoginToCometChat(){
        String UID = getIntent().getStringExtra("username"); // Replace with the UID of the user to login
        String TAG = "select_problemT";


        CometChat.login(UID, authKey, new CometChat.CallbackListener<User>() {

            @Override
            public void onSuccess(User user) {
                Log.d(TAG, "Login Successful : " + user.toString());
                Intent intent = new Intent(select_problemT.this, homeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ap.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                startActivity(intent);
            }

            @Override
            public void onError(CometChatException e) {
                Log.d(TAG, "Login failed with exception: " + e.getMessage());
                ap.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
            }
        });

    }




}
