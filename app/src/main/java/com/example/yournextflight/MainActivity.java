package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView yourNextFlight;
    private Button manager;
    private Button customer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yourNextFlight = (TextView)findViewById(R.id.yourNextFlight);
        manager = (Button)findViewById(R.id.buManger);
        customer = (Button)findViewById(R.id.buCustomer);

        manager.setOnClickListener(new View.OnClickListener() //go to manager activity
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, ManagerLogin.class);
                startActivity(intent);
            }
        });

        customer.setOnClickListener(new View.OnClickListener() //go to customer activity
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CustomerLogin.class);
                startActivity(intent);
            }
        });
    }

}
