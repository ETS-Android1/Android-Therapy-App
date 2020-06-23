package com.example.counselor;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Integer.parseInt;

public class register_therapist extends AppCompatActivity {
    private EditText fname,Lname, ID,mail ,usern, pass, conpass;
    private Button registeerr;
    private TextView nom;
    public static String us,first,last,IDD,Mail,pa;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_therapist);
        fname = findViewById(R.id.f_name);
        Lname = findViewById(R.id.lname);
        ID = findViewById(R.id.id_no);
        mail = findViewById(R.id.email);
        usern = findViewById(R.id.username_registerT);
        nom = findViewById(R.id.password_not_matchP);
        pass = findViewById(R.id.password_registerT);
        conpass = findViewById(R.id.confirm_pass_regT);
        registeerr = findViewById(R.id.register_btnT);
        registeerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                first = fname.getText().toString();
                last = Lname.getText().toString();
                IDD = ID.getText().toString();
                Mail = mail.getText().toString();
                us = usern.getText().toString();
                pa = pass.getText().toString();
                if(pa.equals(conpass.getText().toString())){
                    setlayout();
                }
                else{
                    nom.setText("Passwords don't match");
                }


            }
        });






    }
    public void setlayout(){
        View t = findViewById(R.id.textView5);
        t.setVisibility(View.GONE);
        if(fname.getText().toString().equals("")||Lname.getText().toString().equals("")||ID.getText().toString().equals("")||mail.getText().toString().equals("")||usern.getText().toString().equals("")||pass.getText().toString().equals("")){
            t.setVisibility(View.VISIBLE);
        }
        else {
            Intent intent = new Intent(this, select_problemT.class);
            intent.putExtra("id_no", IDD);
            intent.putExtra("username", us);
            startActivity(intent);
        }

    }
}