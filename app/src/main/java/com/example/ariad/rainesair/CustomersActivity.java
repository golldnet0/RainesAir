package com.example.ariad.rainesair;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ariad.rainesair.aaron.ComposeMessageActivity;
import com.example.ariad.rainesair.aaron.Message;
import com.example.ariad.rainesair.aaron.ViewMessagesActivity;
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


public class CustomersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Intent intent;
    ImageView pic;
    TextView welcome;
    SearchView searchView;
    Button addCustomer;
    String uid, customerId, customerName, profilePic;
    ListView customerList;
    List<String> arrayListCustomer = new ArrayList<>();
    List<String> arrayListCustomerID = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    private ProgressDialog mProgressDialog;
    private DatabaseReference mDatabase, mRef;
    private FirebaseUser user;
    private String customerIDSel = " ";
    StorageReference storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customers, menu);

        try {

        searchView = (SearchView) findViewById(R.id.customer_search);
        user = FirebaseAuth.getInstance().getCurrentUser();
        customerList = (ListView) findViewById(R.id.customer_list);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListCustomer);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListCustomerID);

        customerList.setAdapter(adapter);
        mProgressDialog = new ProgressDialog(this);

        welcome = (TextView) findViewById(R.id.welcomeCustomers);

        mProgressDialog.setMessage("Loading Info..");
        mProgressDialog.show();


        if (user != null) {

            String email = user.getEmail();
            uid = user.getUid();

            welcome.setText("Welcome" + " " + email);
        }

        mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("Customers");


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Customers customer = dataSnapshot.getValue(Customers.class);

                customerId = customer.getCustomerID();
                customerName = customer.getContactPersonName();
                String companyNameList = customer.getCompanyName();
                arrayListCustomer.add(companyNameList);
                arrayListCustomerID.add(customerId);
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
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

            mProgressDialog.dismiss();

        customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Dialog dialog = new Dialog(CustomersActivity.this);
                dialog.setContentView(R.layout.activity_menu_customers);
                dialog.setTitle("Actions");

                final int pos = position;

                final Button btnView = (Button) dialog.findViewById(R.id.customer_menu_view);

                btnView.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                           customerIDSel = String.valueOf(arrayListCustomerID.get(pos));


                        intent = new Intent(getApplicationContext(), CustomersViewDetailsActivity.class);
                        intent.putExtra("CustomerID", customerIDSel);
                        startActivity(intent);
                    }


                });


                final Button btnDelete = (Button) dialog.findViewById(R.id.customer_menu_delete);
                btnDelete.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        String dialog_title = "Confirm?";
                        String dialog_messge = "Confirm";
                        // 1. Instantiate an AlertDialog.Builder with its constructor
                        AlertDialog.Builder builder = new AlertDialog.Builder(CustomersActivity.this);
                        // 2. Chain together various setter methods to set the dialog characteristics
                        builder.setMessage(dialog_messge)
                                .setTitle(dialog_title);


                        // Add the buttons
                        builder
                                .setMessage("Are you sure you want to delete this customer from your list?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        customerIDSel = String.valueOf(arrayListCustomerID.get(pos));
                                        // User clicked Yes button

                                        deleteCustomer(customerIDSel);


                                    }
                                });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog

                                Toast.makeText(getApplicationContext(), "Operation cancelled", Toast.LENGTH_LONG).show();

                                dialog.cancel();
                            }
                        });
                        // 3. Get the AlertDialog from create()
                        AlertDialog dialog = builder.create();

                        // show it
                        dialog.show();


                    }

                });

                final Button btnMessage = (Button) dialog.findViewById(R.id.customer_menu_contact);

                btnMessage.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        customerIDSel = String.valueOf(arrayListCustomerID.get(pos));

                        //CREATE MESSAGE
                        Message createdMessage = new Message();

                        customerIDSel = String.valueOf(arrayListCustomerID.get(pos));

                        createdMessage.setSenderUserID(customerIDSel);

                        createdMessage.setSubject(" ");

                        createdMessage.setSenderName(customerName);

                        Intent messageIntent = new Intent(getApplicationContext(), ComposeMessageActivity.class);
                        messageIntent.putExtra("selectedMessage", createdMessage);
                        startActivity(messageIntent);
                    }


                });

                dialog.dismiss();
                dialog.show();
            }


        });


        addCustomer = (Button) findViewById(R.id.customer_add);


        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), CustomerAddActivity.class);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        pic = (ImageView) findViewById(R.id.imageViewCustomers);

        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
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

    private void deleteCustomer(String idForCustomer) {


        try {
        mProgressDialog.setMessage("Removing Customer..");
        mProgressDialog.show();

       mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("Customers");
            mDatabase.child(idForCustomer).removeValue();

        intent = new Intent(getApplicationContext(), CustomersActivity.class);
        startActivity(intent);


        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }
    }
}