package com.example.counselor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class register_patient extends AppCompatActivity {
    private Button rP;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_patient);
        final MainActivity a = new MainActivity();
        rP = findViewById(R.id.register_btnP);
        rP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayout();
            }
        });



    }
    public void setlayout(){
        Intent intent = new Intent(this , select_problem_p.class);
        startActivity(intent);
    }

}
