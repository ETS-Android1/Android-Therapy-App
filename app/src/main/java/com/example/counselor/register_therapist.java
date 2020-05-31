package com.example.counselor;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
                // for the therapist table
                //auto increment number of patients
                // create a layout to make the therapist choose problems
               /* PHPRequest p = new PHPRequest();
                ContentValues values = new ContentValues();
                values.put("name", fname.getText().toString());
                values.put("surname",Lname.getText().toString());
                values.put("id_no",ID.getText().toString());
                values.put("email",mail.getText().toString());

               values.put("nofPatients",0);
                //values.put("",pass.getText().toString());
                    */
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



                //for the person table
                /*PHPRequest person = new PHPRequest();
                ContentValues personvalues = new ContentValues();
                personvalues.put("username", usern.getText().toString());
                personvalues.put("password" , pass.getText().toString());
                personvalues.put("type", "Therapist");
                */







            }
        });






    }
    public void setlayout(){
        Intent intent = new Intent(this, select_problemT.class);
        intent.putExtra("id_no", ID.getText().toString());
        startActivity(intent);

    }
}