package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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

public class CustomerResultSearching extends AppCompatActivity {

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
                    Intent  i = getIntent();
                    String source = i.getExtras().getString("source");
                    String dest = i.getExtras().getString("dest");
                    String date1 = i.getExtras().getString("date 1");
                    String date2 = i.getExtras().getString("date 2");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String dateInString = flight.getDate();

                    Date fightDate = null;
                    try {
                        fightDate = formatter.parse(dateInString);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Date date_1 = null;
                    try {
                        date_1 = formatter.parse(date1);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Date date_2 = null;
                    try {
                        date_2 = formatter.parse(date2);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

//                    Log.e("log", dest+" "+flight.getDestination());
                    if(flight.getDestination().equals(dest) && flight.getSource().equals(source) && (fightDate.after(date_1) && fightDate.before(date_2) || fightDate.equals(date_1) || fightDate.equals(date_2))) {
                        flightList.add(flight);
                    }
                }

                FlightList adapter = new FlightList(CustomerResultSearching.this, flightList);
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
        setContentView(R.layout.activity_customer_result_searching);

        listViewFlight= (ListView) findViewById(R.id.listViewCustomerResult);

        flightList= new ArrayList<>();

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");

    }

}
