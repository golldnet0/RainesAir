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
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Calendar;
import java.util.Map;

public class SOViewDetailsActivity extends AppCompatActivity
        implements  View.OnClickListener {


    Intent intent;
    EditText seriallNumber, description, trace, tagBy, tagDate, price,
            quantity, creditCardFee, wireTransferFee, dgDocFee, packingFee, shippingCost,
            purchaseTotal, purchaserName, purchaserEmail, purchaserPhone, salesDate, shipTo, comments;
    TextView companyName, partNumber;

    Spinner condition, status, terms;
    String uid, selectedSO;
    Button save, btnDatePicker, btnDatePicker1, send;
    ImageView back;
    String cell, email;
    private int mYear, mMonth, mDay, year, month, day;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soview_details);

        try {

            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            intent = getIntent();
            selectedSO = intent.getStringExtra("soID");


            seriallNumber = (EditText) findViewById(R.id.soDetails_serialnumber);
            description = (EditText) findViewById(R.id.soDetails_description);
            trace = (EditText) findViewById(R.id.soDetails_trace);
            tagBy = (EditText) findViewById(R.id.soDetails_tagby);
            tagDate = (EditText) findViewById(R.id.soDetails_tagdate);
            price = (EditText) findViewById(R.id.soDetails_price);
            quantity = (EditText) findViewById(R.id.soDetails_quantity);
            creditCardFee = (EditText) findViewById(R.id.soDetails_creditcardfee);
            wireTransferFee = (EditText) findViewById(R.id.soDetails_wiretransferfee);
            dgDocFee = (EditText) findViewById(R.id.soDetails_dgdocuments);
            packingFee = (EditText) findViewById(R.id.soDetails_packing);
            shippingCost = (EditText) findViewById(R.id.soDetails_shippingcost);
            purchaseTotal = (EditText) findViewById(R.id.soDetails_salestotal);
            purchaserName = (EditText) findViewById(R.id.soDetails_contactname);
            purchaserEmail = (EditText) findViewById(R.id.soDetails_contactemail);
            purchaserPhone = (EditText) findViewById(R.id.soDetails_contactphone);
            salesDate = (EditText) findViewById(R.id.soDetails_saledate);
            shipTo = (EditText) findViewById(R.id.soDetails_shipto);
            comments = (EditText) findViewById(R.id.soDetails_comments);

            save = (Button) findViewById(R.id.soDetails_update);
            back = (ImageView) findViewById(R.id.soDetailsAdd_back);
            send = (Button) findViewById(R.id.soDetails_send);

            btnDatePicker = (Button) findViewById(R.id.soDetails_selectdate);
            btnDatePicker.setOnClickListener(this);
            btnDatePicker1 = (Button) findViewById(R.id.soDetails_selectsaledate);
            btnDatePicker1.setOnClickListener(this);


            companyName = (TextView) findViewById(R.id.soDetails_companyname);
            condition = (Spinner) findViewById(R.id.soDetails_condition);
            partNumber = (TextView) findViewById(R.id.soDetails_partnumber);
            status = (Spinner) findViewById(R.id.soDetails_status);
            terms = (Spinner) findViewById(R.id.soDetails_terms);


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(getApplicationContext(), SOActivity.class);
                    startActivity(intent);
                }
            });


            if (user != null) {
                uid = user.getUid();

                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("SO").child(selectedSO);

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


            purchaseTotal.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    purchaseTotal.setFocusable(true);
                    purchaseTotal.setFocusableInTouchMode(true);

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

                    updateSO();
                }

            });

            back.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    intent = new Intent(getApplicationContext(), SOActivity.class);
                    startActivity(intent);
                }

            });

            send.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    sendEmail();
                }

            });


            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.hasChildren() == true )
                    {
                        int indexCondition = 0,indexStatus = 0, indexTerms = 0;
                        SO so = dataSnapshot.getValue(SO.class);

                        String  seriallNumber1 = so.getSeriallNumber();
                        String  description1 = so.getDescription();
                        String trace1 = so.getTrace();
                        String  tagBy1 = so.getTagBy();
                        String tagDate1 = so.getTagDate();
                        String  price1 = so.getPrice();
                        String  quantity1 = so.getQuantity();
                        String creditCardFee1 = so.getCreditCardFee();
                        String  wireTransferFee1 = so.getWireTransferFee();
                        String dgDocFee1 = so.getDgDocFee();
                        String  packingFee1 = so.getPackingFee();
                        String  shippingCost1 = so.getShippingCost();
                        String  purchaseTotal1 = so.getPurchaseTotal();
                        String vendorName1 = so.getPurchaserName();
                        String vendorEmail1 = so.getPurchaserEmail();
                        String  vendorPhone1 = so.getPurchaserPhone();
                        String orderDate1 = so.getSalesDate();
                        String shipTo1 = so.getShipTo();
                        String comments1 = so.getComments();

                        String companyName1 = so.getCompanyName();
                        String condition1 = so.getCondition();
                        String partNumber1 = so.getPartNumber();
                        String status1 = so.getStatus();
                        String terms1 = so.getTerms();

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

                        seriallNumber.setText(seriallNumber1);
                        description.setText(description1);
                        trace.setText(trace1);
                        tagBy.setText(tagBy1);
                        tagDate.setText(tagDate1);
                        price.setText(price1);
                        quantity.setText(quantity1);
                        creditCardFee.setText(creditCardFee1);
                        wireTransferFee.setText(wireTransferFee1);
                        dgDocFee.setText(dgDocFee1);
                        packingFee.setText(packingFee1);
                        shippingCost.setText(shippingCost1);
                        purchaseTotal.setText(purchaseTotal1);
                        purchaserName.setText(vendorName1);
                        purchaserEmail.setText(vendorEmail1);
                        purchaserPhone.setText(vendorPhone1);
                        salesDate.setText(orderDate1);
                        shipTo.setText(shipTo1);
                        comments.setText(comments1);

                        companyName.setText(companyName1);
                        condition.setSelection(indexCondition);
                        partNumber.setText(partNumber1);
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


    public void updateSO() {

        try {


            String seriallNumber1 = seriallNumber.getText().toString();
            String description1 = description.getText().toString();
            String trace1 = trace.getText().toString();
            String tagBy1 = tagBy.getText().toString();
            String tagDate1 = tagDate.getText().toString();
            String price1 = price.getText().toString();
            String quantity1 = quantity.getText().toString();
            String creditCardFee1 = creditCardFee.getText().toString();
            String wireTransferFee1 = wireTransferFee.getText().toString();
            String dgDocFee1 = dgDocFee.getText().toString();
            String packingFee1 = packingFee.getText().toString();
            String shippingCost1 = shippingCost.getText().toString();
            String purchaseTotal1 = purchaseTotal.getText().toString();
            String purchaserName1 = purchaserName.getText().toString();
            String purchaserEmail1 = purchaserEmail.getText().toString();
            String purchaserPhone1 = purchaserPhone.getText().toString();
            String salesDate1 = salesDate.getText().toString();
            String shipTo1 = shipTo.getText().toString();
            String comments1 = comments.getText().toString();

            String companyName1 = companyName.getText().toString();
            String condition1 = condition.getSelectedItem().toString();
            String partNumber1 = partNumber.getText().toString();
            String status1 = status.getSelectedItem().toString();
            String terms1 = terms.getSelectedItem().toString();


            if (TextUtils.isEmpty(seriallNumber1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the serial number", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(description1)) {

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

                Toast.makeText(getApplicationContext(), "Please fill in the sale total", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(salesDate1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the sale date", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(shipTo1)) {

                Toast.makeText(getApplicationContext(), "Please fill in the ship to", Toast.LENGTH_SHORT).show();

            } else {


                if (TextUtils.isEmpty(creditCardFee1)) {

                    creditCardFee1 = ("$0.0");
                }
                if (TextUtils.isEmpty(wireTransferFee1)) {

                    wireTransferFee1 = ("$0.0");
                }
                if (TextUtils.isEmpty(dgDocFee1)) {

                    dgDocFee1 = ("$0.0");
                }
                if (TextUtils.isEmpty(packingFee1)) {

                    packingFee1 = ("$0.0");
                }
                if (TextUtils.isEmpty(shippingCost1)) {

                    shippingCost1 = ("$0.0");
                }

                if (TextUtils.isEmpty(purchaserName1)) {

                    purchaserName1 = ("not filled");
                }

                if (TextUtils.isEmpty(purchaserEmail1)) {

                    purchaserEmail1 = ("not filled");
                }

                if (TextUtils.isEmpty(purchaserPhone1)) {

                    purchaserPhone1 = ("not filled");
                }

                if (TextUtils.isEmpty(comments1)) {

                    comments1 = ("none");
                }



                    SO soUpdate = new SO(comments1, companyName1, condition1, creditCardFee1, description1,
                            dgDocFee1, packingFee1, partNumber1, price1, purchaserEmail1,
                            purchaserName1, purchaserPhone1, purchaseTotal1, quantity1, salesDate1,
                            seriallNumber1, shippingCost1, shipTo1, " ", status1, tagBy1, tagDate1, terms1,
                            trace1, wireTransferFee1);


                    Map<String, Object> poValues = soUpdate.toMap();


                    mDatabase.updateChildren(poValues);

                    Toast.makeText(getApplicationContext(), "SO updated successfully", Toast.LENGTH_LONG).show();
                }

        } catch(SQLException ex){
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
