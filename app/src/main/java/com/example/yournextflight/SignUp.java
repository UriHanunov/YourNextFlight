package com.example.yournextflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    //all the variables:
    private EditText name;
    private EditText password;
    private Button signUp;
    private FirebaseAuth mAuth; //for connect the firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //connect variables to their id in xml
        name = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        signUp = (Button)findViewById(R.id.btSignUp);
        mAuth = FirebaseAuth.getInstance();

        //when we click on signUp button - go to registerUser function
        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                registerUser();
            }
        });

    }

    /*
    This function - check if the email and the password that the user insert is a valid one
    if yes - add this user to the firebase
    if not - ask the user to insert it again
     */
    public void registerUser()
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

        Log.d("SignUp", "before firebase: ");

        //add the details to the firebase
        mAuth.createUserWithEmailAndPassword(email, userPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(SignUp.this, CustomerMain.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"User registered successfull", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(getApplicationContext(),"You are already register", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
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