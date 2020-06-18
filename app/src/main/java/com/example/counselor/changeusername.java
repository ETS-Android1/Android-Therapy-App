package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class changeusername extends AppCompatActivity {
    private EditText use, conuse;
    private Button goahead;
    private TextView mat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeusername);
        use = findViewById(R.id.changeusername);
        conuse = findViewById(R.id.confirmchangeusername);
        goahead = findViewById(R.id.btnChangeusername);
        mat = findViewById(R.id.textView18);
        goahead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conuse.equals(use)){
                    // go ahead with the request



                    //then go to main

                }
                else{
                    mat.setText("Make sure that your inputs match");
                }
            }
        });



    }
}