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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

public class SOActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    ImageView pic;
    TextView welcome;
    Button addSO;
    SearchView searchView;

    String uid, soID, soCompany, soDate, partNumber2, profilePic;
    ListView soList;
    List<String> arrayListSO = new ArrayList<>();
    List<String> arrayListSODateComp = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    private ProgressDialog mProgressDialog;
    private DatabaseReference mDatabase, mRef;
    private FirebaseUser user;
    private String soIDSel = " ";
    StorageReference storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so);
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
        getMenuInflater().inflate(R.menu.so, menu);

        try {

            welcome = (TextView) findViewById(R.id.welcomeSO);

            searchView = (SearchView) findViewById(R.id.so_search);

            user = FirebaseAuth.getInstance().getCurrentUser();

            soList = (ListView) findViewById(R.id.so_list);

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListSODateComp);
            adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListSO);

            soList.setAdapter(adapter);

            mProgressDialog = new ProgressDialog(this);

            mProgressDialog.setMessage("Loading Info..");
            mProgressDialog.show();


            if (user != null) {
                String email = user.getEmail();
                uid = user.getUid();

                welcome.setText("Welcome" + " " + email);
            }

            mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("SO");


            mDatabase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    SO so = dataSnapshot.getValue(SO.class);

                    soID = so.getSoNum();
                    soCompany = so.getCompanyName();
                    soDate = so.getSalesDate();
                    partNumber2 = so.getPartNumber();
                    arrayListSODateComp.add(soDate + "      " + soCompany + "      " + partNumber2);
                    arrayListSO.add(soID);
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

            soList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    final Dialog dialog = new Dialog(SOActivity.this);
                    dialog.setContentView(R.layout.activity_menu_so);
                    dialog.setTitle("Actions");

                    final int pos = position;

                    final Button btnView = (Button) dialog.findViewById(R.id.so_menu_view);

                    btnView.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            soIDSel = String.valueOf(arrayListSO.get(pos));


                            intent = new Intent(getApplicationContext(), SOViewDetailsActivity.class);
                            intent.putExtra("soID", soIDSel);
                            startActivity(intent);
                        }


                    });


                    final Button btnDelete = (Button) dialog.findViewById(R.id.so_menu_delete);
                    btnDelete.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            String dialog_title = "Confirm?";
                            String dialog_messge = "Confirm";
                            // 1. Instantiate an AlertDialog.Builder with its constructor
                            AlertDialog.Builder builder = new AlertDialog.Builder(SOActivity.this);
                            // 2. Chain together various setter methods to set the dialog characteristics
                            builder.setMessage(dialog_messge)
                                    .setTitle(dialog_title);


                            // Add the buttons
                            builder
                                    .setMessage("Are you sure you want to delete this SO from the system?")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            soIDSel = String.valueOf(arrayListSO.get(pos));
                                            // User clicked Yes button

                                            deleteSO(soIDSel);


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

                    dialog.dismiss();
                    dialog.show();
                }


            });


            addSO = (Button) findViewById(R.id.so_add);


            addSO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(getApplicationContext(), SOAddActivity.class);
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

            pic = (ImageView) findViewById(R.id.imageViewSO);

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


    private void deleteSO(String idForSO) {


        try {
            mProgressDialog.setMessage("Removing SO..");
            mProgressDialog.show();

            mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("SO");
            mDatabase.child(idForSO).removeValue();
            intent = new Intent(getApplicationContext(), SOActivity.class);
            startActivity(intent);


        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }
    }


}

