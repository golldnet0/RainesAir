package com.example.ariad.rainesair.aaron;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariad.rainesair.CustomersActivity;
import com.example.ariad.rainesair.Users;
import com.example.ariad.rainesair.LoggedInActivity;
import com.example.ariad.rainesair.PartsActivity;
import com.example.ariad.rainesair.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Written by Aaron Browne
public class ComposeMessageActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseSendMessage;
    private DatabaseReference mDatabaseRecipeint;
    ImageView back;
    TextView textRecipient;
    EditText editSubject, editBody;

    private Message selectedMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);

        Intent intent = getIntent();
        selectedMessage = (Message)intent.getSerializableExtra("selectedMessage");

        textRecipient = (TextView) findViewById(R.id.textRecipient);
        textRecipient.setText(selectedMessage.getSenderName());

        editSubject = (EditText)findViewById(R.id.editSubject);
        editSubject.setText("RE: " + selectedMessage.getSubject());

        back = (ImageView) findViewById(R.id.compose_message_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent  intent = new Intent(getApplicationContext(), LoggedInActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sendMessage(View view){
        //database connection
        mAuth = FirebaseAuth.getInstance();

        //create new message
        final Message messageToBeSent = new Message();

        //put subject/body into message
        editSubject = (EditText)findViewById(R.id.editSubject);
        messageToBeSent.setSubject(editSubject.getText().toString());

        editBody = (EditText)findViewById(R.id.editMessageBody);
        messageToBeSent.setMessageText(editBody.getText().toString());

        //string for the current user
        String senderUID = mAuth.getCurrentUser().getUid();
        messageToBeSent.setSenderUserID(senderUID);


        //getting current user name to put into sender field
        mDatabaseRecipeint = FirebaseDatabase.getInstance().getReference().child(senderUID).child("PersonalInfo");
        mDatabaseRecipeint.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                String senderName = user.getFirstName() + " " + user.getLastName();

                messageToBeSent.setSenderName(senderName);

                //string for the receipent
                String recipUID = selectedMessage.getSenderUserID();

                //adding to database
                mDatabaseSendMessage = FirebaseDatabase.getInstance().getReference().child(recipUID).child("Messages").push();
                messageToBeSent.setDbKey(mDatabaseSendMessage.getKey());// adding the key that contains the reference to the database so I can trace it and delete the message later if needed.

                mDatabaseSendMessage.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Toast.makeText(ComposeMessageActivity.this, "Message " + messageToBeSent.getSubject() +" Sent", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                mDatabaseSendMessage.setValue(messageToBeSent);

                editSubject.setText("RE:");
                editBody.setText("");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        }
}
