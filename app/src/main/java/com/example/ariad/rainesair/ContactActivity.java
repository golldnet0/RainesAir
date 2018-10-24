package com.example.ariad.rainesair;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactActivity extends AppCompatActivity {

    EditText firstName, lastName, cell, email, subject, comments;
    Button save, cancel;
    Intent intent;


    String firstName1, lastName1, cell1, email1, subject1, comments1;
    ContactMe contact = new ContactMe();
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        firstName = (EditText) findViewById(R.id.contact_first_name);
        lastName = (EditText) findViewById(R.id.contact_last_name);
        cell = (EditText) findViewById(R.id.contact_cell);
        email = (EditText) findViewById(R.id.contact_email_form);
        subject = (EditText) findViewById(R.id.contact_subject);
        comments = (EditText) findViewById(R.id.contact_comments);

        save = (Button) findViewById(R.id.contact_save);
        cancel = (Button) findViewById(R.id.contact_cancel);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("ContactMe");

        //changes the color of the call icon

        ImageView lineColorCode = (ImageView) findViewById(R.id.contact_call);
        int color = Color.parseColor("#303F9F"); //The color u want
        lineColorCode.setColorFilter(color);

        //changes the color of the email icon

        ImageView lineColorCode1 = (ImageView) findViewById(R.id.contact_email_icon);
        int color1 = Color.parseColor("#303F9F"); //The color u want
        lineColorCode1.setColorFilter(color1);


        ImageView back = (ImageView) findViewById(R.id.contact_back);


        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


            }
        });


        lineColorCode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String phone = "3059261634";

                Uri call = Uri.parse("tel:" + phone);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(callIntent);


            }
        });


        lineColorCode1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String email = "ariadnamf91@aol.com";
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Choose email client..."));

            }
        });


        firstName.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                firstName.setFocusable(true);
                firstName.setFocusableInTouchMode(true);

                return false;
            }
        });


        lastName.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                lastName.setFocusable(true);
                lastName.setFocusableInTouchMode(true);

                return false;
            }
        });


        cell.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cell.setFocusable(true);
                cell.setFocusableInTouchMode(true);

                return false;
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


        subject.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                subject.setFocusable(true);
                subject.setFocusableInTouchMode(true);

                return false;
            }
        });

        comments.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                comments.setFocusable(true);
                comments.setFocusableInTouchMode(true);

                return false;
            }
        });


        save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                sendContactInfo();

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                firstName.setText("");
                lastName.setText("");
                cell.setText("");
                email.setText("");
                subject.setText("");
                comments.setText("");

            }
        });
    }

    public void sendContactInfo() {

        firstName1 = firstName.getText().toString();
        lastName1 = lastName.getText().toString();
        cell1 = cell.getText().toString();
        email1 = email.getText().toString();
        subject1 = subject.getText().toString();
        comments1 = comments.getText().toString();
        cell1 = cell.getText().toString();

        boolean isEmailValid = isValidEmailAddress(email1);


        if (TextUtils.isEmpty(firstName1)) {

            Toast.makeText(getApplicationContext(), "First Name Required", Toast.LENGTH_SHORT).show();
            firstName.setHint("* First Name Required");
            firstName.setHintTextColor(Color.parseColor("#FF0000"));

        } else if (TextUtils.isEmpty(lastName1)) {

            Toast.makeText(getApplicationContext(), "Last Name Required", Toast.LENGTH_SHORT).show();
            lastName.setHint("* Last Name Required");
            lastName.setHintTextColor(Color.parseColor("#FF0000"));


        } else if (TextUtils.isEmpty(cell1)) {

            Toast.makeText(getApplicationContext(), "Cell Number Required", Toast.LENGTH_SHORT).show();
            cell.setHint("* Cell Number Required");
            cell.setHintTextColor(Color.parseColor("#FF0000"));

        } else if (TextUtils.isEmpty(email1)) {

            Toast.makeText(getApplicationContext(), "Email Required", Toast.LENGTH_SHORT).show();
            email.setHint("* Email Required");
            email.setHintTextColor(Color.parseColor("#FF0000"));


        } else if (TextUtils.isEmpty(subject1)) {

            Toast.makeText(getApplicationContext(), "Subject Required", Toast.LENGTH_SHORT).show();
            subject.setHintTextColor(Color.parseColor("#FF0000"));


        } else if (TextUtils.isEmpty(comments1)) {

            Toast.makeText(getApplicationContext(), "Subject Required", Toast.LENGTH_SHORT).show();
            subject.setHintTextColor(Color.parseColor("#FF0000"));


        } else if (isEmailValid == false) {
            Toast.makeText(getApplicationContext(),
                    "Wrong email format.", Toast.LENGTH_LONG)
                    .show();


        } else {


            DatabaseReference currentValues = mDatabase.push();

            contact.setContactID(currentValues.getKey());

            contact.setFirstName(firstName1);
            contact.setLastName(lastName1);
            contact.setCell(cell1);
            contact.setEmail(email1);
            contact.setSubject(subject1);
            contact.setComments(comments1);


            currentValues.setValue(contact);

            firstName.setText("");
            lastName.setText("");
            cell.setText("");
            email.setText("");
            subject.setText("");
            comments.setText("");

            Toast.makeText(getApplicationContext(), "Information Sent Successfully", Toast.LENGTH_SHORT).show();

        }
    }


    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


}