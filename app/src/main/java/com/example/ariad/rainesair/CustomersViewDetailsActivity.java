package com.example.ariad.rainesair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Map;

public class CustomersViewDetailsActivity extends AppCompatActivity {

    EditText companyName, address, city, state, zip, contactPersonInfo, contactPersonCell, workNum, ext, email, fax, comments;
    Spinner country;
    Button update;
    ImageView back;
    Intent intent;
    String uid, selectedCustomer;

    private DatabaseReference mRef;
    private FirebaseUser user;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_view_details);

        try {

            user = FirebaseAuth.getInstance().getCurrentUser();

            intent = getIntent();
            selectedCustomer = intent.getStringExtra("CustomerID");

            mProgressDialog = new ProgressDialog(this);


            if (user != null) {
                uid = user.getUid();

            }
            companyName = (EditText) findViewById(R.id.customersView_company_name);
            address = (EditText) findViewById(R.id.customersView_address);
            city = (EditText) findViewById(R.id.customersView_city);
            state = (EditText) findViewById(R.id.customersView_state);
            zip = (EditText) findViewById(R.id.customersView_zip);
            contactPersonInfo = (EditText) findViewById(R.id.customersView_contact_person);
            contactPersonCell = (EditText) findViewById(R.id.customersView_contact_cell);
            workNum = (EditText) findViewById(R.id.customersView_work_number);
            ext = (EditText) findViewById(R.id.customersView_ext);
            email = (EditText) findViewById(R.id.customersView_email);
            fax = (EditText) findViewById(R.id.customersView_fax);
            comments = (EditText) findViewById(R.id.customersView_comments);
            country = (Spinner) findViewById(R.id.customersView_country);


            back = (ImageView) findViewById(R.id.customersView_back);
            update = (Button) findViewById(R.id.customersView_update);


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

            contactPersonInfo.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    contactPersonInfo.setFocusable(true);
                    contactPersonInfo.setFocusableInTouchMode(true);

                    return false;
                }
            });


            contactPersonCell.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    contactPersonCell.setFocusable(true);
                    contactPersonCell.setFocusableInTouchMode(true);

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


            update.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    updateCustomer();

                }
            });


            back.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    intent = new Intent(getApplicationContext(), CustomersActivity.class);
                    startActivity(intent);

                }
            });


            mRef = FirebaseDatabase.getInstance().getReference().child(uid).child("Customers").child(selectedCustomer);

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    mProgressDialog.setMessage("Loading Info..");
                    mProgressDialog.show();

                    int index = 0;


                    Customers customer = dataSnapshot.getValue(Customers.class);

                    if (dataSnapshot.hasChildren() == true) {
                        String companyName1 = customer.getCompanyName();
                        String address1 = customer.getAddress();
                        String city1 = customer.getCity();
                        String state1 = customer.getState();
                        String zip1 = customer.getZipCode();
                        String contactPersonInfo1 = customer.getContactPersonName();
                        String contactPersonCell1 = customer.getContactPersonCell();
                        String workNum1 = customer.getWorkNumber();
                        String ext1 = customer.getExtension();
                        String email1 = customer.getEmail();
                        String fax1 = customer.getFax();
                        String comments1 = customer.getComments();
                        String country1 = customer.getCountry();

                        for (int i = 0; i < country.getCount(); i++) {

                            if (country.getItemAtPosition(i).equals(country1)) {
                                index = i;
                            }
                        }

                        companyName.setText(companyName1);
                        address.setText(address1);
                        city.setText(city1);
                        state.setText(state1);
                        zip.setText(zip1);
                        contactPersonInfo.setText(contactPersonInfo1);
                        contactPersonCell.setText(contactPersonCell1);
                        workNum.setText(workNum1);
                        ext.setText(ext1);
                        email.setText(email1);
                        fax.setText(fax1);
                        comments.setText(comments1);
                        country.setSelection(index);

                        mProgressDialog.dismiss();
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }

    }


    public void updateCustomer() {

        try {

            String companyName1 = companyName.getText().toString();
            String address1 = address.getText().toString();
            String city1 = city.getText().toString();
            String state1 = state.getText().toString();
            String zip1 = zip.getText().toString();
            String contactPerson1 = contactPersonInfo.getText().toString();
            String cell1 = contactPersonCell.getText().toString();
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
                contactPersonInfo.setHint("* Contact Person Name Required");
                contactPersonInfo.setHintTextColor(Color.parseColor("#FF0000"));


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
                Customers customerUpdate = new Customers(address1, city1, comments1, companyName1, cell1, contactPerson1,
                        country1, email1, ext1, fax1, state1, workNum1, zip1);


                Map<String, Object> customerValues = customerUpdate.toMap();


                mRef.updateChildren(customerValues);

                Toast.makeText(CustomersViewDetailsActivity.this,
                        "Profile updated successfully",
                        Toast.LENGTH_LONG).show();


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
