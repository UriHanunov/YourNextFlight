package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerLogin extends AppCompatActivity {

    //all the variables:
    private EditText email;
    private EditText password;
    private TextView info;
    private Button login;
    private Button signUp;
    private int count = 5; //number of tries to login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlogin);

        //connect variables to their id in xml
        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        info = (TextView)findViewById(R.id.tvInfo);
        login = (Button)findViewById(R.id.btLogin);
        signUp = (Button)findViewById(R.id.btSignUp);

        info.setText("number of attempts remaining: 5");

        //when we click on login button - go to validate function
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                validate(email.getText().toString(),password.getText().toString());
            }
        });

        //when we click on signUp button - go to SignUp activity
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

    ///need to change this !!! ////
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
