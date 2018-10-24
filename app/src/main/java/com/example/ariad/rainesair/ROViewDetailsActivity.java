package com.example.ariad.rainesair;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Map;

public class ROViewDetailsActivity extends AppCompatActivity
    implements View.OnClickListener {

    Intent intent;
    EditText seriallNumber, description, trace, tagBy, tagDate,
            quantity, laborCost, partsCost, totalCost, customerName,
            customerEmail, customerPhone, repairDate,  shipTo, comments;
    TextView companyName, partNumber;

    Spinner condition, status, terms, workRequested;
    String uid, selectedRO;
    Button update, btnDatePicker, btnDatePicker1, send;
    ImageView back;
    String cell, email;
    private int mYear, mMonth, mDay, year, month, day;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roview_details);

        try {

            intent = getIntent();
            selectedRO = intent.getStringExtra("roID");

            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            seriallNumber = (EditText) findViewById(R.id.roDetails_serialnumber);
            description = (EditText) findViewById(R.id.roDetails_description);
            trace = (EditText) findViewById(R.id.roDetails_trace);
            tagBy = (EditText) findViewById(R.id.roDetails_tagby);
            tagDate = (EditText) findViewById(R.id.roDetails_tagdate);
            quantity = (EditText) findViewById(R.id.roDetails_quantity);
            laborCost = (EditText) findViewById(R.id.roDetails_laborcost);
            partsCost = (EditText) findViewById(R.id.roDetails_partscost);
            totalCost = (EditText) findViewById(R.id.roDetails_totalcost);
            customerName = (EditText) findViewById(R.id.roDetails_customername);
            customerEmail = (EditText) findViewById(R.id.roDetails_customeremail);
            customerPhone = (EditText) findViewById(R.id.roDetails_customerphone);
            repairDate = (EditText) findViewById(R.id.roDetails_repairdate);
            shipTo = (EditText) findViewById(R.id.roDetails_shipto);
            comments = (EditText) findViewById(R.id.roDetails_comments);

            update = (Button) findViewById(R.id.roDetails_update);
            back = (ImageView) findViewById(R.id.roDetailsAdd_back);

            btnDatePicker = (Button) findViewById(R.id.roDetails_selectdate);
            btnDatePicker.setOnClickListener(this);
            btnDatePicker1 = (Button) findViewById(R.id.roDetails_selectrepairdate);
            btnDatePicker1.setOnClickListener(this);


            companyName = (TextView) findViewById(R.id.roDetails_companyname);
            condition = (Spinner) findViewById(R.id.roDetails_condition);
            partNumber = (TextView) findViewById(R.id.roDetails_partnumber);
            status = (Spinner) findViewById(R.id.roDetails_status);
            terms = (Spinner) findViewById(R.id.roDetails_terms);
            workRequested = (Spinner) findViewById(R.id.roDetails_workrequested);
            send = (Button) findViewById(R.id.roDetails_send);


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(getApplicationContext(), ROActivity.class);
                    startActivity(intent);
                }
            });


            if (user != null) {
                uid = user.getUid();

                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("RO").child(selectedRO);

            }


            seriallNumber.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    seriallNumber.setFocusable(true);
                    seriallNumber.setFocusableInTouchMode(true);

                    return false;
                }
            });

            description.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    description.setFocusable(true);
                    description.setFocusableInTouchMode(true);

                    return false;
                }
            });

            trace.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    trace.setFocusable(true);
                    trace.setFocusableInTouchMode(true);

                    return false;
                }
            });


            tagBy.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    tagBy.setFocusable(true);
                    tagBy.setFocusableInTouchMode(true);

                    return false;
                }
            });


            tagDate.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    tagDate.setFocusable(true);
                    tagDate.setFocusableInTouchMode(true);

                    return false;
                }
            });


            quantity.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    quantity.setFocusable(true);
                    quantity.setFocusableInTouchMode(true);

                    return false;
                }
            });


            laborCost.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    laborCost.setFocusable(true);
                    laborCost.setFocusableInTouchMode(true);

                    return false;
                }
            });

            partsCost.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    partsCost.setFocusable(true);
                    partsCost.setFocusableInTouchMode(true);

                    return false;
                }
            });

            totalCost.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    totalCost.setFocusable(true);
                    totalCost.setFocusableInTouchMode(true);

                    return false;
                }
            });


            customerName.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    customerName.setFocusable(true);
                    customerName.setFocusableInTouchMode(true);

                    return false;
                }
            });


            customerEmail.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    customerEmail.setFocusable(true);
                    customerEmail.setFocusableInTouchMode(true);

                    return false;
                }
            });


            customerPhone.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    customerPhone.setFocusable(true);
                    customerPhone.setFocusableInTouchMode(true);

                    return false;
                }
            });


            repairDate.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    repairDate.setFocusable(true);
                    repairDate.setFocusableInTouchMode(true);

                    return false;
                }
            });

            shipTo.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    shipTo.setFocusable(true);
                    shipTo.setFocusableInTouchMode(true);

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

            send.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    sendEmail();
                }

            });


            update.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    updateRO();
                }

            });


            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.hasChildren() == true ) {
                        int indexCondition = 0, indexStatus = 0, indexTerms = 0;
                        RO ro = dataSnapshot.getValue(RO.class);

                        String companyName1 = ro.getCompanyName();
                        String partNumber1 = ro.getPartNumber();
                        String seriallNumber1 = ro.getSeriallNumber();
                        String description1 = ro.getDescription();
                        String trace1 = ro.getTrace();
                        String tagBy1 = ro.getTagBy();
                        String tagDate1 = ro.getTagDate();
                        String quantity1 = ro.getQuantity();
                        String laborCost1 = ro.getLaborCost();
                        String partsCost1 = ro.getPartsCost();
                        String totalCost1 = ro.getTotalCost();
                        String customerName1 = ro.getCustomerName();
                        String customerEmail1 = ro.getCustomerEmail();
                        String customerPhone1 = ro.getCustomerPhone();
                        String repairDate1 = ro.getRepairDate();
                        String shipTo1 = ro.getShipTo();
                        String comments1 = ro.getComments();

                        String workRequested1 = ro.getWorkRequested();
                        String condition1 = ro.getCondition();
                        String status1 = ro.getStatus();
                        String terms1 = ro.getTerms();

                        for (int i = 0; i < condition.getCount(); i++) {

                            if (condition.getItemAtPosition(i).equals(condition1)) {
                                indexCondition = i;
                            }
                        }
                        for (int i = 0; i < status.getCount(); i++) {

                            if (status.getItemAtPosition(i).equals(status1)) {
                                indexStatus = i;
                            }
                        }

                        for (int i = 0; i < terms.getCount(); i++) {

                            if (terms.getItemAtPosition(i).equals(terms1)) {
                                indexTerms = i;
                            }
                        }

                        companyName.setText(companyName1);
                        partNumber.setText(partNumber1);
                        seriallNumber.setText(seriallNumber1);
                        description.setText(description1);
                        trace.setText(trace1);
                        tagBy.setText(tagBy1);
                        tagDate.setText(tagDate1);
                        quantity.setText(quantity1);
                        laborCost.setText(laborCost1);
                        partsCost.setText(partsCost1);
                        totalCost.setText(totalCost1);
                        customerName.setText(customerName1);
                        customerEmail.setText(customerEmail1);
                        customerPhone.setText(customerPhone1);
                        repairDate.setText(repairDate1);
                        shipTo.setText(shipTo1);
                        comments.setText(comments1);


                        condition.setSelection(indexCondition);
                        status.setSelection(indexStatus);
                        terms.setSelection(indexTerms);

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


    public void updateRO() {

        try {

            String  seriallNumber1 = seriallNumber.getText().toString();
            String  description1 = description.getText().toString();
            String trace1 = trace.getText().toString();
            String  tagBy1 = tagBy.getText().toString();
            String tagDate1 = tagDate.getText().toString();
            String  quantity1 = quantity.getText().toString();
            String laborCost1 = laborCost.getText().toString();
            String  partsCost1 = partsCost.getText().toString();
            String totalCost1 = totalCost.getText().toString();
            String  customerName1 = customerName.getText().toString();
            String  customerEmail1 = customerEmail.getText().toString();
            String  customerPhone1 = customerPhone.getText().toString();
            String repairDate1 = repairDate.getText().toString();
            String shipTo1 = shipTo.getText().toString();
            String comments1 = comments.getText().toString();
            String companyName1 = companyName.getText().toString();
            String condition1 = condition.getSelectedItem().toString();
            String partNumber1 = partNumber.getText().toString();
            String status1 = status.getSelectedItem().toString();
            String terms1 = terms.getSelectedItem().toString();
            String workRequested1 =  workRequested.getSelectedItem().toString();


            if (TextUtils.isEmpty(seriallNumber1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the serial number", Toast.LENGTH_SHORT).show();

            }
            else if (TextUtils.isEmpty(description1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the description", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(trace1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the trace", Toast.LENGTH_SHORT).show();


            } else if (TextUtils.isEmpty(tagBy1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the tag by", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(tagDate1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the tag date", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(quantity1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the quantity", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(laborCost1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the labor cost", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(partsCost1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the parts cost", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(totalCost1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the total cost", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(repairDate1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the repair date", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(shipTo1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the ship to", Toast.LENGTH_SHORT).show();

            }


            else {

                if  (TextUtils.isEmpty(customerName1)) {
                    customerName1 = "not filled";
                }
                if  (TextUtils.isEmpty(customerEmail1)) {
                    customerEmail1 = "not filled";
                }
                if  (TextUtils.isEmpty(customerPhone1)) {
                    customerPhone1 = "not filled";
                }
                if  (TextUtils.isEmpty(comments1)) {
                    comments1 = "none";
                }


                RO roUpdate = new RO( comments1, companyName1, condition1, customerEmail1, customerName1,
                        customerPhone1, description1, laborCost1, repairDate1, partNumber1,
                        partsCost1, quantity1, " ", seriallNumber1, shipTo1, status1, tagBy1,
                        tagDate1, terms1, totalCost1, trace1, workRequested1);

                Map<String, Object> poValues = roUpdate.toMap();

                mDatabase.updateChildren(poValues);
                Toast.makeText(getApplicationContext(), "RO Added Successfully", Toast.LENGTH_SHORT).show();

            }

        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            tagDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }


        else  if (v == btnDatePicker1) {

            // Get Current Date
            final Calendar d = Calendar.getInstance();
            year = d.get(Calendar.YEAR);
            month = d.get(Calendar.MONTH);
            day = d.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year1,
                                              int monthOfYear1, int dayOfMonth1) {

                            repairDate.setText(dayOfMonth1 + "-" + (monthOfYear1 + 1) + "-" + year1);

                        }
                    }, year, month, day);
            datePickerDialog1.show();
        }

    }

    private void sendEmail() {

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren() == true) {

                    RO ro = dataSnapshot.getValue(RO.class);

                    String companyName1 = ro.getCompanyName();
                    String partNumber1 = ro.getPartNumber();
                    String seriallNumber1 = ro.getSeriallNumber();
                    String description1 = ro.getDescription();
                    String trace1 = ro.getTrace();
                    String tagBy1 = ro.getTagBy();
                    String tagDate1 = ro.getTagDate();
                    String quantity1 = ro.getQuantity();
                    String laborCost1 = ro.getLaborCost();
                    String partsCost1 = ro.getPartsCost();
                    String totalCost1 = ro.getTotalCost();
                    String customerName1 = ro.getCustomerName();
                    String customerEmail1 = ro.getCustomerEmail();
                    String customerPhone1 = ro.getCustomerPhone();
                    String repairDate1 = ro.getRepairDate();
                    String shipTo1 = ro.getShipTo();
                    String comments1 = ro.getComments();

                    String workRequested1 = ro.getWorkRequested();
                    String condition1 = ro.getCondition();
                    String status1 = ro.getStatus();
                    String terms1 = ro.getTerms();

                    String emailBody = "Company Name: " + companyName1 + "\n" +
                            "Part Number: " + partNumber1 + "\n" + "Serial Number: " + seriallNumber1 + "\n" +
                            "Description: " + description1 + "\n" + "Condition: " + condition1 + "\n" +
                            "Trace: " + trace1 + "\n" + "Tag By: " + tagBy1 + "\n" +
                            "Tag Date: " + tagDate1 + "\n" + "Quantity: " + quantity1 + "\n" +
                            "Labor Cost: " + laborCost1 + "\n" + "Parts Cost: " + partsCost1 + "\n" +
                            "Total Cost: " + totalCost1 + "\n" + "Customer Name: " + customerName1 + "\n" +
                            "Customer Email1: " + customerEmail1 + "\n" + "Customer Phone: " + customerPhone1 + "\n" +
                            "Repair Date: " + repairDate1 + "\n" + "Work Requested: " + workRequested1 + "\n" +
                            "Status: " + status1 + "\n" +  "Terms: " + terms1 + "\n" +
                            "Ship To: " + shipTo1 + "\n" + "Comments: " + comments1;

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{customerEmail1});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Repair Order");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
                    emailIntent.setType("message/rfc822");
                    startActivity(Intent.createChooser(emailIntent, "Choose email client..."));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}