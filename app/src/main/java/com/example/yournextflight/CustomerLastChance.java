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

public class CustomerLastChance extends AppCompatActivity {

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

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String dateInString = flight.getDate();

                    Date fightDate = null;
                    try {
                        fightDate = formatter.parse(dateInString);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Date today = new Date(System.currentTimeMillis());
                    Date newdate = new Date(System.currentTimeMillis());
                    newdate.setMonth(newdate.getMonth()+1);

                    if(fightDate.after(today) && fightDate.before(newdate)){
                    flightList.add(flight);
                    }
                }

                FlightList adapter = new FlightList(CustomerLastChance.this, flightList);
                listViewFlight.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDialog( final String FlightId,  final String source, final String destination,  final String Time,  final String date, final int price) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.order_dialog, null);

        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Updating Price" + destination);
        final AlertDialog alertDialog= dialogBuilder.create();
        alertDialog.show();

        final EditText editTextPrice = (EditText) dialogView.findViewById(R.id.editTextNewPrice);
        final Button buttonUpdate= (Button) dialogView.findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int NewPrice = Integer.parseInt(editTextPrice.getText().toString().trim());

//                updatePrice(FlightId, source,destination, Time,date,NewPrice);

                alertDialog.dismiss();
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_last_chance);

        listViewFlight= (ListView) findViewById(R.id.ListViewLastMinute);

        flightList= new ArrayList<>();

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");

        listViewFlight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Flight flight = flightList.get(position);

                Intent intent = new Intent(CustomerLastChance.this, CustomerOrders.class);
                intent.putExtra("flightId",flight.getFlightId());

                startActivity(intent);

            }
        });

    }



}
