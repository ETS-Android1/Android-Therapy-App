package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView regy;
    private Button bt;
    private EditText user, passs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        user = findViewById(R.id.username_login);
        passs = findViewById(R.id.password_login);
        regy = this.findViewById(R.id.register_txt);
        bt = findViewById(R.id.login_btn);


        regy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayout();


            }
        });


    }

    public void setlayout() {
        Intent intent = new Intent(this, type_of_regi.class);
        startActivity(intent);
    }

   /* public Boolean getDetails(JSONArray json) throws JSONException {
        JSONArray ja = new JSONArray(json);
        // store the things in an array;
        String username = user.getText().toString();
        String password = passs.getText().toString();


        boolean TF = false;
        for (int i = 0; i < ja.length(); i++) {

            JSONObject jo = ja.getJSONObject(i);
            if (username.equals(jo.getString("PERSON_USERNAME")) && passs.equals(jo.getString("PERSON_PASSWORD"))) {

                TF = true;
            }


        }
        return TF;
    }*/



}
