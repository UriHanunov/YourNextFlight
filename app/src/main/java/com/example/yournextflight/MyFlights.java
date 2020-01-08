package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyFlights extends AppCompatActivity {

    DatabaseReference DatabaseOrders;
    DatabaseReference DatabaseFlights;
    ArrayList<String> MyFlights;

    ListView listViewFlight;

    List<Flight> flightList;


    @Override
    protected void onStart() {
        super.onStart();

        DatabaseOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot flightSnapshot : dataSnapshot.getChildren()){
                    orderFlight order= flightSnapshot.getValue(orderFlight.class);
                    if (order.getUserId().equals( FirebaseAuth.getInstance().getCurrentUser().getUid().toString())){
                        Log.e("log", order.getFlightId());
                        MyFlights.add(order.getFlightId().toString());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

            DatabaseFlights.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for( int i=0; i< MyFlights.size(); i++) {
                        Log.e("log", MyFlights.get(i));
                    for (DataSnapshot flightSnapshot : dataSnapshot.getChildren()) {
                        Flight flight= flightSnapshot.getValue(Flight.class);

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String dateInString = flight.getDate();

                        Date fightDate = null;
                        try {
                            fightDate = formatter.parse(dateInString);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Date today = new Date(System.currentTimeMillis());

                        if(flight.getFlightId().equals(MyFlights.get(i)) && fightDate.after(today) || fightDate.equals(today))

                            flightList.add(flight);
                    }
                }
                    FlightList adapter = new FlightList(MyFlights.this, flightList);
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
        setContentView(R.layout.activity_my_flights);

        DatabaseOrders = FirebaseDatabase.getInstance().getReference("Orders");
        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");

        listViewFlight= (ListView) findViewById(R.id.MyFlightListView);

        flightList= new ArrayList<>();
        MyFlights= new ArrayList<>();

        listViewFlight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Flight flight = flightList.get(position);

                Intent intent = new Intent(MyFlights.this, CustomerFlightDetails.class);
                intent.putExtra("flightId",flight.getFlightId());

                startActivity(intent);

            }
        });

    }
}
