package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerFirstActivity extends AppCompatActivity {

    private Button addFlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        addFlight = (Button)findViewById(R.id.buttonAddFlight);

        addFlight.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ManagerFirstActivity.this, AddFlight.class);
                startActivity(intent);
            }
        });
    }
}
