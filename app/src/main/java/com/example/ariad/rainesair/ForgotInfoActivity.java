package com.example.ariad.rainesair;

import android.content.Intent;
import android.database.SQLException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotInfoActivity extends AppCompatActivity {

    EditText email;
    Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_info);

        try {

            email = (EditText) findViewById(R.id.forgot_email);
            send = (Button) findViewById(R.id.forgot_send);

            ImageView lineColorCode = (ImageView) findViewById(R.id.forgot_back);

            lineColorCode.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);


                }
            });


            email.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    email.setFocusable(true);
                    email.setFocusableInTouchMode(true);

                    return false;
                }
            });


            send.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String emailAddress = email.getText().toString();

                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(ForgotInfoActivity.this,
                                                "Email sent successfully",
                                                Toast.LENGTH_LONG).show();

                                        email.setText("");


                                    } else {
                                        Toast.makeText(ForgotInfoActivity.this,
                                                "Please enter an email associated with your account",
                                                Toast.LENGTH_LONG).show();
                                        email.setText("");
                                    }
                                }
                            });


                }
            });


        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }

    }

}