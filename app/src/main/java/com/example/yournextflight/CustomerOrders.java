package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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

                for(DataSnapshot flightSnapshot : dataSnapshot.getChildren()){
                    Flight flight= flightSnapshot.getValue(Flight.class);
                    Intent  i = getIntent();
                    ID = i.getExtras().getString("flightId");
                    Log.e("log", ID);
                    if(flight.getFlightId().equals(ID)){
                        Log.e("log", flight.getDestination());
                        from.setText("From: "+flight.getSource());
                        to.setText("To: "+flight.getDestination());
                        date.setText("Date: "+flight.getDate());
                        time.setText("Time: "+flight.getTime());
                        price.setText("Price: "+Integer.toString(flight.getprice()));
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

        order = (Button)findViewById(R.id.buttonOrder);

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");
        DatabaseOrders = FirebaseDatabase.getInstance().getReference("Orders");

        order.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v){
                orderFlight();
            }
        });
    }

    private void orderFlight(){

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        String orderId = DatabaseOrders.push().getKey();

        orderFlight order = new orderFlight (ID, userId, orderId);

        DatabaseOrders.child(orderId).setValue(order);

    }

}



