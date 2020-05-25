package com.example.counselor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView regy;
  private Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

       regy = this.findViewById(R.id.register_txt);
       bt = findViewById(R.id.login_btn);



      regy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               setlayout();


           }
       });





    }
    public void setlayout(){
        Intent intent = new Intent(this , type_of_regi.class);
        startActivity(intent);
    }


}
