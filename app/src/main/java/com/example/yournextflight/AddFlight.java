package com.example.yournextflight;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddFlight extends AppCompatActivity {

    private Button Add;
    private EditText source;
    private EditText dest;
    private EditText time;
//    private EditText date;
    private EditText price;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "AddFlight";
    String  date;

    DatabaseReference DatabaseFlights;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);
        mDisplayDate = (TextView) findViewById(R.id.textViewSelectDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddFlight.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);

                date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");

        source = (EditText)findViewById(R.id.editTextSource);
        dest = (EditText)findViewById(R.id.editTextDest);
        time = (EditText)findViewById(R.id.editTextTime);
        price = (EditText)findViewById(R.id.editTextPrice);
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
        String da= date;
        int pr= Integer.parseInt(price.getText().toString());

        if(!TextUtils.isEmpty(so)||!TextUtils.isEmpty(des)||TextUtils.isEmpty(ti)||!TextUtils.isEmpty(da)){

            String id = DatabaseFlights.push().getKey();

            Flight flight= new Flight(id, so, des, ti, da, pr);

            DatabaseFlights.child(id).setValue(flight);

            Toast.makeText(this, "flight added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "you didnt write all of the options", Toast.LENGTH_LONG).show();
        }
    }
}
