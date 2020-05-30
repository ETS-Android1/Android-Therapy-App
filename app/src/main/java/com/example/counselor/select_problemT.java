package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class select_problemT extends AppCompatActivity {
    private CheckBox fam,aca, rm;
    private Button ap;
    private TextView tx5;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_problem_t);

        sessionManager = new SessionManager(this);

        fam = findViewById(R.id.family);
        aca = findViewById(R.id.academics);
        rm = findViewById(R.id.RM);
        ap = findViewById(R.id.goOn);
        tx5 = findViewById(R.id.textView5);
        register_therapist r = new register_therapist();
        tx5.setText("hello" + r.us);


        ap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_therapist r = new register_therapist();


                setlayout();
                PHPRequest toPerson = new PHPRequest();
                ContentValues PersonValues = new ContentValues();
                PersonValues.put("username", r.us);
                PersonValues.put("password", r.pa);

                PersonValues.put("type", "therapist");
                toPerson.RequestWithParameters(select_problemT.this, "register.php",PersonValues);
                // get the person-Id


                MainActivity m = new MainActivity();







                PHPRequest person = new PHPRequest();
                ContentValues justValue = new ContentValues();







                PHPRequest toTherapist = new PHPRequest();

                ContentValues therapistValues = new ContentValues();
                    // pass in the person id
                therapistValues.put("personID", m.userID);
                m.userID ++;
                therapistValues.put("name", r.first);
                therapistValues.put("surname", r.last);
                therapistValues.put("id_no", r.IDD);
                therapistValues.put("email", r.Mail);
                therapistValues.put("noOfPatients",0);
                therapistValues.put("problem", check());
                toTherapist.RequestWithParameters(select_problemT.this, "registerTherapist.php", therapistValues);

                sessionManager.createSession(r.us);









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


        return longString;
    }
    public void setlayout(){
        Intent intent = new Intent(this, homeActivity.class);
        startActivity(intent);

    }




}
