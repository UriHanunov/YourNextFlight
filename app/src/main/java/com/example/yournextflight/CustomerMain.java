package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class CustomerMain extends AppCompatActivity {

    private TextView info;

    DatabaseReference DatabaseUsers;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        info = (TextView)findViewById(R.id.tv2);
        String fName = "aaa";
    }
}
