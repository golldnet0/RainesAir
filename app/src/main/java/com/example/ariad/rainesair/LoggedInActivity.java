package com.example.ariad.rainesair;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


import com.example.ariad.rainesair.aaron.ComposeMessageActivity;
import com.example.ariad.rainesair.aaron.Message;
import com.example.ariad.rainesair.aaron.RatingActivity;
import com.example.ariad.rainesair.aaron.ViewMessagesActivity;
import com.example.ariad.rainesair.aaron.ViewPartActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class LoggedInActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    Intent intent;
    ImageView pic;
    TextView welcome;
    String uid, profilePic;
    ListView partsList;
    List<String> arrayListParts = new ArrayList<>();
    List<Parts> objPartsList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    String userFirst, userLast, userEmail, userPhone, publisherId;
    private DatabaseReference mDatabase, mRef;
    StorageReference storage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logged_in, menu);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("PublishedParts");

        welcome = (TextView) findViewById(R.id.welcomeLoggedIn);
        pic = (ImageView) findViewById(R.id.imageViewLoggedIn);

        partsList = (ListView) findViewById(R.id.home_list);

        adapter = new ArrayAdapter<String>(this, android.R.layout.preference_category, arrayListParts);
        partsList.setAdapter(adapter);

        if (user != null) {
            String email = user.getEmail();
            uid = user.getUid();

            welcome.setText("Welcome" + " " + email);


            mDatabase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    Parts part = dataSnapshot.getValue(Parts.class);

                    objPartsList.add(part);//AARON BROWNE


                    final String partName = part.getPartNumber();
                    final String partSerial = part.getSeriallNumber();
                    final String description = part.getDescription();
                    final String condition = part.getCondition();
                    final String trace = part.getTrace();
                    final String tagBy = part.getTagBy();
                    final String tagDate = part.getTagDate();
                    publisherId = part.getPublisherId();


                     /*
                    userFirst = part.getPublisherFirst();
                    userLast = part.getPublisherLast();
                    userPhone = part.getPublisherCell();
                    userEmail = part.getPublisherEmail();
                    */

                    //Trying to get the average rating from the User. -- AARON BROWNE
                    DatabaseReference mUserRatingData = FirebaseDatabase.getInstance().getReference().child(publisherId).child("PersonalInfo");

                    mUserRatingData.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Users user = dataSnapshot.getValue(Users.class);
                            double rating = user.getRating();
                            userFirst = user.getFirstName();
                            userLast = user.getLastName();
                            userPhone = user.getCell();
                            userEmail = user.getEmail();


                            if (publisherId.equals(uid)) {

                                publisherId = "Myself";

                                arrayListParts.add("\n" + "Part Number:  " +  partName + "\n" + "Serial Number:  " + partSerial + "\n" +
                                        "Description:  " + description +  "\n" + "Condition:  " + condition  + "\n" + "Trace:  " + trace +  "\n" +
                                        "Tag By:  " +  tagBy + "\n" + "Tag Date:  " + tagDate + "\n" + "Publisher:  "
                                        + " " + publisherId + "\n" + "User Rating: "  + rating + "\n" );

                            }

                            else{

                                arrayListParts.add("\n" + "Part Number:  " +  partName + "\n" + "Serial Number:  " + partSerial + "\n" +
                                        "Description:  " + description +  "\n" + "Condition:  " + condition  + "\n" + "Trace:  " + trace +  "\n" +
                                        "Tag By:  " +  tagBy + "\n" + "Tag Date:  " + tagDate + "\n" + "Publisher:  "
                                        + " " + userFirst + " " + userLast + "\n" + "Publisher Cell:" + " " + userPhone +
                                        "\n" + "Publisher Email:" + " " + userEmail + "\n" + "User Rating: " + rating + "\n");

                            }




                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



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

            mRef = FirebaseDatabase.getInstance().getReference().child(uid).child("PersonalInfo");

           storage = FirebaseStorage.getInstance().getReference().child(uid);

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                   Users user = dataSnapshot.getValue(Users.class);

                    String photo = user.getProfilePic();


                    if (photo.equals("defaultprofile")) {

                        StorageReference defaultStorage = FirebaseStorage.getInstance().getReference().child(photo);

                        defaultStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Picasso.with(getApplicationContext()).load(uri.toString()).into(pic);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                        profilePic = "defaultprofile";
                    } else {
                        StorageReference getReference = storage.child(photo);

                        getReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Picasso.with(getApplicationContext()).load(uri.toString()).into(pic);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                        profilePic = photo;

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            partsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {

                    final Dialog dialog = new Dialog(LoggedInActivity.this);
                    dialog.setContentView(R.layout.activity_published_parts_menu);
                    dialog.setTitle("Actions");

                    final Button btnCall = (Button) dialog.findViewById(R.id.published_parts_menu_call);

                    btnCall.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            String publisherPhone = objPartsList.get(position).getPublisherCell();

                            Uri call = Uri.parse("tel:" + publisherPhone);
                            Intent callIntent = new Intent(Intent.ACTION_DIAL, call);
                            startActivity(callIntent);
                        }

                    });

                    final Button btnMessage = (Button) dialog.findViewById(R.id.published_parts_menu_message);

                    btnMessage.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            //CREATE MESSAGE
                            Message createdMessage = new Message();

                            String selectedPublishID = objPartsList.get(position).getPublisherId();
                            Log.d(">", selectedPublishID);

                            createdMessage.setSenderUserID(selectedPublishID);

                            createdMessage.setSubject(" ");

                            String publisherName = objPartsList.get(position).getPublisherFirst() + " " + objPartsList.get(position).getPublisherLast();

                            createdMessage.setSenderName(publisherName);

                            Intent messageIntent = new Intent(getApplicationContext(), ComposeMessageActivity.class);
                            messageIntent.putExtra("selectedMessage", createdMessage);
                            startActivity(messageIntent);
                        }


                    });

                    final Button btnEmail = (Button) dialog.findViewById(R.id.published_parts_menu_email);

                    btnEmail.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            String publisherEmail = objPartsList.get(position).getPublisherEmail();

                            Intent emailIntent = new Intent(Intent.ACTION_SEND);
                            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{publisherEmail});
                            emailIntent.setType("message/rfc822");
                            startActivity(Intent.createChooser(emailIntent, "Choose email client..."));

                        }

                    });

                    final Button btnPO = (Button) dialog.findViewById(R.id.published_parts_purchase_order);


                    btnPO.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            Intent poIntent = new Intent(LoggedInActivity.this, POActivity.class);
                            startActivity(poIntent);

                        }


                    });

                    final Button btnRatings = (Button) dialog.findViewById(R.id.published_parts_purchase_star);


                    btnRatings.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            String selectedUserID = objPartsList.get(position).getPublisherId();
                            String publisherFullName = objPartsList.get(position).getPublisherFirst() + " " + objPartsList.get(position).getPublisherLast();

                            Intent rateIntent = new Intent(getApplicationContext(), RatingActivity.class);
                            rateIntent.putExtra("selectedUserID", selectedUserID);
                            rateIntent.putExtra("publisherFullName", publisherFullName);
                            startActivity(rateIntent);
                        }

                    });

                    final Button btnView = (Button)dialog.findViewById(R.id.published_parts_view);

                    btnView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent viewIntent = new Intent(getApplicationContext(), ViewPartActivity.class);

                            String selectedPublisherID = objPartsList.get(position).getPublisherId();

                            viewIntent.putExtra("selectedPublisherID", selectedPublisherID);
                            startActivity(viewIntent);
                        }
                    });

                    dialog.dismiss();

                    dialog.show();
                }


            });
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            FirebaseAuth.getInstance().signOut();

            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

            intent = new Intent(getApplicationContext(), LoggedInActivity.class);
            startActivity(intent);


        } else if (id == R.id.po) {

            intent = new Intent(getApplicationContext(), POActivity.class);
            startActivity(intent);


        } else if (id == R.id.so) {

            intent = new Intent(getApplicationContext(), SOActivity.class);
            startActivity(intent);


        } else if (id == R.id.ro) {

            intent = new Intent(getApplicationContext(), ROActivity.class);
            startActivity(intent);


        } else if (id == R.id.parts) {

            intent = new Intent(getApplicationContext(), PartsActivity.class);
            startActivity(intent);


        } else if (id == R.id.inventory) {

            intent = new Intent(getApplicationContext(), InventoryActivity.class);
            startActivity(intent);


        } else if (id == R.id.customers) {

            intent = new Intent(getApplicationContext(), CustomersActivity.class);
            startActivity(intent);


        } else if (id == R.id.info) {

            intent = new Intent(getApplicationContext(), PersonalInfoActivity.class);
            startActivity(intent);


        } else if (id == R.id.message) {

            intent = new Intent(getApplicationContext(), ViewMessagesActivity.class);
            startActivity(intent);


        } else if (id == R.id.settings) {

            intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}