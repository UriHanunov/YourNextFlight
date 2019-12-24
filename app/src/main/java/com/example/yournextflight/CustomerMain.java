package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

    private TextView info;
    private TextView name;
    private Button myFlights;
    private Button lastChance;
    private TextView search;

    DatabaseReference DatabaseUsers;
    FirebaseAuth mAuth;


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

        DatabaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        final String q = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        DatabaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot flightSnapshot : dataSnapshot.getChildren()){
                    User temp2 = dataSnapshot.getValue(User.class);
                    String a = temp2.userId;
                    if(a.equals(q))
                    {
                        name.setText(temp2.firstName);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
