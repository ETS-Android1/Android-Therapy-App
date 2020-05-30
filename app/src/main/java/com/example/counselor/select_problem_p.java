package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class select_problem_p extends AppCompatActivity {
    private RadioButton rbtn, rrr;
    private RadioGroup radioGroup;
    private Button applybtn;
    private TextView t;
    private EditText n;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_problem_p);

        sessionManager = new SessionManager(this);

        applybtn = findViewById(R.id.apply);
        radioGroup = findViewById(R.id.radiogroup);
        t = findViewById(R.id.test);
        n = findViewById(R.id.none);

        rrr = findViewById(R.id.other);

        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioID = radioGroup.getCheckedRadioButtonId();
                rbtn = findViewById(radioID);
               final  String chosenProblem = rbtn.getText().toString();
               PHPRequest p = new PHPRequest();
                ContentValues values = new ContentValues();
                values.put("username", getIntent().getStringExtra("username"));
                values.put("password", getIntent().getStringExtra("password"));
                values.put("type", getIntent().getStringExtra("type"));

//                creates person
                p.RequestWithParameters(select_problem_p.this, "register.php", values);

//                creates patient
                values.clear();
                values.put("username", getIntent().getStringExtra("username"));
                values.put("problem", chosenProblem);
                p.RequestWithParameters(select_problem_p.this, "registerPatient.php", values);

                sessionManager.createSession(getIntent().getStringExtra("username"));

                setlayout();





            }
        });



    }

    public void checkButton(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();
        rbtn = findViewById(radioID);
    }

    public void setlayout(){
        Intent intent = new Intent(this , homeActivity.class);
        startActivity(intent);
    }
}
