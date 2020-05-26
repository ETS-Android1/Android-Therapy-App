package com.example.counselor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class type_of_regi extends AppCompatActivity {
    private Button p;
    private Button T;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_of_reg);


     p = findViewById(R.id.choose_Pbtn);
     T = findViewById(R.id.choose_Tbtn);

     p.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             setlayoutp();
            //final String choice = "Patient";
         }
     });
    T.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setlayoutt();
            //public String choice = "Therapist";
        }
    });

    }

    public void setlayoutp(){
        Intent intent = new Intent(this ,register_patient.class );
        startActivity(intent);
    }
    public void setlayoutt(){
        Intent intent = new Intent(this , register_therapist.class);
        startActivity(intent);
    }
}
