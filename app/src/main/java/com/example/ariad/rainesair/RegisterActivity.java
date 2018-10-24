package com.example.ariad.rainesair;

import android.content.Intent;
import android.graphics.Color;
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


public class RegisterActivity extends AppCompatActivity {

    EditText firstName, lastName, address, city, state, zip, cell, workNum, ext, email, passw;
    Spinner country;
    Button save, cancel;
    Intent intent;
    Users users = new Users();
    String firstName1, lastName1, address1, city1, state1, zip1, cell1, workNum1,
            ext1, email1, passw1, country1;
    private String userID;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        firstName = (EditText) findViewById(R.id.register_first_name);
        lastName = (EditText) findViewById(R.id.register_last_name);
        address = (EditText) findViewById(R.id.register_address);
        city = (EditText) findViewById(R.id.register_city);
        state = (EditText) findViewById(R.id.register_state);
        zip = (EditText) findViewById(R.id.register_zip);
        cell = (EditText) findViewById(R.id.register_cell);
        workNum = (EditText) findViewById(R.id.register_work_number);
        ext = (EditText) findViewById(R.id.register_ext);
        email = (EditText) findViewById(R.id.register_email);
        passw = (EditText) findViewById(R.id.register_password);
        country = (Spinner) findViewById(R.id.register_country);


        save = (Button) findViewById(R.id.register_save);
        cancel = (Button) findViewById(R.id.register_cancel);

        ImageView back = (ImageView) findViewById(R.id.register_back);


        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


            }
        });

        country.getBackground().clearColorFilter();

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


        address.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                address.setFocusable(true);
                address.setFocusableInTouchMode(true);

                return false;
            }
        });


        city.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                city.setFocusable(true);
                city.setFocusableInTouchMode(true);

                return false;
            }
        });


        state.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                state.setFocusable(true);
                state.setFocusableInTouchMode(true);

                return false;
            }
        });


        zip.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                zip.setFocusable(true);
                zip.setFocusableInTouchMode(true);

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


        workNum.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                workNum.setFocusable(true);
                workNum.setFocusableInTouchMode(true);

                return false;
            }
        });

        ext.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ext.setFocusable(true);
                ext.setFocusableInTouchMode(true);

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


        passw.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                passw.setFocusable(true);
                passw.setFocusableInTouchMode(true);

                return false;
            }
        });


        save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                register();


            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }


    public void register() {

        firstName1 = firstName.getText().toString();
        lastName1 = lastName.getText().toString();
        address1 = address.getText().toString();
        city1 = city.getText().toString();
        state1 = state.getText().toString();
        zip1 = zip.getText().toString();
        cell1 = cell.getText().toString();
        workNum1 = workNum.getText().toString();
        ext1 = ext.getText().toString();
        email1 = email.getText().toString();
        passw1 = passw.getText().toString();
        country1 = country.getSelectedItem().toString();

        boolean isEmailValid = isValidEmailAddress(email1);
        boolean isPasswordValid = isValidPassword(passw1);


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


        } else if (TextUtils.isEmpty(passw1)) {

            Toast.makeText(getApplicationContext(), "Password Required", Toast.LENGTH_SHORT).show();
            passw.setHintTextColor(Color.parseColor("#FF0000"));


        } else if (isEmailValid == false) {
            Toast.makeText(getApplicationContext(),
                    "Wrong email format.", Toast.LENGTH_LONG)
                    .show();

        } else if (isPasswordValid == false) {
            Toast.makeText(getApplicationContext(),
                    "Password must be greater than 6 characters.", Toast.LENGTH_LONG)
                    .show();
        } else {


            mAuth.createUserWithEmailAndPassword(email1, passw1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration Problem", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "User Registered Successfully", Toast.LENGTH_LONG).show();


                                userID = mAuth.getCurrentUser().getUid();
                                DatabaseReference currentUser1 = mDatabase.child(userID);

                                if (TextUtils.isEmpty(address1)) {

                                    address1 = "not filled";

                                }

                                if (TextUtils.isEmpty(city1)) {

                                    city1 = "not filled";

                                }

                                if (TextUtils.isEmpty(state1)) {

                                    state1 = "not filled";

                                }

                                if (TextUtils.isEmpty(zip1)) {

                                    zip1 = " ";

                                }

                                if (TextUtils.isEmpty(workNum1)) {

                                    workNum1 = "not filled";

                                }


                                if (TextUtils.isEmpty(ext1)) {

                                    ext1 = "not filled";

                                }

                                users.setFirstName(firstName1);
                                users.setLastName(lastName1);
                                users.setCountry(country1);
                                users.setAddress(address1);
                                users.setCity(city1);
                                users.setState(state1);
                                users.setZipCode(zip1);
                                users.setCell(cell1);
                                users.setWorkNumber(workNum1);
                                users.setExtension(ext1);
                                users.setEmail(email1);
                                users.setProfilePic("defaultprofile");

                                DatabaseReference currentUser = currentUser1.child("PersonalInfo");
                                currentUser.setValue(users);


                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                user.sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {

                                                    Toast.makeText(RegisterActivity.this,
                                                            "An email with instructions to reset your password has been sent",
                                                            Toast.LENGTH_LONG).show();

                                                }
                                            }
                                        });

                                firstName.setText("");
                                lastName.setText("");
                                address.setText("");
                                city.setText("");
                                state.setText("");
                                zip.setText("");
                                cell.setText("");
                                workNum.setText("");
                                ext.setText("");
                                email.setText("");
                                passw.setText("");
                                country.setSelection(0);


//                                firstName.setHint("First Name:");
//                                firstName.setHintTextColor(Color.parseColor("#000"));
//
//                                lastName.setHint("Last Name:");
//                                lastName.setHintTextColor(Color.parseColor("#000"));
//
//                                cell.setHint("Cell:");
//                                cell.setHintTextColor(Color.parseColor("#000"));
//
//
//                                email.setHint("Email:");
//                                email.setHintTextColor(Color.parseColor("#000"));
//
//
//                                passw.setHintTextColor(Color.parseColor("#000"));


                                FirebaseAuth.getInstance().signOut();

                            }


                        }
                    });
        }
    }


    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


    private boolean isValidPassword(String password) {

        if (password.length() > 6) {
            return true;
        }

        return false;
    }


}