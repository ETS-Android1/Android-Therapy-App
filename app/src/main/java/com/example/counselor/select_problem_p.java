package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.counselor.Constants.authKey;

public class select_problem_p extends AppCompatActivity {
    private RadioButton rbtn, rrr;
    private RadioGroup radioGroup;
    private Button applybtn;
    private TextView t;
    private EditText n;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_problem_p);

        sessionManager = new SessionManager(this);

        applybtn = findViewById(R.id.apply);
        radioGroup = findViewById(R.id.radiogroup);
        t = findViewById(R.id.test);
        n = findViewById(R.id.none);

        rrr = findViewById(R.id.other);

        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioID = radioGroup.getCheckedRadioButtonId();
                rbtn = findViewById(radioID);

               final  String chosenProblem = rbtn.getText().toString();
               PHPRequest p = new PHPRequest();
               ContentValues values = new ContentValues();
               values.put("username", getIntent().getStringExtra("username"));
               values.put("password", getIntent().getStringExtra("password"));
               values.put("type", "Patient");
               values.put("problem", chosenProblem);
               p.RequestWithParameters(select_problem_p.this, "reg.php", values);


                sessionManager.createSession(getIntent().getStringExtra("username"));

                createChatUser();
                LoginToCometChat();

                setlayout();





            }
        });



    }

    public void checkButton(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();
        rbtn = findViewById(radioID);
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
        String TAG = "select_problem_p";

        CometChat.login(UID, authKey, new CometChat.CallbackListener<User>() {

            @Override
            public void onSuccess(User user) {
                Log.d(TAG, "Login Successful : " + user.toString());
                Intent intent = new Intent(select_problem_p.this , homeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(CometChatException e) {
                Log.d(TAG, "Login failed with exception: " + e.getMessage());
            }
        });

    }

    public void RequestWithParameters(final Activity a, String file, ContentValues params) {

        OkHttpClient client = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();

        for(String key:params.keySet()){
            builder.add(key, params.getAsString(key));
        }


        Request request = new Request.Builder().url("https://lamp.ms.wits.ac.za/home/s2094007/" + file).post(builder.build()).build();



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull okhttp3.Call call, Response response) throws IOException {
                final String responseData = response.body().string();



                System.out.println(responseData);



            }

            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                e.printStackTrace();
            }


        });


    }
}
