package com.example.ariad.rainesair;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button logon, forgot, enroll, contact, location;
    Intent intent;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {

                    intent = new Intent(getApplicationContext(), LoggedInActivity.class);
                    startActivity(intent);

                }
            }

        };

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);

        logon = (Button) findViewById(R.id.login_btn_signIn);
        forgot = (Button) findViewById(R.id.login_btn_forgotPass);
        enroll = (Button) findViewById(R.id.login_btn_enroll);
        contact = (Button) findViewById(R.id.login_btn_contact);
        location = (Button) findViewById(R.id.login_btn_Location);


        email.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                email.setFocusable(true);
                email.setFocusableInTouchMode(true);

                return false;
            }
        });

        password.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                password.setFocusable(true);
                password.setFocusableInTouchMode(true);

                return false;
            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                intent = new Intent(getApplicationContext(), ForgotInfoActivity.class);
                startActivity(intent);

            }
        });

        //SIGN IN

        logon.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                startSignIn();

            }
        });


        enroll.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

            }
        });


        contact.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                intent = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intent);

            }
        });


        location.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);


    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }


    public void startSignIn() {

        String getEmail = email.getText().toString();
        String getPassword = password.getText().toString();


        if (TextUtils.isEmpty(getEmail) || TextUtils.isEmpty(getPassword)) {

            Toast.makeText(getApplicationContext(), "Please Fill All Info", Toast.LENGTH_LONG).show();


        } else {

            mAuth.signInWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toast.makeText(getApplicationContext(), "Username or Password do not match. Please Try Again.", Toast.LENGTH_LONG).show();


                    }

                }
            });

        }

    }


}
