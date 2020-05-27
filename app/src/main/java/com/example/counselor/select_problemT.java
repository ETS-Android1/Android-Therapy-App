package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class select_problemT extends AppCompatActivity {
    private CheckBox fam,aca, rm;
    private Button ap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_problem_t);
        fam = findViewById(R.id.family);
        aca = findViewById(R.id.academics);
        rm = findViewById(R.id.RM);
        ap = findViewById(R.id.goOn);
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
                therapistValues.put("personID", 10);
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
        else if(aca.isChecked()){
            longString = longString + "academics ";
        }

        else if(rm.isChecked()){
            longString = longString + "romantic_relationships ";
        }


        return longString;
    }
    public void setlayout(){
        Intent intent = new Intent(this, homeActivity.class);
        startActivity(intent);

    }
}
