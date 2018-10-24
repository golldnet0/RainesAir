package com.example.ariad.rainesair.jc_payments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariad.rainesair.LoggedInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.ariad.rainesair.R;
import org.json.JSONException;
import org.json.JSONObject;


public class ConfirmationActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    private FirebaseUser user;
    String uid;

    ImageView back;
    TextView textViewId, textViewStatus, textViewAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        //Getting Intent
        Intent intent = getIntent();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            uid = user.getUid();
        }


        mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("Payments");
        back = (ImageView)findViewById(R.id.payment_confirmation_back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(getApplicationContext(), LoggedInActivity.class);
                startActivity(intent);
            }
        });


        try {
            JSONObject jsonDetails = new JSONObject(intent.getStringExtra("PaymentDetails"));

            //Displaying payment details
            showDetails(jsonDetails.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));
        } catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //TODO
        //PLEASE CHECK WHY THE CONFIRMATION IS CRASHING WHEN WRITING TO THE DB

//        Payment payment = new Payment();
//
//        String id = textViewId.getText().toString();
//        String status = textViewStatus.getText().toString();
//        String amount = textViewAmount.getText().toString();
//
//        payment.setId(id);
//        payment.setAmount(amount);
//        payment.setStatus(status);
//
//        DatabaseReference payment1 = mDatabase.push();
//        payment1.setValue(payment);
    }

    private void showDetails(JSONObject jsonDetails, String paymentAmount) throws JSONException {
        //Views
        textViewId = (TextView) findViewById(R.id.paymentId);
        textViewStatus = (TextView) findViewById(R.id.paymentStatus);
        textViewAmount = (TextView) findViewById(R.id.paymentAmount);

        //Showing the details from json object
        textViewId.setText(jsonDetails.getString("id"));
        textViewStatus.setText(jsonDetails.getString("state"));
        textViewAmount.setText(paymentAmount + " USD");


            }

    }
