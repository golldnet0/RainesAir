package com.example.ariad.rainesair;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariad.rainesair.aaron.ViewMessagesActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.Map;


public class PersonalInfoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {

    public static final int RESULT_LOAD = 1;
    Intent intent;
    ImageView pic;
    TextView welcome;
    EditText firstName, lastName, address, city, state, zip, cell, workNum, ext, email;
    Spinner country;
    Button edit, changePassw, delete, addProfile;
    String uid, filename, profilePic;
    String newPassword1;
    String confirmPassword1;
    Users user;
    private DatabaseReference mRef, mRef2;
    private FirebaseUser user1;
    private StorageReference storage;
    private ProgressDialog mProgressDialog;
    private Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
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
        getMenuInflater().inflate(R.menu.personal_info, menu);

        try {

            user1 = FirebaseAuth.getInstance().getCurrentUser();

            mProgressDialog = new ProgressDialog(this);

            firstName = (EditText) findViewById(R.id.personalInfo_first_name);
            lastName = (EditText) findViewById(R.id.personalInfo_last_name);
            address = (EditText) findViewById(R.id.personalInfo_address);
            city = (EditText) findViewById(R.id.personalInfo_city);
            state = (EditText) findViewById(R.id.personalInfo_state);
            zip = (EditText) findViewById(R.id.personalInfo_zip);
            cell = (EditText) findViewById(R.id.personalInfo_cell);
            workNum = (EditText) findViewById(R.id.personalInfo_work_number);
            ext = (EditText) findViewById(R.id.personalInfo_ext);
            email = (EditText) findViewById(R.id.personalInfo_email);
            country = (Spinner) findViewById(R.id.personalInfo_country);


            edit = (Button) findViewById(R.id.personalInfo_edit_info);
            changePassw = (Button) findViewById(R.id.personalInfo_edit_password);
            delete = (Button) findViewById(R.id.personalInfo_delete);
            addProfile = (Button) findViewById(R.id.personalInfo_edit_profile);

            welcome = (TextView) findViewById(R.id.welcomeInfo);

            mProgressDialog.setMessage("Loading profile");
            mProgressDialog.show();

            firstName.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    firstName.setFocusable(true);
                    firstName.setFocusableInTouchMode(true);

                    return false;
                }
            });


            lastName.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    lastName.setFocusable(true);
                    lastName.setFocusableInTouchMode(true);

                    return false;
                }
            });


            address.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    address.setFocusable(true);
                    address.setFocusableInTouchMode(true);

                    return false;
                }
            });


            city.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    city.setFocusable(true);
                    city.setFocusableInTouchMode(true);

                    return false;
                }
            });


            state.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    state.setFocusable(true);
                    state.setFocusableInTouchMode(true);

                    return false;
                }
            });


            zip.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    zip.setFocusable(true);
                    zip.setFocusableInTouchMode(true);

                    return false;
                }
            });


            cell.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    cell.setFocusable(true);
                    cell.setFocusableInTouchMode(true);

                    return false;
                }
            });


            workNum.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    workNum.setFocusable(true);
                    workNum.setFocusableInTouchMode(true);

                    return false;
                }
            });


            ext.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ext.setFocusable(true);
                    ext.setFocusableInTouchMode(true);

                    return false;
                }
            });


            email.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    email.setFocusable(true);
                    email.setFocusableInTouchMode(true);

                    return false;
                }
            });


            if (user1 != null) {
                String emailValue = user1.getEmail();
                uid = user1.getUid();

                welcome.setText("Welcome" + " " + emailValue);
                pic = (ImageView) findViewById(R.id.imageViewInfo);

                addProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, RESULT_LOAD);
                    }
                });

                mRef = FirebaseDatabase.getInstance().getReference().child(uid).child("PersonalInfo");
                mRef2 = FirebaseDatabase.getInstance().getReference().child(uid);

                storage = FirebaseStorage.getInstance().getReference().child(uid);

                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        int index = 0;

                        user = dataSnapshot.getValue(Users.class);

                        String firstName1 = user.getFirstName();
                        String lastName1 = user.getLastName();
                        String address1 = user.getAddress();
                        String city1 = user.getCity();
                        String state1 = user.getState();
                        String zip1 = user.getZipCode();
                        String cell1 = user.getCell();
                        String workNum1 = user.getWorkNumber();
                        String ext1 = user.getExtension();
                        String email1 = user.getEmail();
                        String country1 = user.getCountry();
                        String photo = user.getProfilePic();

                        for (int i = 0; i < country.getCount(); i++) {

                            if (country.getItemAtPosition(i).equals(country1)) {
                                index = i;
                            }
                        }

                        firstName.setText(firstName1);
                        lastName.setText(lastName1);
                        address.setText(address1);
                        city.setText(city1);
                        state.setText(state1);
                        zip.setText(zip1);
                        cell.setText(cell1);
                        workNum.setText(workNum1);
                        ext.setText(ext1);
                        email.setText(email1);
                        country.setSelection(index);


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


                        mProgressDialog.dismiss();
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            } else if (user1 == null) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }


            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    updateProfile();
                }
            });


            changePassw.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    updatePassword();


                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    String dialog_title = "Confirm?";
                    String dialog_messge = "Confirm";
                    // 1. Instantiate an AlertDialog.Builder with its constructor
                    AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInfoActivity.this);
                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(dialog_messge)
                            .setTitle(dialog_title);


                    // Add the buttons
                    builder
                            .setMessage("Are you sure you want to delete your profile permanently?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    // User clicked Yes button
                                    deleteAccount();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewLoggedIn:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD);
                break;

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (requestCode == RESULT_LOAD && resultCode == RESULT_OK && data != null) {

                mProgressDialog.setMessage("Uploading...");
                mProgressDialog.show();

                selectedImage = data.getData();
                StorageReference filepath = storage.child(selectedImage.getLastPathSegment());
                filename = filepath.getName();

                filepath.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(getApplicationContext(), "Profile picture uploaded successfully", Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();
                    }
                });

                pic.setImageURI(selectedImage);

                String firstName2 = firstName.getText().toString();
                String lastName2 = lastName.getText().toString();
                String address2 = address.getText().toString();
                String city2 = city.getText().toString();
                String state2 = state.getText().toString();
                String zip2 = zip.getText().toString();
                String cell2 = cell.getText().toString();
                String workNum2 = workNum.getText().toString();
                String ext2 = ext.getText().toString();
                String email2 = email.getText().toString();
                String country2 = country.getSelectedItem().toString();

                Users userUpdate = new Users(address2, cell2, city2, country2, email2, ext2, firstName2, lastName2, state2,
                        workNum2, zip2, filename);

                Map<String, Object> userValues = userUpdate.toMap();

                mRef.updateChildren(userValues);

            }

        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }

    }


    private void deleteAccount() {

        try {


            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

            mProgressDialog.setMessage("Deleting Account..");
            mProgressDialog.show();


            user1.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(getApplicationContext(), "User has been deleted", Toast.LENGTH_LONG).show();
                    }
                }
            });

            mRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Iterator<DataSnapshot> snapshotIterator = dataSnapshot.getChildren().iterator();

                    while (snapshotIterator.hasNext()) {
                        DataSnapshot snapshot = snapshotIterator.next();
                        snapshot.getRef().removeValue();
                    }

                    Toast.makeText(getApplicationContext(), "User has been deleted", Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();
                    FirebaseAuth.getInstance().signOut();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }

    }


    public void updatePassword() {

        try {

            final Dialog dialog = new Dialog(PersonalInfoActivity.this);
            dialog.setContentView(R.layout.activity_change_password);
            dialog.setTitle("Change Password");

            final EditText newPassword = (EditText) dialog
                    .findViewById(R.id.changepassword_new);
            final EditText confirmPassword = (EditText) dialog
                    .findViewById(R.id.changepassword_confirm);


            newPassword.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    newPassword.setFocusable(true);
                    newPassword.setFocusableInTouchMode(true);

                    return false;
                }
            });

            confirmPassword.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    confirmPassword.setFocusable(true);
                    confirmPassword.setFocusableInTouchMode(true);

                    return false;
                }
            });

            Button btnChange = (Button) dialog.findViewById(R.id.changepassword_change);

            btnChange.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {


                    newPassword1 = newPassword.getText().toString();
                    confirmPassword1 = confirmPassword.getText().toString();


                    if ((newPassword1.length()) > 6 && (newPassword1.equals(confirmPassword1))) {


                        user1.updatePassword(newPassword1)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(PersonalInfoActivity.this,
                                                    "Password updated successfully",
                                                    Toast.LENGTH_LONG).show();
                                        } else {

                                            if (newPassword1.length() < 6) {

                                                Toast.makeText(PersonalInfoActivity.this,
                                                        "Password must be greater than 6 characters.",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                            Toast.makeText(PersonalInfoActivity.this,
                                                    "Password was not changed",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                        dialog.dismiss();

                    } else {
                        Toast.makeText(PersonalInfoActivity.this,
                                "Passwords do not match",
                                Toast.LENGTH_LONG).show();
                    }


                }
            });

            dialog.show();

        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }
    }


    public void updateProfile() {

        try {

            String firstName2 = firstName.getText().toString();
            String lastName2 = lastName.getText().toString();
            String address2 = address.getText().toString();
            String city2 = city.getText().toString();
            String state2 = state.getText().toString();
            String zip2 = zip.getText().toString();
            String cell2 = cell.getText().toString();
            String workNum2 = workNum.getText().toString();
            String ext2 = ext.getText().toString();
            String email2 = email.getText().toString();
            String country2 = country.getSelectedItem().toString();

            Users userUpdate = new Users(address2, cell2, city2, country2, email2, ext2, firstName2, lastName2, state2,
                    workNum2, zip2, profilePic);


            Map<String, Object> userValues = userUpdate.toMap();


            mRef.updateChildren(userValues);

            Toast.makeText(PersonalInfoActivity.this,
                    "Profile updated successfully",
                    Toast.LENGTH_LONG).show();


            user1.updateEmail(email2)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            }
                        }
                    });


        } catch (SQLException ex) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }

    }


}
