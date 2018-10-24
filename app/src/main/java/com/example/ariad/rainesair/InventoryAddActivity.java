package com.example.ariad.rainesair;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InventoryAddActivity extends AppCompatActivity
    implements View.OnClickListener {


    public static final int RESULT_LOAD = 1;
    Button save, cancel, btnDatePicker;
    Intent intent;
    String uid;
    ImageView pic;
    Inventory inventory = new Inventory();
    private int mYear, mMonth, mDay;

    List<String> partsList = new ArrayList<String>();

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, mRef;


    Spinner partNumber, condition;
    EditText seriallNumber, description, trace, tagBy, tagDate,
     cost, quantity, totalInvested, listPrice, comments;
    String inventoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_add);


        try {


            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            partNumber = (Spinner) findViewById(R.id.addInventory_parts_number);
            condition = (Spinner) findViewById(R.id.addInventory_condition);

            seriallNumber = (EditText) findViewById(R.id.addInventory_serialnumber);
            description = (EditText) findViewById(R.id.addInventory_description);
            trace = (EditText) findViewById(R.id.addInventory_trace);
            tagBy = (EditText) findViewById(R.id.addInventory_tagby);
            tagDate = (EditText) findViewById(R.id.addInventory_tagdate);
            cost = (EditText) findViewById(R.id.addInventory_cost);
            quantity = (EditText) findViewById(R.id.addInventory_quantity);
            totalInvested = (EditText) findViewById(R.id.addInventory_totalInvested);
            listPrice = (EditText) findViewById(R.id.addInventory_listPrice);
            comments = (EditText) findViewById(R.id.addInventory_comments);


            save = (Button) findViewById(R.id.addInventory_save);
            cancel = (Button) findViewById(R.id.addInventory_cancel);

            btnDatePicker = (Button) findViewById(R.id.addInventory_selectdate);
            btnDatePicker.setOnClickListener(this);

            final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ImageView back = (ImageView) findViewById(R.id.addInventory_back);

            if (user != null) {
                uid = user.getUid();

                mRef = FirebaseDatabase.getInstance().getReference().child(uid).child("Inventory");

            mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("Parts");


            mDatabase.addChildEventListener(new ChildEventListener() {
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

            }

            back.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), InventoryActivity.class);
                    startActivity(intent);


                }
            });


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

            cost.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    cost.setFocusable(true);
                    cost.setFocusableInTouchMode(true);

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


            totalInvested.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    totalInvested.setFocusable(true);
                    totalInvested.setFocusableInTouchMode(true);

                    return false;
                }
            });

            listPrice.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    listPrice.setFocusable(true);
                    listPrice.setFocusableInTouchMode(true);

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

                    addInventory();


                }
            });


            cancel.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    partNumber.setSelection(0);
                    condition.setSelection(0);

                    seriallNumber.setText("");
                    description.setText("");
                    trace.setText("");
                    tagBy.setText("");
                    tagDate.setText("");
                    cost.setText("");
                    quantity.setText("");
                    totalInvested.setText("");
                    listPrice.setText("");
                    comments.setText("");


                }
            });


        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }

    }



    public void addInventory() {

        try {


            String partNumber1 = partNumber.getSelectedItem().toString();
            String seriallNumber1 = seriallNumber.getText().toString();
            String description1 = description.getText().toString();
            String condition1 = condition.getSelectedItem().toString();
            String trace1 = trace.getText().toString();
            String tagBy1 = tagBy.getText().toString();
            String tagDate1 = tagDate.getText().toString();
            String cost1 = cost.getText().toString();
            String quantity1 = quantity.getText().toString();
            String totalInvested1 = totalInvested.getText().toString();
            String listPrice1 = listPrice.getText().toString();
            String comments1 = comments.getText().toString();



            if (TextUtils.isEmpty(seriallNumber1)) {

                Toast.makeText(getApplicationContext(), "Serial Number Required", Toast.LENGTH_SHORT).show();
                seriallNumber.setHint("* Serial Number Required");
                seriallNumber.setHintTextColor(Color.parseColor("#FF0000"));

            } else if (TextUtils.isEmpty(description1)) {

                Toast.makeText(getApplicationContext(), "Description Required", Toast.LENGTH_SHORT).show();
                description.setHint("* Description Required");
                description.setHintTextColor(Color.parseColor("#FF0000"));

            } else if (TextUtils.isEmpty(trace1)) {

                Toast.makeText(getApplicationContext(), "Trace Required", Toast.LENGTH_SHORT).show();
                trace.setHint("* Trace Required");
                trace.setHintTextColor(Color.parseColor("#FF0000"));

            } else if (TextUtils.isEmpty(tagBy1)) {

                Toast.makeText(getApplicationContext(), "Tag By Required", Toast.LENGTH_SHORT).show();
                tagBy.setHint("* Tag By Required");
                tagBy.setHintTextColor(Color.parseColor("#FF0000"));

            } else if (TextUtils.isEmpty(tagDate1)) {

                Toast.makeText(getApplicationContext(), "Tag Date Required", Toast.LENGTH_SHORT).show();
                tagDate.setHint("* Tag Date Required");
                tagDate.setHintTextColor(Color.parseColor("#FF0000"));

            } else if (TextUtils.isEmpty(cost1)) {

                Toast.makeText(getApplicationContext(), "Cost Required", Toast.LENGTH_SHORT).show();
                cost.setHint("* Cost Required");
                cost.setHintTextColor(Color.parseColor("#FF0000"));

            } else if (TextUtils.isEmpty(quantity1)) {

                Toast.makeText(getApplicationContext(), "Quantity Required", Toast.LENGTH_SHORT).show();
                quantity.setHint("* Quantity Required");
                quantity.setHintTextColor(Color.parseColor("#FF0000"));


            } else if (TextUtils.isEmpty(totalInvested1)) {

                Toast.makeText(getApplicationContext(), "Total Invested Required", Toast.LENGTH_SHORT).show();
                totalInvested.setHint("* Total Invested Required");
                totalInvested.setHintTextColor(Color.parseColor("#FF0000"));


            } else if (TextUtils.isEmpty(listPrice1)) {

                Toast.makeText(getApplicationContext(), "List Price Required", Toast.LENGTH_SHORT).show();
                listPrice.setHint("* List Price Required");
                listPrice.setHintTextColor(Color.parseColor("#FF0000"));



            } else {

                DatabaseReference currentUser = mRef.push();

                inventory.setInventoryID(currentUser.getKey());
                inventory.setPartNumber(partNumber1);
                inventory.setSeriallNumber(seriallNumber1);
                inventory.setDescription(description1);
                inventory.setCondition(condition1);
                inventory.setTrace(trace1);
                inventory.setTagBy(tagBy1);
                inventory.setTagDate(tagDate1);
                inventory.setCost(cost1);
                inventory.setQuantity(quantity1);
                inventory.setListPrice(listPrice1);
                inventory.setTotalInvested(totalInvested1);
                inventory.setComments(comments1);

                currentUser.setValue(inventory);

                Toast.makeText(getApplicationContext(),
                        "Inventory Added Successfully",
                        Toast.LENGTH_LONG).show();

                partNumber.setSelection(0);
                condition.setSelection(0);

                seriallNumber.setText("");
                description.setText("");
                trace.setText("");
                tagBy.setText("");
                tagDate.setText("");
                cost.setText("");
                quantity.setText("");
                totalInvested.setText("");
                listPrice.setText("");
                comments.setText("");



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

    }


}

