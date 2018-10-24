package com.example.ariad.rainesair.aaron;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariad.rainesair.LoggedInActivity;
import com.example.ariad.rainesair.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//created by Aaron Browne
public class ViewIndividualMessageActivity extends AppCompatActivity {

    ImageView back;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual_message);

        intent = getIntent();
        Message message = (Message)intent.getSerializableExtra("selectedMessage");

        TextView senderText = (TextView)findViewById(R.id.sender);
        senderText.setText(message.getSenderName());

        TextView subjectText = (TextView)findViewById(R.id.subject);
        subjectText.setText(message.getSubject());

        TextView bodyText = (TextView)findViewById(R.id.messageBody);
        bodyText.setText(message.getMessageText());

        back = (ImageView) findViewById(R.id.reply_message_back);

        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                intent = new Intent(getApplicationContext(), ViewMessagesActivity.class);
                startActivity(intent);

            }
        });

    }

    public void replyToMessage(View view){
        Intent receiveIntent = getIntent();
        final Message message = (Message)receiveIntent.getSerializableExtra("selectedMessage");

        Intent sendIntent = new Intent(getApplicationContext(), ComposeMessageActivity.class);
        sendIntent.putExtra("selectedMessage", message);
        startActivity(sendIntent);
    }

    public void deleteMessage(View view){
        Intent receiveIntent = getIntent();
        final Message message = (Message)receiveIntent.getSerializableExtra("selectedMessage");

        String messageDBChild = message.getDbKey();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference messageReference = FirebaseDatabase.getInstance().getReference().child(uid).child("Messages").child(messageDBChild);

        messageReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(ViewIndividualMessageActivity.this, "Message Deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ViewMessagesActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        messageReference.removeValue();
    }
}
