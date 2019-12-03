package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFlight extends AppCompatActivity {

    private Button Add;
    private EditText source;
    private EditText dest;
    private EditText time;
    private EditText date;

    DatabaseReference DatabaseFlights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");

        source = (EditText)findViewById(R.id.editTextSource);
        dest = (EditText)findViewById(R.id.editTextDest);
        time = (EditText)findViewById(R.id.editTextTime);
        date = (EditText)findViewById(R.id.editTextDate);
        Add = (Button)findViewById(R.id.buttonAdd);

        Add.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v){
                AddFlight();
            }
        });
    }

    private void AddFlight(){
        String so= source.getText().toString().trim();
        String des= dest.getText().toString().trim();
        String ti= time.getText().toString().trim();
        String da= date.getText().toString().trim();

        if(!TextUtils.isEmpty(so)||!TextUtils.isEmpty(des)||TextUtils.isEmpty(ti)||!TextUtils.isEmpty(da)){

            String id = DatabaseFlights.push().getKey();

            Flight flight= new Flight(id, so, des, ti, da);

            DatabaseFlights.child(id).setValue(flight);

            Toast.makeText(this, "flight added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "you didnt write all of the options", Toast.LENGTH_LONG).show();
        }
    }
}
