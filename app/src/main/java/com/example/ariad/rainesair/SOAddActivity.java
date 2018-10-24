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

public class SOAddActivity extends AppCompatActivity
        implements View.OnClickListener {

    Intent intent;
    EditText seriallNumber, description, trace, tagBy, tagDate, price,
            quantity, creditCardFee, wireTransferFee, dgDocFee, packingFee, shippingCost,
            salesTotal, purchaserName, purchaserEmail, purchaserPhone, salesDate, shipTo, comments;

    Spinner companyName, partNumber, condition, status, terms;
    String uid;
    Button save, cancel, btnDatePicker, btnDatePicker1, send;
    ImageView back;
    String  cell, email;
    private int mYear, mMonth, mDay, year, month, day;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, mRef, mCustomer;



    List<String> partsList = new ArrayList<String>();
    List<String> customerList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soadd);

        try {

            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            seriallNumber = (EditText) findViewById(R.id.so_serialnumber);
            description = (EditText) findViewById(R.id.so_description);
            trace = (EditText) findViewById(R.id.so_trace);
            tagBy = (EditText) findViewById(R.id.so_tagby);
            tagDate = (EditText) findViewById(R.id.so_tagdate);
            price = (EditText) findViewById(R.id.so_price);
            quantity = (EditText) findViewById(R.id.so_quantity);


            creditCardFee = (EditText) findViewById(R.id.so_creditcardfee);
            wireTransferFee = (EditText) findViewById(R.id.so_wiretransferfee);
            dgDocFee = (EditText) findViewById(R.id.so_dgdocuments);
            packingFee = (EditText) findViewById(R.id.so_packing);
            shippingCost = (EditText) findViewById(R.id.so_shippingcost);
            salesTotal = (EditText) findViewById(R.id.so_salestotal);
            purchaserName = (EditText) findViewById(R.id.so_contactname);
            purchaserEmail = (EditText) findViewById(R.id.so_contactemail);
            purchaserPhone = (EditText) findViewById(R.id.so_contactphone);
            salesDate = (EditText) findViewById(R.id.so_salesdate);
            shipTo = (EditText) findViewById(R.id.so_shipto);
            comments = (EditText) findViewById(R.id.so_comments);

            save = (Button) findViewById(R.id.so_save);
            cancel = (Button) findViewById(R.id.so_cancel);
            back = (ImageView) findViewById(R.id.soAdd_back);
            send = (Button) findViewById(R.id.so_send);

            btnDatePicker = (Button) findViewById(R.id.so_selectdate);
            btnDatePicker.setOnClickListener(this);

            btnDatePicker1 = (Button) findViewById(R.id.so_selecteddate);
            btnDatePicker1.setOnClickListener(this);


            companyName = (Spinner) findViewById(R.id.so_companyname);
            condition = (Spinner) findViewById(R.id.so_condition);
            partNumber = (Spinner) findViewById(R.id.so_partnumber);
            status = (Spinner) findViewById(R.id.so_status);
            terms = (Spinner) findViewById(R.id.so_terms);


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(getApplicationContext(), SOActivity.class);
                    startActivity(intent);
                }
            });


            if (user != null) {
               uid = user.getUid();

                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("SO");
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


            price.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    price.setFocusable(true);
                    price.setFocusableInTouchMode(true);

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


            creditCardFee.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    creditCardFee.setFocusable(true);
                    creditCardFee.setFocusableInTouchMode(true);

                    return false;
                }
            });

            wireTransferFee.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    wireTransferFee.setFocusable(true);
                    wireTransferFee.setFocusableInTouchMode(true);

                    return false;
                }
            });

            dgDocFee.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dgDocFee.setFocusable(true);
                    dgDocFee.setFocusableInTouchMode(true);

                    return false;
                }
            });


            packingFee.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    packingFee.setFocusable(true);
                    packingFee.setFocusableInTouchMode(true);

                    return false;
                }
            });


            shippingCost.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    shippingCost.setFocusable(true);
                    shippingCost.setFocusableInTouchMode(true);

                    return false;
                }
            });


            salesTotal.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    salesTotal.setFocusable(true);
                    salesTotal.setFocusableInTouchMode(true);

                    return false;
                }
            });


            purchaserName.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    purchaserName.setFocusable(true);
                    purchaserName.setFocusableInTouchMode(true);

                    return false;
                }
            });


            purchaserEmail.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    purchaserEmail.setFocusable(true);
                    purchaserEmail.setFocusableInTouchMode(true);

                    return false;
                }
            });

            purchaserPhone.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    purchaserPhone.setFocusable(true);
                    purchaserPhone.setFocusableInTouchMode(true);

                    return false;
                }
            });

            salesDate.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    salesDate.setFocusable(true);
                    salesDate.setFocusableInTouchMode(true);

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

                    addSO();
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

                    price.setText("");
                    quantity.setText("");

                    creditCardFee.setText("");
                    wireTransferFee.setText("");
                    dgDocFee.setText("");
                    packingFee.setText("");
                    shippingCost.setText("");
                    salesTotal.setText("");
                    purchaserName.setText("");
                    purchaserEmail.setText("");
                    purchaserPhone.setText("");
                    salesDate.setText("");
                    comments.setText("");

                    companyName.setSelection(0);
                    condition.setSelection(0);
                    partNumber.setSelection(0);
                    status.setSelection(0);
                    terms.setSelection(0);
                }

            });


            back.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    intent = new Intent(getApplicationContext(), SOActivity.class);
                    startActivity(intent);
                }

            });


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


    public void addSO() {

        try {


            String  seriallNumber1 = seriallNumber.getText().toString();
            String  description1 = description.getText().toString();
            String trace1 = trace.getText().toString();
            String  tagBy1 = tagBy.getText().toString();
            String tagDate1 = tagDate.getText().toString();

            String  price1 = price.getText().toString();
            String  quantity1 = quantity.getText().toString();
            String creditCardFee1 = creditCardFee.getText().toString();
            String  wireTransferFee1 = wireTransferFee.getText().toString();
            String dgDocFee1 = dgDocFee.getText().toString();
            String  packingFee1 = packingFee.getText().toString();
            String  shippingCost1 = shippingCost.getText().toString();
            String  purchaseTotal1 = salesTotal.getText().toString();
            String purchaserName1 = purchaserName.getText().toString();
            String purchaserEmail1 = purchaserEmail.getText().toString();
            String  purchaserPhone1 = purchaserPhone.getText().toString();
            String salesDate1 = salesDate.getText().toString();
            String shipTo1 = shipTo.getText().toString();
            String comments1 = comments.getText().toString();

            String companyName1 = companyName.getSelectedItem().toString();
            String condition1 = condition.getSelectedItem().toString();
            String partNumber1 = partNumber.getSelectedItem().toString();
            String status1 = status.getSelectedItem().toString();
            String terms1 = terms.getSelectedItem().toString();


            SO so = new SO();

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


            } else if (TextUtils.isEmpty(price1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the price", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(quantity1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the quantity", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(purchaseTotal1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the purchase total", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(salesDate1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the order date", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(shipTo1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the ship to", Toast.LENGTH_SHORT).show();

            }

            else {

                    if (TextUtils.isEmpty(creditCardFee1)) {

                        creditCardFee1 =("$0.0");
                    }
                    if (TextUtils.isEmpty(wireTransferFee1)) {

                        wireTransferFee1 =("$0.0");
                    }
                    if (TextUtils.isEmpty(dgDocFee1)) {

                        dgDocFee1= ("$0.0");
                    }
                    if (TextUtils.isEmpty(packingFee1)) {

                        packingFee1 =("$0.0");
                    }
                    if (TextUtils.isEmpty(shippingCost1)) {

                        shippingCost1= ("$0.0");
                    }

                    if (TextUtils.isEmpty(purchaserName1)) {

                        purchaserName1= ("not filled");
                    }

                    if (TextUtils.isEmpty(purchaserEmail1)) {

                        purchaserEmail1= ("not filled");
                    }

                    if (TextUtils.isEmpty(purchaserPhone1)) {

                        purchaserPhone1= ("not filled");
                    }

                    if (TextUtils.isEmpty(comments1)) {

                        comments1= ("none");
                    }


                    DatabaseReference so1 = mDatabase.push();
                so.setSoNum(so1.getKey());

                so.setCompanyName(companyName1);
                so.setPartNumber(partNumber1);
                so.setSeriallNumber(seriallNumber1);
                so.setDescription(description1);
                so.setCondition(condition1);
                so.setTrace(trace1);
                so.setTagBy(tagBy1);
                so.setTagDate(tagDate1);
                so.setPrice(price1);
                so.setQuantity(quantity1);
                so.setTerms(terms1);
                so.setCreditCardFee(creditCardFee1);
                so.setWireTransferFee(wireTransferFee1);
                so.setDgDocFee(dgDocFee1);
                so.setPackingFee(packingFee1);
                so.setShippingCost(shippingCost1);
                so.setPurchaseTotal(purchaseTotal1);
                so.setPurchaserName(purchaserName1);
                so.setPurchaserEmail(purchaserEmail1);
                so.setPurchaserPhone(purchaserPhone1);
                so.setSalesDate(salesDate1);
                so.setStatus(status1);
                so.setShipTo(shipTo1);
                so.setComments(comments1);


                so1.setValue(so);


                Toast.makeText(getApplicationContext(), "SO Added Successfully", Toast.LENGTH_SHORT).show();

                seriallNumber.setText("");
                description.setText("");
                trace.setText("");
                tagBy.setText("");
                tagDate.setText("");
                price.setText("");
                quantity.setText("");
                creditCardFee.setText("");
                wireTransferFee.setText("");
                dgDocFee.setText("");
                packingFee.setText("");
                shippingCost.setText("");
                salesTotal.setText("");
                purchaserName.setText("");
                purchaserEmail.setText("");
                purchaserPhone.setText("");
                salesDate.setText("");
                shipTo.setText("");
                comments.setText("");

                companyName.setSelection(0);
                condition.setSelection(0);
                partNumber.setSelection(0);
                status.setSelection(0);
                terms.setSelection(0);

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

                            salesDate.setText(dayOfMonth1 + "-" + (monthOfYear1 + 1) + "-" + year1);

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

                    SO so = dataSnapshot.getValue(SO.class);

                    String seriallNumber1 = so.getSeriallNumber();
                    String description1 = so.getDescription();
                    String trace1 = so.getTrace();
                    String tagBy1 = so.getTagBy();
                    String tagDate1 = so.getTagDate();
                    String price1 = so.getPrice();
                    String quantity1 = so.getQuantity();
                    String creditCardFee1 = so.getCreditCardFee();
                    String wireTransferFee1 = so.getWireTransferFee();
                    String dgDocFee1 = so.getDgDocFee();
                    String packingFee1 = so.getPackingFee();
                    String shippingCost1 = so.getShippingCost();
                    String purchaseTotal1 = so.getPurchaseTotal();
                    String purchaserName1 = so.getPurchaserName();
                    String purchaserEmail1 = so.getPurchaserEmail();
                    String purchaserPhone1 = so.getPurchaserPhone();
                    String salesDate1 = so.getSalesDate();
                    String shipTo1 = so.getShipTo();
                    String comments1 = so.getComments();

                    String companyName1 = so.getCompanyName();
                    String condition1 = so.getCondition();
                    String partNumber1 = so.getPartNumber();
                    String status1 = so.getStatus();
                    String terms1 = so.getTerms();

                    String emailBody = "Company Name: " + companyName1 + "\n" +
                            "Part Number: " + partNumber1 + "\n" + "Serial Number: " + seriallNumber1 + "\n" +
                            "Description: " + description1 + "\n" + "Condition: " + condition1 + "\n" +
                            "Trace: " + trace1 + "\n" + "Tag By: " + tagBy1 + "\n" +
                            "Tag Date: " + tagDate1 + "\n" + "Price: " + price1 + "\n" +
                            "Quantity: " + quantity1 + "\n" + "Terms: " + terms1 + "\n" +
                            "Credit Card Fee: " + creditCardFee1 + "\n" + "Wire Transfer Fee: " + wireTransferFee1 + "\n" +
                            "DG Documents Fee: " + dgDocFee1 + "\n" + "Packing Fee: " + packingFee1 + "\n" +
                            "Shipping Cost: " + shippingCost1 + "\n" + "Sales Total: " + purchaseTotal1 + "\n" +
                            "Purchaser Name: " + purchaserName1 + "\n" + "Purchaser Email: " + purchaserEmail1 + "\n" +
                            "Purchaser Phone: " + purchaserPhone1 + "\n" + "Sales Date: " + salesDate1 + "\n" +
                            "Status: " + status1 + "\n" + "Ship To: " + shipTo1 + "\n" +
                            "Comments: " + comments1;

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{purchaserEmail1});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sales Order");
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
