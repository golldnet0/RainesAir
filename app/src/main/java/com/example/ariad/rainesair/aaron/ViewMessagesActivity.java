package com.example.ariad.rainesair.aaron;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ariad.rainesair.Users;
import com.example.ariad.rainesair.LoggedInActivity;
import com.example.ariad.rainesair.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class ViewMessagesActivity extends AppCompatActivity {

    private ListView listView;
    private Message sampleMessage;
    private Users sender;
    private Users reciever;
    private FirebaseAuth mAuth;
    private DatabaseReference recieverReference;
    private ArrayList<Message> messageList;
    ImageView back;
    Intent intent;
    StorageReference storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(">", "HELLO");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        //database connection
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();

        back = (ImageView) findViewById(R.id.messagelist_back);

        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                intent = new Intent(getApplicationContext(), LoggedInActivity.class);
                startActivity(intent);

            }
        });

        recieverReference =  FirebaseDatabase.getInstance().getReference().child(uid).child("Messages");
        messageList = new ArrayList<Message>();
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Message message = dataSnapshot.getValue(Message.class);
                messageList.add(message);

                listView = (ListView)findViewById(R.id.listMessages);

                String messageText;
                String[] values = new String[messageList.size()];

                for(int i = 0; i < messageList.size(); i++){
                    messageText = messageList.get(i).getSubject() + " : " + messageList.get(i).getSenderName();
                    values[i] = messageText;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewMessagesActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

                listView = (ListView)findViewById(R.id.listMessages);

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //View individual message
                        Intent intent = new Intent(getApplicationContext(), ViewIndividualMessageActivity.class);
                        intent.putExtra("selectedMessage", messageList.get(i));
                        startActivity(intent);
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
        };
        recieverReference.addChildEventListener(childEventListener);
        recieverReference.orderByKey();

    }

}
