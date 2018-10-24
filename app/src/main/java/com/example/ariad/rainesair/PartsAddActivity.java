package com.example.ariad.rainesair;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PartsAddActivity extends AppCompatActivity
        implements View.OnClickListener {


    Intent intent;
    EditText partNumber, seriallNumber, description, trace, tagBy, tagDate, comments;
    Spinner condition;
    String uid;
    Button save, cancel, btnDatePicker, publish;
    ImageView back;
    private int mYear, mMonth, mDay;
    String first, last, cell, email;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, mRef, mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts_add);

        try {

            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            mRef = FirebaseDatabase.getInstance().getReference().child("PublishedParts");


            partNumber = (EditText) findViewById(R.id.addparts_number);
            seriallNumber = (EditText) findViewById(R.id.addparts_serialnumber);
            description = (EditText) findViewById(R.id.addparts_description);
            condition = (Spinner) findViewById(R.id.addparts_condition);
            trace = (EditText) findViewById(R.id.addparts_trace);
            tagBy = (EditText) findViewById(R.id.addparts_tagby);
            tagDate = (EditText) findViewById(R.id.addparts_tagdate);
            comments = (EditText) findViewById(R.id.addparts_comments);

            save = (Button) findViewById(R.id.addparts_save);
            cancel = (Button) findViewById(R.id.addparts_cancel);
            back = (ImageView) findViewById(R.id.addParts_back);

            publish = (Button) findViewById(R.id.addparts_publish);
            btnDatePicker = (Button) findViewById(R.id.addparts_selectdate);
            btnDatePicker.setOnClickListener(this);



            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(getApplicationContext(), PartsActivity.class);
                    startActivity(intent);
                }
            });



            if (user != null) {

                uid = user.getUid();

                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("Parts");
                mUser = FirebaseDatabase.getInstance().getReference().child(uid).child("PersonalInfo");

                mUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Users user = dataSnapshot.getValue(Users.class);

                        first = user.getFirstName();
                        last = user.getLastName();
                        cell = user.getCell();
                        email = user.getEmail();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            partNumber.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    partNumber.setFocusable(true);
                    partNumber.setFocusableInTouchMode(true);

                    return false;
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


            comments.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    comments.setFocusable(true);
                    comments.setFocusableInTouchMode(true);

                    return false;
                }
            });


            publish.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                   publishPart();
                }

            });


            save.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    addPart();
                }

            });


            cancel.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    partNumber.setText("");
                    seriallNumber.setText("");
                    description.setText("");
                    tagBy.setText("");
                    tagDate.setText("");
                    trace.setText("");
                    comments.setText("");
                }

            });

        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }
    }


       public void addPart() {

        try {


            String partNumber1 = partNumber.getText().toString();
            String seriallNumber1 = seriallNumber.getText().toString();
            String description1 = description.getText().toString();
            String condition1 = condition.getSelectedItem().toString();
            String trace1 = trace.getText().toString();
            String tagBy1 = tagBy.getText().toString();
            String tagDate1 = tagDate.getText().toString();
            String comments1 = comments.getText().toString();

            Parts part = new Parts();

            if (TextUtils.isEmpty(partNumber1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the part number", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(seriallNumber1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the serial number", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(description1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the description", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(trace1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the trace", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(tagBy1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the tag by", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(tagDate1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the tag date", Toast.LENGTH_SHORT).show();

            } else {

                DatabaseReference currentUser = mDatabase.push();
                part.setPartID(currentUser.getKey());

                part.setPublisherId(uid);
                part.setPartNumber(partNumber1);
                part.setSeriallNumber(seriallNumber1);
                part.setDescription(description1);
                part.setCondition(condition1);
                part.setTrace(trace1);
                part.setTagBy(tagBy1);
                part.setTagDate(tagDate1);
                part.setComments(comments1);

                currentUser.setValue(part);


                Toast.makeText(getApplicationContext(), "Part Added Successfully", Toast.LENGTH_SHORT).show();

            }

        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }
    }



    public void publishPart() {

        try {



            String partNumber1 = partNumber.getText().toString();
            String seriallNumber1 = seriallNumber.getText().toString();
            String description1 = description.getText().toString();
            String condition1 = condition.getSelectedItem().toString();
            String trace1 = trace.getText().toString();
            String tagBy1 = tagBy.getText().toString();
            String tagDate1 = tagDate.getText().toString();
            String comments1 = comments.getText().toString();

            Parts part = new Parts();

            if (TextUtils.isEmpty(partNumber1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the part number", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(seriallNumber1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the serial number", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(description1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the description", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(trace1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the trace", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(tagBy1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the tag by", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(tagDate1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the tag date", Toast.LENGTH_SHORT).show();

            } else {

                DatabaseReference publishPart = mRef.push();
                part.setPartID(publishPart.getKey());



                part.setPartNumber(partNumber1);
                part.setSeriallNumber(seriallNumber1);
                part.setDescription(description1);
                part.setCondition(condition1);
                part.setTrace(trace1);
                part.setTagBy(tagBy1);
                part.setTagDate(tagDate1);
                part.setComments(comments1);
                part.setPublisherId(uid);

                part.setPublisherFirst(first);
                part.setPublisherLast(last);
                part.setPublisherCell(cell);
                part.setPublisherEmail(email);



                publishPart.setValue(part);

                partNumber.setText("");
                seriallNumber.setText("");
                description.setText("");
                tagBy.setText("");
                tagDate.setText("");
                trace.setText("");
                comments.setText("");

                Toast.makeText(getApplicationContext(), "Part Published Successfully", Toast.LENGTH_SHORT).show();

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
