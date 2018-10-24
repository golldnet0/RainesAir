package com.example.ariad.rainesair.aaron;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariad.rainesair.R;
import com.example.ariad.rainesair.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {
    private String userFullName;
    private String userToBeRatedStr;
    private Rating aRating;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUser;
    private DatabaseReference mDatabaseRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Intent intent = getIntent();
        //get information from previous activity
        userFullName = intent.getStringExtra("publisherFullName");
        userToBeRatedStr = intent.getStringExtra("selectedUserID");

        TextView ratingPrompt = (TextView)findViewById(R.id.ratingPrompt);

        ratingPrompt.setText("How would you like to rate " + userFullName + "?");

    }


    public void rateUser(View view){
        //create rating object
        aRating = new Rating();

        mAuth = FirebaseAuth.getInstance();
        String currentUID = mAuth.getCurrentUser().getUid();
        aRating.setUserID(currentUID);

        Spinner spinnerRating = (Spinner)findViewById(R.id.spinnerRating);
        int ratingNum = Integer.parseInt(spinnerRating.getSelectedItem().toString());
        aRating.setRatingNumber(ratingNum);

        //get current user name
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child(currentUID).child("PersonalInfo");
        mDatabaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(">", "IN DATABASEUSER LISTENER");
                Users user = dataSnapshot.getValue(Users.class);
                final String currentUserName = user.getFirstName() + " " + user.getLastName();

                aRating.setUserName(currentUserName);

                //Submit rating to database
                mDatabaseRating = FirebaseDatabase.getInstance().getReference().child(userToBeRatedStr).child("Ratings");
                mDatabaseRating.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.v(">", "IN DATABASERATING LISTENER");
                        Toast.makeText(RatingActivity.this, "Rating for " + userFullName + " sent.", Toast.LENGTH_SHORT).show();
                        averageRatings(userToBeRatedStr);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                mDatabaseRating.push().setValue(aRating);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    //create average of rating and store it inot the database
    private void averageRatings (String userID){
        Log.v(">", "IN AVERAGERATINGS FUNCTION");
        final ArrayList<Rating> ratingList = new ArrayList<Rating>();

        final DatabaseReference mRefRating = FirebaseDatabase.getInstance().getReference().child(userID).child("Ratings");
        final DatabaseReference mRefUser = FirebaseDatabase.getInstance().getReference().child(userID).child("PersonalInfo");

        //get average rating
        final ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.v(">", "IN MREFRATING LISTENER");
                Rating rating = dataSnapshot.getValue(Rating.class);
                ratingList.add(rating);



                //add rating to database
                //this only executes once all of the ratings (children) are collected in the ratinglist
                if (ratingList.size() == dataSnapshot.getChildrenCount()) {
                    mRefUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.v(">", "IN MREFUSER LISTENER");

                            //find average
                            int totalScore = 0;
                            for (Rating oneRating : ratingList) {
                                Log.v(">", String.valueOf(oneRating.getRatingNumber()));
                                totalScore += oneRating.getRatingNumber();
                            }

                            //format average to 2 decimal places
                            NumberFormat nf = NumberFormat.getInstance();
                            nf.setMaximumFractionDigits(2);

                            double averageRating = ((double)totalScore) / ratingList.size();
                            averageRating = Double.parseDouble(nf.format(averageRating));


                            Users user = dataSnapshot.getValue(Users.class);

                            user.setRating(averageRating);
                            mRefUser.setValue(user);
                            Toast.makeText(getApplicationContext(), "Average rating is now " + averageRating, Toast.LENGTH_LONG).show();


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.v(">", "IN CHANGED LISTENER");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.v(">", "IN REMOVED LISTENER");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.v(">", "IN MOVED LISTENER");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v(">", "IN CANCELLED LISTENER");
            }
        };
        mRefRating.addChildEventListener(childEventListener);
        //mRefRating.orderByKey();
    }
}