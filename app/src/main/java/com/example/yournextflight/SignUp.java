package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button signUp;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        signUp = (Button)findViewById(R.id.btSignUp);
        mAuth = FirebaseAuth.getInstance();



        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                registerUser();
            }
        });

    }

    public void registerUser()
    {
        String email = name.getText().toString().trim();
        String userPass = password.getText().toString().trim();
        if(email.isEmpty())
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

        if(userPass.isEmpty())
        {
            password.setError("password is required");
            password.requestFocus();
            return;
        }

        if(userPass.length() < 6)
        {
            password.setError("minimum length of password sould be 6");
            password.requestFocus();
            return;
        }
        Log.d("SignUp", "before firebase: ");

        mAuth.createUserWithEmailAndPassword(email, userPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"User registered successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("SignUp", "onFailure: " + e.toString());
                Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }


}

