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

public class POAddActivity extends AppCompatActivity
        implements View.OnClickListener{

    Intent intent;
    EditText seriallNumber, description, trace, tagBy, tagDate, price,
            quantity, creditCardFee, wireTransferFee, dgDocFee, packingFee, shippingCost,
            purchaseTotal, vendorName, vendorEmail, vendorPhone, orderDate, shipTo, comments;

    Spinner companyName, partNumber, condition, status, terms;
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
        setContentView(R.layout.activity_poadd);


        try {

            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            seriallNumber = (EditText) findViewById(R.id.po_serialnumber);
            description = (EditText) findViewById(R.id.po_description);
            trace = (EditText) findViewById(R.id.po_trace);
            tagBy = (EditText) findViewById(R.id.po_tagby);
            tagDate = (EditText) findViewById(R.id.po_tagdate);
            price = (EditText) findViewById(R.id.po_price);
            quantity = (EditText) findViewById(R.id.po_quantity);
            creditCardFee = (EditText) findViewById(R.id.po_creditcardfee);
            wireTransferFee = (EditText) findViewById(R.id.po_wiretransferfee);
            dgDocFee = (EditText) findViewById(R.id.po_dgdocuments);
            packingFee = (EditText) findViewById(R.id.po_packing);
            shippingCost = (EditText) findViewById(R.id.po_shippingcost);
            purchaseTotal = (EditText) findViewById(R.id.po_purchasetotal);
            vendorName = (EditText) findViewById(R.id.po_vendorname);
            vendorEmail = (EditText) findViewById(R.id.po_vendoremail);
            vendorPhone = (EditText) findViewById(R.id.po_vendorphone);
            orderDate = (EditText) findViewById(R.id.po_orderdate);
            shipTo = (EditText) findViewById(R.id.po_shipto);
            comments = (EditText) findViewById(R.id.po_comments);

            save = (Button) findViewById(R.id.po_save);
            cancel = (Button) findViewById(R.id.po_cancel);
            back = (ImageView) findViewById(R.id.poAdd_back);
            send = (Button) findViewById(R.id.po_send);

            btnDatePicker = (Button) findViewById(R.id.po_selectdate);
            btnDatePicker.setOnClickListener(this);
            btnDatePicker1 = (Button) findViewById(R.id.po_selectorderdate);
            btnDatePicker1.setOnClickListener(this);


            companyName = (Spinner) findViewById(R.id.po_companyname);
            condition = (Spinner) findViewById(R.id.po_condition);
            partNumber = (Spinner) findViewById(R.id.po_partnumber);
            status = (Spinner) findViewById(R.id.po_status);
            terms = (Spinner) findViewById(R.id.po_terms);


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(getApplicationContext(), PartsActivity.class);
                    startActivity(intent);
                }
            });


            if (user != null) {
               uid = user.getUid();

                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("PO");
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


            purchaseTotal.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    purchaseTotal.setFocusable(true);
                    purchaseTotal.setFocusableInTouchMode(true);

                    return false;
                }
            });


            vendorName.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    vendorName.setFocusable(true);
                    vendorName.setFocusableInTouchMode(true);

                    return false;
                }
            });


            vendorEmail.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    vendorEmail.setFocusable(true);
                    vendorEmail.setFocusableInTouchMode(true);

                    return false;
                }
            });

            vendorPhone.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    vendorPhone.setFocusable(true);
                    vendorPhone.setFocusableInTouchMode(true);

                    return false;
                }
            });

            orderDate.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    orderDate.setFocusable(true);
                    orderDate.setFocusableInTouchMode(true);

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

                    addPO();
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
                    purchaseTotal.setText("");
                    vendorName.setText("");
                    vendorEmail.setText("");
                    vendorPhone.setText("");
                    orderDate.setText("");
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

                   intent = new Intent(getApplicationContext(), POActivity.class);
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


    public void addPO() {

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
            String  purchaseTotal1 = purchaseTotal.getText().toString();
            String vendorName1 = vendorName.getText().toString();
            String vendorEmail1 = vendorEmail.getText().toString();
            String  vendorPhone1 = vendorPhone.getText().toString();
            String orderDate1 = orderDate.getText().toString();
            String shipTo1 = shipTo.getText().toString();
            String comments1 = comments.getText().toString();

            String companyName1 = companyName.getSelectedItem().toString();
            String condition1 = condition.getSelectedItem().toString();
            String partNumber1 = partNumber.getSelectedItem().toString();
            String status1 = status.getSelectedItem().toString();
            String terms1 = terms.getSelectedItem().toString();


           PO po = new PO();

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

            } else if (TextUtils.isEmpty(orderDate1)) {

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

                if (TextUtils.isEmpty(vendorName1)) {

                    vendorName1= ("not filled");
                }

                if (TextUtils.isEmpty(vendorEmail1)) {

                    vendorEmail1= ("not filled");
                }

                if (TextUtils.isEmpty(vendorPhone1)) {

                    vendorPhone1= ("not filled");
                }

                if (TextUtils.isEmpty(comments1)) {

                    comments1= ("none");
                }


                DatabaseReference po1 = mDatabase.push();
                po.setPoNum(po1.getKey());

                po.setCompanyName(companyName1);
                po.setPartNumber(partNumber1);
                po.setSeriallNumber(seriallNumber1);
                po.setDescription(description1);
                po.setCondition(condition1);
                po.setTrace(trace1);
                po.setTagBy(tagBy1);
                po.setTagDate(tagDate1);
                po.setPrice(price1);
                po.setQuantity(quantity1);
                po.setTerms(terms1);
                po.setCreditCardFee(creditCardFee1);
                po.setWireTransferFee(wireTransferFee1);
                po.setDgDocFee(dgDocFee1);
                po.setPackingFee(packingFee1);
                po.setShippingCost(shippingCost1);
                po.setPurchaseTotal(purchaseTotal1);
                po.setVendorName(vendorName1);
                po.setVendorEmail(vendorEmail1);
                po.setVendorPhone(vendorPhone1);
                po.setOrderDate(orderDate1);
                po.setStatus(status1);
                po.setShipTo(shipTo1);
                po.setComments(comments1);

                po1.setValue(po);

                Toast.makeText(getApplicationContext(), "PO Added Successfully", Toast.LENGTH_SHORT).show();

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
                purchaseTotal.setText("");
                vendorName.setText("");
                vendorEmail.setText("");
                vendorPhone.setText("");
                orderDate.setText("");
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

                            orderDate.setText(dayOfMonth1 + "-" + (monthOfYear1 + 1) + "-" + year1);

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

                    PO po = dataSnapshot.getValue(PO.class);

                    String seriallNumber1 = po.getSeriallNumber();
                    String description1 = po.getDescription();
                    String trace1 = po.getTrace();
                    String tagBy1 = po.getTagBy();
                    String tagDate1 = po.getTagDate();
                    String price1 = po.getPrice();
                    String quantity1 = po.getQuantity();
                    String creditCardFee1 = po.getCreditCardFee();
                    String wireTransferFee1 = po.getWireTransferFee();
                    String dgDocFee1 = po.getDgDocFee();
                    String packingFee1 = po.getPackingFee();
                    String shippingCost1 = po.getShippingCost();
                    String purchaseTotal1 = po.getPurchaseTotal();
                    String vendorName1 = po.getVendorName();
                    String vendorEmail1 = po.getVendorEmail();
                    String vendorPhone1 = po.getVendorPhone();
                    String orderDate1 = po.getOrderDate();
                    String shipTo1 = po.getShipTo();
                    String comments1 = po.getComments();

                    String companyName1 = po.getCompanyName();
                    String condition1 = po.getCondition();
                    String partNumber1 = po.getPartNumber();
                    String status1 = po.getStatus();
                    String terms1 = po.getTerms();

                    String emailBody = "Company Name: " + companyName1 + "\n" +
                            "Part Number: " + partNumber1 + "\n" + "Serial Number: " + seriallNumber1 + "\n" +
                            "Description: " + description1 + "\n" + "Condition: " + condition1 + "\n" +
                            "Trace: " + trace1 + "\n" + "Tag By: " + tagBy1 + "\n" +
                            "Tag Date: " + tagDate1 + "\n" + "Price: " + price1 + "\n" +
                            "Quantity: " + quantity1 + "\n" + "Terms: " + terms1 + "\n" +
                            "Credit Card Fee: " + creditCardFee1 + "\n" + "Wire Transfer Fee: " + wireTransferFee1 + "\n" +
                            "DG Documents Fee: " + dgDocFee1 + "\n" + "Packing Fee: " + packingFee1 + "\n" +
                            "Shipping Cost: " + shippingCost1 + "\n" + "Purchase Total: " + purchaseTotal1 + "\n" +
                            "Vendor Name: " + vendorName1 + "\n" + "Vendor Email: " + vendorEmail1 + "\n" +
                            "Vendor Phone: " + vendorPhone1 + "\n" + "Order Date: " + orderDate1 + "\n" +
                            "Status: " + status1 + "\n" + "Ship To: " + shipTo1 + "\n" +
                            "Comments: " + comments1;

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{vendorEmail1});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Purchase Order");
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
