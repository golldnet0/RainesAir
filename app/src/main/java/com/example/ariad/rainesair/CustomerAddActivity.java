package com.example.ariad.rainesair;

import android.content.Intent;
import android.database.SQLException;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class CustomerAddActivity extends AppCompatActivity {

    public static final int RESULT_LOAD = 1;
    EditText companyName, address, city, state, zip, contactPerson, cell, workNum, ext, email, fax, comments;
    Spinner country;
    Button save, cancel;
    Intent intent;
    String uid;
    ImageView pic;
    Customers customer = new Customers();

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_add);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        companyName = (EditText) findViewById(R.id.addCustomer_company_name);
        address = (EditText) findViewById(R.id.addCustomer_address);
        city = (EditText) findViewById(R.id.addCustomer_city);
        state = (EditText) findViewById(R.id.addCustomer_state);
        zip = (EditText) findViewById(R.id.addCustomer_zip);
        contactPerson = (EditText) findViewById(R.id.addCustomer_contact_person);
        cell = (EditText) findViewById(R.id.addCustomer_cell);
        workNum = (EditText) findViewById(R.id.addCustomer_work_number);
        ext = (EditText) findViewById(R.id.addCustomer_ext);
        email = (EditText) findViewById(R.id.addCustomer_email);
        fax = (EditText) findViewById(R.id.addCustomer_fax);
        comments = (EditText) findViewById(R.id.addCustomer_comments);
        country = (Spinner) findViewById(R.id.addCustomer_country);


        save = (Button) findViewById(R.id.addCustomer_save);
        cancel = (Button) findViewById(R.id.addCustomer_cancel);

        ImageView back = (ImageView) findViewById(R.id.addCustomer_back);

        if (user != null) {
            uid = user.getUid();

            mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("Customers");

        }

        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CustomersActivity.class);
                startActivity(intent);


            }
        });

        country.getBackground().clearColorFilter();

        companyName.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                companyName.setFocusable(true);
                companyName.setFocusableInTouchMode(true);

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

        contactPerson.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                contactPerson.setFocusable(true);
                contactPerson.setFocusableInTouchMode(true);

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


        fax.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                fax.setFocusable(true);
                fax.setFocusableInTouchMode(true);

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

                addCustomer();


            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                intent = new Intent(getApplicationContext(), CustomersActivity.class);
                startActivity(intent);

            }
        });

    }


    public void addCustomer() {

        try {


            String companyName1 = companyName.getText().toString();
            String address1 = address.getText().toString();
            String city1 = city.getText().toString();
            String state1 = state.getText().toString();
            String zip1 = zip.getText().toString();
            String contactPerson1 = contactPerson.getText().toString();
            String cell1 = cell.getText().toString();
            String workNum1 = workNum.getText().toString();
            String ext1 = ext.getText().toString();
            String email1 = email.getText().toString();
            String fax1 = fax.getText().toString();
            String comments1 = comments.getText().toString();
            String country1 = country.getSelectedItem().toString();


            boolean isEmailValid = isValidEmailAddress(email1);


            if (TextUtils.isEmpty(companyName1)) {

                Toast.makeText(getApplicationContext(), "Company Name Required", Toast.LENGTH_SHORT).show();
                companyName.setHint("* Company Name Required");
                companyName.setHintTextColor(Color.parseColor("#FF0000"));

            } else if (TextUtils.isEmpty(contactPerson1)) {

                Toast.makeText(getApplicationContext(), "Contact Person Name Required", Toast.LENGTH_SHORT).show();
                contactPerson.setHint("* Contact Person Name Required");
                contactPerson.setHintTextColor(Color.parseColor("#FF0000"));


            } else {


                if (!TextUtils.isEmpty(email1)) {
                    if (isEmailValid == false) {
                        Toast.makeText(getApplicationContext(),
                                "Wrong email format.", Toast.LENGTH_LONG)
                                .show();
                        email1 = "not filled";

                    }

                }

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

                    zip1 = "not filled ";

                }

                if (TextUtils.isEmpty(cell1)) {

                    cell1 = "not filled ";

                }

                if (TextUtils.isEmpty(workNum1)) {

                    workNum1 = "not filled";

                }


                if (TextUtils.isEmpty(ext1)) {

                    ext1 = "not filled";

                }

                if (TextUtils.isEmpty(email1)) {

                    email1 = "not filled";

                }

                if (TextUtils.isEmpty(fax1)) {

                    fax1 = "not filled";

                }

                if (TextUtils.isEmpty(comments1)) {

                    comments1 = "not filled";

                }

                if (!TextUtils.isEmpty(state1)) {

                    if (state1.length() != 2) {
                        Toast.makeText(getApplicationContext(),
                                "Please enter state code (Ex: FL)",
                                Toast.LENGTH_LONG).show();
                        state.setText("");

                    } else {


                        DatabaseReference currentUser = mDatabase.push();
                        // DatabaseReference customerDB = currentUser.child("Customers").push();

                        customer.setCustomerID(currentUser.getKey());
                        customer.setCompanyName(companyName1);
                        customer.setCountry(country1);
                        customer.setAddress(address1);
                        customer.setCity(city1);
                        customer.setState(state1);
                        customer.setZipCode(zip1);
                        customer.setContactPersonName(contactPerson1);
                        customer.setContactPersonCell(cell1);
                        customer.setWorkNumber(workNum1);
                        customer.setExtension(ext1);
                        customer.setEmail(email1);
                        customer.setFax(fax1);
                        customer.setComments(comments1);

                        currentUser.setValue(customer);

                        Toast.makeText(getApplicationContext(),
                                "Customer Added Successfully",
                                Toast.LENGTH_LONG).show();

                        companyName.setText("");
                        address.setText("");
                        city.setText("");
                        state.setText("");
                        zip.setText("");
                        contactPerson.setText("");
                        cell.setText("");
                        workNum.setText("");
                        ext.setText("");
                        email.setText("");
                        fax.setText("");
                        comments.setText("");
                        country.setSelection(0);

                    }
                }
            }
        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }

    }


    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


}