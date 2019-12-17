package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLogin extends AppCompatActivity {

    //all the variables:
    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private Button signUp;
    private int count = 5; //number of tries to login
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlogin);

        //connect variables to their id in xml
        name = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        info = (TextView)findViewById(R.id.tvInfo);
        login = (Button)findViewById(R.id.btLogin);
        signUp = (Button)findViewById(R.id.btSignUp);
        mAuth = FirebaseAuth.getInstance();

        info.setText("number of attempts remaining: 5");

        //when we click on login button - go to userLogin function
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                userLogin();
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


    private void userLogin()
    {
        String email = name.getText().toString().trim();
        String userPass = password.getText().toString().trim();

        if(email.isEmpty()) //if email is empty
        {
            name.setError("email is required");
            name.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) //if the email is not valid
        {
            name.setError("please enter a valid email");
            name.requestFocus();
            return;
        }

        if(userPass.isEmpty()) //if password is empty
        {
            password.setError("password is required");
            password.requestFocus();
            return;
        }

        if(userPass.length() < 6) //the password need to be over than 5
        {
            password.setError("minimum length of password sould be 6");
            password.requestFocus();
            return;
        }

        //check if the email and the password is correct by firebase
        mAuth.signInWithEmailAndPassword(email,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful()) //if we found this user with the correct password
                {
                    Intent intent = new Intent(CustomerLogin.this, CustomerMain.class);
                    startActivity(intent);
                }
                else //the password or email is not correct
                {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage() , Toast.LENGTH_LONG).show();
                    count--; //minus 1 to tries
                    info.setText("number of attempts remaining: " + String.valueOf(count));
                    if(count == 0)
                    {
                        login.setEnabled(false);
                    }
                }
            }
        });
    }
}
