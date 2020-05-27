package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class select_problemT extends AppCompatActivity {
    private CheckBox fam,aca, rm;
    private Button ap;
    private TextView tx5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_problem_t);
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
                PHPRequest toTherapist = new PHPRequest();
                ContentValues therapistValues = new ContentValues();

                therapistValues.put("name", r.first);
                therapistValues.put("surname", r.last);
                therapistValues.put("id_no", r.IDD);
                therapistValues.put("email", r.Mail);
                therapistValues.put("noOfPatients",0);
                therapistValues.put("problem", check());
                toTherapist.RequestWithParameters(select_problemT.this, "registerTherapist.php", therapistValues);









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
