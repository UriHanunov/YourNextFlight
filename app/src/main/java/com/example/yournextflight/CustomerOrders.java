package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerOrders extends AppCompatActivity {

    private TextView title;
    private TextView from;
    private TextView to;
    private TextView date;
    private TextView time;
    private TextView price;
    private Button order;
    String ID;
    Flight flight;

    DatabaseReference DatabaseFlights;
    DatabaseReference DatabaseOrders;

    @Override
    protected void onStart() {
        super.onStart();

        Intent i = getIntent();
        ID = i.getExtras().getString("flightId");

        title = (TextView) findViewById(R.id.textViewflightsDeatils);
        from = (TextView) findViewById(R.id.textViewSource);
        to = (TextView) findViewById(R.id.textViewDest);
        date = (TextView) findViewById(R.id.textViewDate);
        time = (TextView) findViewById(R.id.textViewTime);
        price = (TextView) findViewById(R.id.textViewPrice);

        DatabaseFlights.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot flightSnapshot : dataSnapshot.getChildren()) {
                   Flight flightTemp = flightSnapshot.getValue(Flight.class);
                    if (flightTemp.getFlightId().equals(ID)) {
                        flight= flightSnapshot.getValue(Flight.class);
                        from.setText("From: " + flight.getSource());
                        to.setText("To: " + flight.getDestination());
                        date.setText("Date: " + flight.getDate());
                        time.setText("Time: " + flight.getTime());
                        price.setText("Price: " + Integer.toString(flight.getprice()));

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);

        order = (Button) findViewById(R.id.buttonOrder);

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");
        DatabaseOrders = FirebaseDatabase.getInstance().getReference("Orders");

        order.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v) {
                orderFlight();
            }
        });
    }

    private void orderFlight() {

        if (flight.getplaces() > 0) {

            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
            String orderId = DatabaseOrders.push().getKey();

            orderFlight order = new orderFlight(ID, userId, orderId);

            DatabaseOrders.child(orderId).setValue(order);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("flights").child(ID);

            flight.setplaces();

            Flight newFlight = new Flight(ID, flight.getSource(), flight.getDestination(), flight.getTime(), flight.getDate(), flight.getprice(), flight.getplaces());

            databaseReference.setValue(newFlight);

            Intent intent = new Intent(CustomerOrders.this, CustomerMain.class);
            startActivity(intent);

            Toast.makeText(this, "flight ordered, check my flights for more details", Toast.LENGTH_LONG).show();

        } else {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

            LayoutInflater inflater = getLayoutInflater();

            final View dialogView = inflater.inflate(R.layout.no_place_dialog, null);

            dialogBuilder.setView(dialogView);

            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();

            final Button buttonReturn = (Button) dialogView.findViewById(R.id.buttonReturn);

            buttonReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CustomerOrders.this, CustomerMain.class);
                    alertDialog.dismiss();
                    startActivity(intent);
                }
            });


        }

    }
}



