package com.example.ariad.rainesair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
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
import com.google.firebase.storage.StorageReference;

import java.util.Map;

public class PartsViewDetailsActivity extends AppCompatActivity {


    EditText partNumber, seriallNumber, description, trace, tagBy, tagDate, comments;
    Spinner condition;
    Button update, publish;
    ImageView back;
    Intent intent;
    String uid, selectedPart;
    String first, last, cell, email;

    private DatabaseReference mRef, mDatabase, mUser;
    private FirebaseUser user;
    private StorageReference storage;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts_view_details);


        try {

            user = FirebaseAuth.getInstance().getCurrentUser();
            mRef = FirebaseDatabase.getInstance().getReference().child("PublishedParts");

            intent = getIntent();
            selectedPart = intent.getStringExtra("partID");

            mProgressDialog = new ProgressDialog(this);


            if (user != null) {
                uid = user.getUid();

            }

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

            partNumber = (EditText) findViewById(R.id.partsView_number);
            seriallNumber = (EditText) findViewById(R.id.partsView_serialnumber);
            description = (EditText) findViewById(R.id.partsView_description);
            condition = (Spinner) findViewById(R.id.partsView_condition);
            trace = (EditText) findViewById(R.id.partsView_trace);
            tagBy = (EditText) findViewById(R.id.partsView_tagby);
            tagDate = (EditText) findViewById(R.id.partsView_tagdate);
            comments = (EditText) findViewById(R.id.partsView_comments);


            back = (ImageView) findViewById(R.id.partsView_back);
            update = (Button) findViewById(R.id.partsView_update);

            publish = (Button) findViewById(R.id.partsView_publish);


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


            condition.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    condition.setFocusable(true);
                    condition.setFocusableInTouchMode(true);

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


            update.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    updatePart();

                }
            });


            back.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    intent = new Intent(getApplicationContext(), PartsActivity.class);
                    startActivity(intent);

                }
            });

            publish.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    publishPart();
                }

            });


            mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("Parts").child(selectedPart);

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    mProgressDialog.setMessage("Loading Info..");
                    mProgressDialog.show();

                    int index = 0;


                    Parts part = dataSnapshot.getValue(Parts.class);

                    if (dataSnapshot.hasChildren() == true) {
                        String partNumber1 = part.getPartNumber();
                        String seriallNumber1 = part.getSeriallNumber();
                        String description1 = part.getDescription();
                        String condition1 = part.getCondition();
                        String trace1 = part.getTrace();
                        String tagBy1 = part.getTagBy();
                        String tagDate1 = part.getTagDate();
                        String comments1 = part.getComments();


                        for (int i = 0; i < condition.getCount(); i++) {

                            if (condition.getItemAtPosition(i).equals(condition1)) {
                                index = i;
                            }
                        }


                        partNumber.setText(partNumber1);
                        seriallNumber.setText(seriallNumber1);
                        description.setText(description1);
                        trace.setText(trace1);
                        tagBy.setText(tagBy1);
                        tagDate.setText(tagDate1);
                        comments.setText(comments1);
                        condition.setSelection(index);


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


    public void updatePart() {

        try {

            String partNumber1 = partNumber.getText().toString();
            String seriallNumber1 = seriallNumber.getText().toString();
            String description1 = description.getText().toString();
            String condition1 = condition.getSelectedItem().toString();
            String trace1 = trace.getText().toString();
            String tagBy1 = tagBy.getText().toString();
            String tagDate1 = tagDate.getText().toString();
            String comments1 = comments.getText().toString();


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


                Parts partUpdate = new Parts(comments1, condition1, description1, partNumber1, seriallNumber1,
                        tagBy1, tagDate1, trace1, " ");


                Map<String, Object> partsValues = partUpdate.toMap();


                mDatabase.updateChildren(partsValues);

                Toast.makeText(PartsViewDetailsActivity.this,
                        "Profile updated successfully",
                        Toast.LENGTH_LONG).show();


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


}
