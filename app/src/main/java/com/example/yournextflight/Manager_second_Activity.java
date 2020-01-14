package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Manager_second_Activity extends AppCompatActivity {

    private static final String TAG = "Manager_second_Activity";

    private Button addFlight;
    private Button allFlights;
    private EditText Destination;
    private Button search;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    private TextView mDisplayDate1;
    private TextView mDisplayDate2;
    String date;
    String date2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_second_activity);

        addFlight = (Button)findViewById(R.id.buttonAddFlight);
        allFlights = (Button)findViewById(R.id.buttonAllFlights);
        search = (Button)findViewById(R.id.buttonSearch);
        Destination = (EditText) findViewById(R.id.editTextDestination);

        mDisplayDate1 = (TextView) findViewById(R.id.textViewDate1);
        mDisplayDate2 = (TextView) findViewById(R.id.textViewDate2);

        mDisplayDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Manager_second_Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
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
                mDisplayDate1.setText(date);
            }
        };

        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Manager_second_Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);

                date2 = day + "/" + month + "/" + year;
                mDisplayDate2.setText(date2);
            }
        };


        addFlight.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Manager_second_Activity.this, AddFlight.class);
                startActivity(intent);
            }
        });

        allFlights.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Manager_second_Activity.this, AllFlights.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v)
            {
                String Dest= Destination.getText().toString().trim();

                String da = date;
                String da2 = date2;

                Intent intent = new Intent(Manager_second_Activity.this, search.class);
                intent.putExtra("dest",Dest);
                intent.putExtra("date 1",da);
                intent.putExtra("date 2",da2);
                startActivity(intent);
            }
        });
    }




}
