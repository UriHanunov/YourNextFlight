package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerLogin extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private Button signUp;
    private int count = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlogin);


        name = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        info = (TextView)findViewById(R.id.tvInfo);
        login = (Button)findViewById(R.id.btLogin);
        signUp = (Button)findViewById(R.id.btSignUp);

        info.setText("number of attempts remaining: 5");

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                validate(name.getText().toString(),password.getText().toString());
            }
        });

        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CustomerLogin.this, SignUp.class);
                startActivity(intent);
            }
        });

    }

    private void validate(String userName , String userPassword)
    {
        if(userName.equals("admin") && userPassword.equals("1234"))
        {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        }
        else
        {
            count--;
            info.setText("number of attempts remaining: " + String.valueOf(count));
            if(count == 0)
            {
                login.setEnabled(false);
            }
        }
    }
}
