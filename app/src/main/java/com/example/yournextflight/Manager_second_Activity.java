package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manager_second_Activity extends AppCompatActivity {

    private Button addFlight;
    private Button allFlights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_second_activity);

        addFlight = (Button)findViewById(R.id.buttonAddFlight);
        allFlights = (Button)findViewById(R.id.buttonAllFlights);

        addFlight.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Manager_second_Activity.this, AddFlight.class);
                startActivity(intent);
            }
        });

        allFlights.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Manager_second_Activity.this, AllFlights.class);
                startActivity(intent);
            }
        });
    }




}
