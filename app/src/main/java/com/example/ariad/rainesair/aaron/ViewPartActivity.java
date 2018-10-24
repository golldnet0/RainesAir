package com.example.ariad.rainesair.aaron;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ariad.rainesair.LoggedInActivity;
import com.example.ariad.rainesair.Parts;
import com.example.ariad.rainesair.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ViewPartActivity extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_part);

        back = (ImageView) findViewById(R.id.view_part_back);

        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent backIntent = new Intent(getApplicationContext(), LoggedInActivity.class);
                startActivity(backIntent);

            }
        });

        final ImageView pictureBox = (ImageView)findViewById(R.id.imageViewPart);

        Intent intent = getIntent();
        String partID = intent.getStringExtra("selectedPublisherID");

        StorageReference defaultStorage = FirebaseStorage.getInstance().getReference().child(partID);

        defaultStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.v(">", "ONSUCCESSLISTENER");
                Picasso.with(getApplicationContext()).load(uri.toString()).into(pictureBox);
            }
        });

    }
}
