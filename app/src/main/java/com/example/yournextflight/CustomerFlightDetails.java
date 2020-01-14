package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerFlightDetails extends AppCompatActivity {

    private TextView title;
    private TextView from;
    private TextView to;
    private TextView date;
    private TextView time;
    private TextView price;
    private Button Cancel;
    String ID;
    String orderID;
    Flight flight;

    DatabaseReference DatabaseFlights;
    DatabaseReference DatabaseOrders;

    @Override
    protected void onStart() {
        super.onStart();

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
                    Intent i = getIntent();
                    ID = i.getExtras().getString("flightId");
//                    Log.e("log", ID);
                    if (flightTemp.getFlightId().equals(ID)) {
                        flight = flightSnapshot.getValue(Flight.class);
//                        Log.e("log", flight.getDestination());
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

        DatabaseOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot flightSnapshot : dataSnapshot.getChildren()) {
                    orderFlight order = flightSnapshot.getValue(orderFlight.class);
                    Intent i = getIntent();
                    ID = i.getExtras().getString("flightId");
                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
//                    Log.e("log", ID);
//                    Log.e("log", userId);
                    if (order.getFlightId().equals(ID) && order.getUserId().equals(userId)) {
                         orderID= order.getOrderId();
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
        setContentView(R.layout.activity_customer_flight_details);

        Cancel= (Button) findViewById(R.id.buttonCancel);

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");
        DatabaseOrders = FirebaseDatabase.getInstance().getReference("Orders");

        Cancel.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v) {
                cancelFlight();
            }
        });

    }

    private void cancelFlight() {

//        Log.e("log", "fsklhcd"+ ID);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("flights").child(ID);

        Log.e("log",Integer.toString(flight.getplaces()));

        flight.setplacesPlus();

        Log.e("log",Integer.toString(flight.getplaces()));
//        Log.e("log", ID);
//        Log.e("log", ID);
//        Log.e("log", ID);
//        Log.e("log", ID);

        Flight newFlight = new Flight(ID, flight.getSource(), flight.getDestination(), flight.getTime(), flight.getDate(), flight.getprice(), flight.getplaces());

        databaseReference.setValue(newFlight);

        DatabaseOrders.child(orderID).orderByKey().equalTo(orderID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        Intent intent = new Intent(CustomerFlightDetails.this, CustomerMain.class);
        startActivity(intent);

        Toast.makeText(this, "flight canceled, check my flights for more details", Toast.LENGTH_LONG).show();
    }
}
