package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    DatabaseReference DatabaseFlights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);

        title = (TextView) findViewById(R.id.textViewflightsDeatils);
        from = (TextView) findViewById(R.id.textViewSource);
        to = (TextView) findViewById(R.id.editTextDest);
        date = (TextView) findViewById(R.id.textViewDate);
        time = (TextView) findViewById(R.id.textViewTime);
        price = (TextView) findViewById(R.id.textViewPrice);

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");

        Intent i = getIntent();
        String source = i.getExtras().getString("flightId");

        protected void onStart() {
            super.onStart();

            DatabaseFlights.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot flightSnapshot : dataSnapshot.getChildren()) {
                        Flight flight = flightSnapshot.getValue(Flight.class);
                        Intent i = getIntent();
                        String source = i.getExtras().getString("filghtId");
                        if ()
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            }
        };


    }
}
