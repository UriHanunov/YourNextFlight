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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CustomerMain extends AppCompatActivity {
    private static final String TAG = "CustomerMain";

    private TextView hello;
    private TextView name;
    private TextView search;
    private EditText source;
    private EditText dest;
    private Button myFlights;
    private Button lastChance;
    private Button searchAll;
    private TextView mDisplayDate1;
    private TextView mDisplayDate2;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    String date;
    String date2;

    DatabaseReference DatabaseFlights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

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
                        CustomerMain.this,
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
                        CustomerMain.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year,month,day);
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



        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");
        hello = (TextView)findViewById(R.id.textViewHello); //hello
        name = (TextView)findViewById(R.id.textViewName); //save the name
        myFlights = (Button) findViewById(R.id.buttonMyFlights);
        lastChance = (Button)findViewById(R.id.buttonLastChance);
        search = (TextView)findViewById(R.id.textViewSerach);
        source = (EditText)findViewById(R.id.editTextSorce2);
        dest = (EditText)findViewById(R.id.editTextDest2);
        searchAll = (Button)findViewById(R.id.buttonSearchAll);



        myFlights.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CustomerMain.this, CustomerMyFlights.class);
                startActivity(intent);
            }
        });


        lastChance.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CustomerMain.this, CustomerLastChance.class);
                startActivity(intent);
            }
        });


        searchAll.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(CustomerMain.this, CustomerLastChance.class);
            startActivity(intent);
        }
        });
}


}


