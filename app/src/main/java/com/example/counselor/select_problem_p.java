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
import android.widget.ProgressBar;
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
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_problem_p);

        sessionManager = new SessionManager(this);

        applybtn = findViewById(R.id.apply);
        radioGroup = findViewById(R.id.radiogroup);
        t = findViewById(R.id.test);
        n = findViewById(R.id.none);
        loading = findViewById(R.id.progressBar);

        rrr = findViewById(R.id.other);

        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applybtn.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);

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


                sessionManager.createSession(getIntent().getStringExtra("username"), "Patient");

                createChatUser();

                setlayout();





            }
        });



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
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                applybtn.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                startActivity(intent);
            }

            @Override
            public void onError(CometChatException e) {
                Log.d(TAG, "Login failed with exception: " + e.getMessage());
                applybtn.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
            }
        });

    }

}
