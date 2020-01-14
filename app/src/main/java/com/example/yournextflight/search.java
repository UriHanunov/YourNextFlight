package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class search extends AppCompatActivity {

    ListView listViewFlight;

    List<Flight> flightList;

    String date1;
    String date2;

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
                    String dest = i.getExtras().getString("dest");
                    date1 = i.getExtras().getString("date 1");
                    date2 = i.getExtras().getString("date 2");
                    Log.e("log", dest+" "+flight.getDestination());
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
                    if(flight.getDestination().equals(dest) && (fightDate.after(date_1) && fightDate.before(date_2) || fightDate.equals(date_1) || fightDate.equals(date_2))) {
                        flightList.add(flight);
                    }

                }

                FlightList adapter = new FlightList(search.this, flightList);
                listViewFlight.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDialog( final String FlightId,  final String source, final String destination,  final String Time,  final String date, final int price, final int places) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);

        final AlertDialog alertDialog= dialogBuilder.create();
        alertDialog.show();

        final EditText editTextPrice = (EditText) dialogView.findViewById(R.id.editTextNewPrice);
        final Button buttonUpdate= (Button) dialogView.findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int NewPrice = Integer.parseInt(editTextPrice.getText().toString().trim());

                updatePrice(FlightId, source,destination, Time,date,NewPrice, places);

                alertDialog.dismiss();
            }
        });


    }

    private boolean updatePrice (String FlightId, String source, String destination, String Time, String date, int price, int places){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("flights").child(FlightId);

        Flight flight = new Flight (FlightId, source,  destination, Time, date, price,places);

        databaseReference.setValue(flight);

        Toast.makeText(this, "price update seccessfully", Toast.LENGTH_LONG).show();

        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_flights);

        listViewFlight= (ListView) findViewById(R.id.listViewAllFlights);

        flightList= new ArrayList<>();

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");

        listViewFlight.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {

                Flight flight = flightList.get(i);

                showUpdateDialog(flight.getFlightId(), flight.getSource(), flight.getDestination(), flight.getTime(), flight.getDate(), flight.getprice(), flight.getplaces());
                return false;
            }
        });

    }

}

