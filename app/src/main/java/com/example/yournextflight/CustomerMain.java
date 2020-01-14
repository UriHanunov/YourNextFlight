package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CustomerMain extends AppCompatActivity {
    private static final String TAG = "CustomerMain";
    private static final int REQUEST_CALL = 1;
    private static final String NUMBER_CALL = "0526839535";

    private TextView hello;
    private TextView name;
    private TextView search;
    private TextView source;
    String userName;
    private EditText dest;
    private Button myFlights;
    private Button lastChance;
    private Button searchAll;
    private ImageButton imageCall;
    private TextView mDisplayDate1;
    private TextView mDisplayDate2;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    String date;
    String date2;

    DatabaseReference DatabaseFlights;
    DatabaseReference DatabaseUsers;

    protected void onStart() {
        super.onStart();

        DatabaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot flightSnapshot : dataSnapshot.getChildren()){
                    User user= flightSnapshot.getValue(User.class);
                    if(user.getUserId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()))
                        name.setText("Hello  "+ user.getFirstName());
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
                        CustomerMain.this,
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


        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");
        DatabaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        hello = (TextView) findViewById(R.id.textViewHello); //hello
        name = (TextView) findViewById(R.id.textViewName); //save the name
        myFlights = (Button) findViewById(R.id.buttonMyFlights);
        lastChance = (Button) findViewById(R.id.buttonLastChance);
        search = (TextView) findViewById(R.id.textViewSerach);
        source = (EditText) findViewById(R.id.editTextSorce2);
        dest = (EditText) findViewById(R.id.editTextDest2);
        name = (TextView) findViewById(R.id.textViewName);
        searchAll = (Button) findViewById(R.id.buttonSearchAll);
        imageCall = (ImageButton) findViewById(R.id.imageButtonIc_Phone);

        myFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerMain.this, CustomerMyFlights.class);
                startActivity(intent);
            }
        });


        lastChance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerMain.this, CustomerLastChance.class);
                startActivity(intent);
            }
        });

        myFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerMain.this, MyFlights.class);
                startActivity(intent);
            }
        });


        searchAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFlights();
            }
        });

        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }

    private void makePhoneCall()
    {

        if(ContextCompat.checkSelfPermission(CustomerMain.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(CustomerMain.this,
                    new String[] {Manifest.permission.CALL_PHONE} , REQUEST_CALL );
        }
        else
        {
            String dial = "tel:" + NUMBER_CALL;
            startActivity(new Intent(Intent.ACTION_CALL , Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                makePhoneCall();
            }
        }
        else
        {
            Toast.makeText(this,"Permission DENIED", Toast.LENGTH_SHORT).show();
        }
    }


    private void searchFlights() {
        String so = source.getText().toString().trim();
        String des = dest.getText().toString().trim();
        String da = date;
        String da2 = date2;

        if (!TextUtils.isEmpty(so) || !TextUtils.isEmpty(des) || TextUtils.isEmpty(da) || !TextUtils.isEmpty(da2)) {
            Intent intent = new Intent(CustomerMain.this, CustomerResultSearching.class);
            intent.putExtra("source",so);
            intent.putExtra("dest",des);
            intent.putExtra("date 1",da);
            intent.putExtra("date 2",da2);
            startActivity(intent);
        }

    }
}


