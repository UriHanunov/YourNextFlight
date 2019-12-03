package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CustomerMain extends AppCompatActivity {

    private TextView info;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        info = (TextView)findViewById(R.id.tvInfo);
        b = (Button)findViewById(R.id.button);
    }
}
