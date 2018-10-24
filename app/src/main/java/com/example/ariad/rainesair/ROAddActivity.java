package com.example.ariad.rainesair;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
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
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ROAddActivity extends AppCompatActivity
        implements View.OnClickListener {

    Intent intent;
    EditText seriallNumber, description, trace, tagBy, tagDate,
            quantity, laborCost, partsCost, totalCost, customerName,
            customerEmail, customerPhone, repairDate,  shipTo, comments;

    Spinner companyName, partNumber, condition, status, terms, workRequested;
    String uid;
    Button save, cancel, btnDatePicker, btnDatePicker1, send;
    ImageView back;
    String cell, email;
    private int mYear, mMonth, mDay, year, month, day;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, mRef, mCustomer;

    List<String> partsList = new ArrayList<String>();
    List<String> customerList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadd);

        try {

            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            seriallNumber = (EditText) findViewById(R.id.ro_serialnumber);
            description = (EditText) findViewById(R.id.ro_description);
            trace = (EditText) findViewById(R.id.ro_trace);
            tagBy = (EditText) findViewById(R.id.ro_tagby);
            tagDate = (EditText) findViewById(R.id.ro_tagdate);
            quantity = (EditText) findViewById(R.id.ro_quantity);
            laborCost = (EditText) findViewById(R.id.ro_laborcost);
            partsCost = (EditText) findViewById(R.id.ro_partscost);
            totalCost = (EditText) findViewById(R.id.ro_totalcost);
            customerName = (EditText) findViewById(R.id.ro_contactname);
            customerEmail = (EditText) findViewById(R.id.ro_contactemail);
            customerPhone = (EditText) findViewById(R.id.ro_contactphone);
            repairDate = (EditText) findViewById(R.id.ro_repairdate);
            shipTo = (EditText) findViewById(R.id.ro_shipto);
            comments = (EditText) findViewById(R.id.ro_comments);

            save = (Button) findViewById(R.id.ro_save);
            cancel = (Button) findViewById(R.id.ro_cancel);
            back = (ImageView) findViewById(R.id.roAdd_back);
            send = (Button) findViewById(R.id.ro_send);


            btnDatePicker = (Button) findViewById(R.id.ro_selecttagdate);
            btnDatePicker.setOnClickListener(this);
            btnDatePicker1 = (Button) findViewById(R.id.ro_selectrepairdate);
            btnDatePicker1.setOnClickListener(this);


            companyName = (Spinner) findViewById(R.id.ro_companyname);
            condition = (Spinner) findViewById(R.id.ro_condition);
            partNumber = (Spinner) findViewById(R.id.ro_partnumber);
            status = (Spinner) findViewById(R.id.ro_status);
            terms = (Spinner) findViewById(R.id.ro_terms);
            workRequested = (Spinner) findViewById(R.id.ro_workrequested);


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(getApplicationContext(), ROActivity.class);
                    startActivity(intent);
                }
            });


            if (user != null) {
                uid = user.getUid();

                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("RO");
                mRef = FirebaseDatabase.getInstance().getReference().child(uid).child("Parts");
                mCustomer = FirebaseDatabase.getInstance().getReference().child(uid).child("Customers");

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


            save.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    addRO();
                }

            });

            send.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    sendEmail();
                }

            });

            cancel.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    seriallNumber.setText("");
                    description.setText("");
                    trace.setText("");
                    tagBy.setText("");
                    tagDate.setText("");
                    quantity.setText("");

                    laborCost.setText("");
                    partsCost.setText("");
                    totalCost.setText("");
                    customerName.setText("");
                    customerEmail.setText("");
                    customerPhone.setText("");
                    repairDate.setText("");
                    shipTo.setText("");
                    comments.setText("");

                    companyName.setSelection(0);
                    condition.setSelection(0);
                    partNumber.setSelection(0);
                    status.setSelection(0);
                    terms.setSelection(0);
                    workRequested.setSelection(0);
                }

            });


            back.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    intent = new Intent(getApplicationContext(), ROActivity.class);
                    startActivity(intent);
                }

            });

            // Parts

            final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    Parts part = dataSnapshot.getValue(Parts.class);

                    String partsInfo = part.getPartNumber();

                    partsList.add(partsInfo);
                    partNumber.setAdapter(spinnerAdapter);
                    spinnerAdapter.add(partsInfo);


                    spinnerAdapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



            //Customers

            final ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            mCustomer.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    Customers customers = dataSnapshot.getValue(Customers.class);

                    String customerInfo = customers.getCompanyName();


                    customerList.add(customerInfo);
                    companyName.setAdapter(spinnerAdapter1);
                    spinnerAdapter1.add(customerInfo);


                    spinnerAdapter1.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }
    }


    public void addRO() {

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
            String companyName1 = companyName.getSelectedItem().toString();
            String condition1 = condition.getSelectedItem().toString();
            String partNumber1 = partNumber.getSelectedItem().toString();
            String status1 = status.getSelectedItem().toString();
            String terms1 = terms.getSelectedItem().toString();
            String workRequested1 =  workRequested.getSelectedItem().toString();


            RO ro = new RO();

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


                DatabaseReference ro1 = mDatabase.push();
                ro.setRoNum(ro1.getKey());

                ro.setCompanyName(companyName1);
                ro.setPartNumber(partNumber1);
                ro.setSeriallNumber(seriallNumber1);
                ro.setDescription(description1);
                ro.setCondition(condition1);
                ro.setTrace(trace1);
                ro.setTagBy(tagBy1);
                ro.setTagDate(tagDate1);
                ro.setQuantity(quantity1);
                ro.setLaborCost(laborCost1);
                ro.setPartsCost(partsCost1);
                ro.setTotalCost(totalCost1);
                ro.setCustomerName(customerName1);
                ro.setCustomerEmail(customerEmail1);
                ro.setCustomerPhone(customerPhone1);
                ro.setRepairDate(repairDate1);

                ro.setTerms(terms1);
                ro.setStatus(status1);
                ro.setWorkRequested(workRequested1);
                ro.setShipTo(shipTo1);
                ro.setComments(comments1);



                ro1.setValue(ro);


                Toast.makeText(getApplicationContext(), "RO Added Successfully", Toast.LENGTH_SHORT).show();

                seriallNumber.setText("");
                description.setText("");
                trace.setText("");
                tagBy.setText("");
                tagDate.setText("");
                quantity.setText("");
                laborCost.setText("");
                partsCost.setText("");
                totalCost.setText("");
                customerName.setText("");
                customerEmail.setText("");
                customerPhone.setText("");
                repairDate.setText("");
                shipTo.setText("");
                comments.setText("");

                companyName.setSelection(0);
                condition.setSelection(0);
                partNumber.setSelection(0);
                status.setSelection(0);
                terms.setSelection(0);
                workRequested.setSelection(0);

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