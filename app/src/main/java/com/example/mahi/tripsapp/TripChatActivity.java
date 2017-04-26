package com.example.mahi.tripsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mahi.tripsapp.adapters.FriendsAdapter;
import com.example.mahi.tripsapp.adapters.TripChatAdapter;
import com.example.mahi.tripsapp.classes.Message;
import com.example.mahi.tripsapp.classes.Trip;
import com.example.mahi.tripsapp.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class TripChatActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference messageRef,messagesRef;
    String trip_id;
    EditText message_text;
    ImageView send_message;
    ArrayList<Message> messageList;
    RecyclerView messageRecycler;
    TripChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_chat);

        trip_id = getIntent().getStringExtra("trip_id");

        mAuth = FirebaseAuth.getInstance();
        messageRef = FirebaseDatabase.getInstance().getReference().child("trips").child(trip_id);
        messageList = new ArrayList<>();

        ValueEventListener messageListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("messages"))
                {
                    messagesRef=FirebaseDatabase.getInstance().getReference().child("trips").child(trip_id).child("messages");
                    ValueEventListener messagesListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot postSnapshot: dataSnapshot.getChildren())
                            {
                                Log.d("Demo",postSnapshot.getValue().toString());
                                Message message = postSnapshot.getValue(Message.class);
                                Log.d("Demomessage",message.toString());
                                messageList.add(message);
                            }
                            Log.d("Demo",messageList.toString());

                            messageRecycler = (RecyclerView) findViewById(R.id.message_recycler);
                            messageRecycler.setLayoutManager(new LinearLayoutManager(TripChatActivity.this));
                            chatAdapter = new TripChatAdapter(messageList,TripChatActivity.this);
                            messageRecycler.setAdapter(chatAdapter);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    messagesRef.addValueEventListener(messagesListener);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        messageRef.addValueEventListener(messageListener);


        message_text = (EditText) findViewById(R.id.chat_message_text);
        send_message = (ImageView) findViewById(R.id.message_send);
        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = messageRef.push().getKey();
                Message message = new Message();
                message.setImage_url("0");
                message.setMessage(message_text.getText().toString());
                message.setSent_by(mAuth.getCurrentUser().getUid());
                Date date = new Date();
                message.setTime(date.toString());
                message.setDelete_list(null);

                Map<String,Object> new_message_post = message.toMap();
                messageRef.child("messages").child(key).setValue(new_message_post);
            }
        });
    }
}
