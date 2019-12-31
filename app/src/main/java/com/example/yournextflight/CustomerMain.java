package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerMain extends AppCompatActivity {
    private static final String TAG = "CustomerMain";

    private TextView info;
    private TextView name;
    private Button myFlights;
    private Button lastChance;
    private TextView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        info = (TextView)findViewById(R.id.tv2); //hello
        name = (TextView)findViewById(R.id.textViewName); //save the name
        myFlights = (Button) findViewById(R.id.buttonMyFlights);
        lastChance = (Button)findViewById(R.id.buttonLastChance);
        search = (TextView)findViewById(R.id.textViewSerach);


        //when we click on myFlights button - go to myFlights activity
        myFlights.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CustomerMain.this, CustomerMyFlights.class);
                startActivity(intent);
            }
        });

        //when we click on myFlights button - go to myFlights activity
        lastChance.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CustomerMain.this, CustomerLastChance.class);
                startActivity(intent);
            }
        });
    }


}


