package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Manager_second_Activity extends AppCompatActivity {

    private Button addFlight;
    private Button allFlights;
    private EditText Destination;
    private Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_second_activity);

        addFlight = (Button)findViewById(R.id.buttonAddFlight);
        allFlights = (Button)findViewById(R.id.buttonAllFlights);
        search = (Button)findViewById(R.id.buttonSearch);
        Destination = (EditText) findViewById(R.id.editTextDestination);

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

        search.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v)
            {
                String Dest= Destination.getText().toString().trim();

                Intent intent = new Intent(Manager_second_Activity.this, search.class);
                intent.putExtra("dest",Dest);
                startActivity(intent);
            }
        });
    }




}
