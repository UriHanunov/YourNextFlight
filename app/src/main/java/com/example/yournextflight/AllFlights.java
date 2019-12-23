package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllFlights extends AppCompatActivity {

    ListView listViewFlight;

    List<Flight> flightList;

    DatabaseReference DatabaseFlights;

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseFlights.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                flightList.clear();

                for(DataSnapshot flightSnapshot : dataSnapshot.getChildren()){
                    Flight flight= flightSnapshot.getValue(Flight.class);
                    flightList.add(flight);
                }

                FlightList adapter = new FlightList(AllFlights.this, flightList);
                listViewFlight.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_flights);

        listViewFlight= (ListView) findViewById(R.id.listViewAllFlights);

        flightList= new ArrayList<>();

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");

    }

}
