package com.example.counselor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class homeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);







    }
    public Boolean getDetails(){
        Boolean res = false;
        PHPRequest p = new PHPRequest();
        p.Request(homeActivity.this, "logg.php" );



        return res;

    }
}
