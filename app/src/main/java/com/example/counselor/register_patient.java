package com.example.counselor;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class register_patient extends AppCompatActivity {
    private Button rP;
    private EditText usernameRP, passwordRP, conpasswordRP;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_patient);
        final MainActivity a = new MainActivity();
        usernameRP = findViewById(R.id.username_registerT);
        passwordRP = findViewById(R.id.password_registerP);
        conpasswordRP = findViewById(R.id.confirm_pass_regP);

        rP = findViewById(R.id.register_btnP);
        rP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernamep = usernameRP.getText().toString();
                String passwordp = conpasswordRP.getText().toString();
                if(passwordp.equals(passwordRP.getText().toString())){

                    setlayout();
                    PHPRequest p = new PHPRequest();
                    ContentValues values = new ContentValues();
                    values.put("username" , usernamep);
                    values.put("password", passwordp);
                    values.put("type", "Patient");
                    p.RequestWithParameters(register_patient.this, "register.php", values);
                }
                else{
                    // display password dont match

                }
            }
        });
        type_of_regi t = new type_of_regi();



    }
    public void setlayout(){
        Intent intent = new Intent(this , select_problem_p.class);
        startActivity(intent);
    }

}