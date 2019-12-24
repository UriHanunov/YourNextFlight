package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerDetails extends AppCompatActivity {

    private TextView info;
    private EditText firstName;
    private EditText lastName;
    private EditText city;
    private EditText streetAddress;
    private EditText phoneNumber;
    private Button nextPage;

    DatabaseReference DatabaseUsers;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        //connect variables to their id in xml
        info = (TextView)findViewById(R.id.tv2);
        firstName = (EditText)findViewById(R.id.etFName);
        lastName = (EditText)findViewById(R.id.etlName);
        city = (EditText)findViewById(R.id.etCity);
        streetAddress = (EditText)findViewById(R.id.etAddress);
        phoneNumber = (EditText)findViewById(R.id.etPhone);
        nextPage = (Button)findViewById(R.id.btNext);

        DatabaseUsers = FirebaseDatabase.getInstance().getReference("Users");

        //when we click on nextPage button - go to updateUserData function
        nextPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateUserData();

            }
        });
    }

    private void updateUserData()
    {
        String first= firstName.getText().toString().trim();
        String last= lastName.getText().toString().trim();
        String city2= city.getText().toString().trim();
        String address2= streetAddress.getText().toString().trim();
        String phone= phoneNumber.getText().toString().trim();

        if(!TextUtils.isEmpty(first)&&!TextUtils.isEmpty(last)&&!TextUtils.isEmpty(city2)&&!TextUtils.isEmpty(address2)&&!TextUtils.isEmpty(phone))
        {
            String id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
            String temp = DatabaseUsers.push().getKey();
            User user= new User(id, first, last, city2, address2, phone);
            DatabaseUsers.child(temp).setValue(user);
            Toast.makeText(this, "personal details added", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CustomerDetails.this, CustomerMain.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "you didnt write all of the options", Toast.LENGTH_LONG).show();
        }
    }
}
