package com.example.mahi.tripsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mahi.tripsapp.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class UserHomeActivity extends AppCompatActivity {

    private Button signOut,friends_button,profile_button,trips_button;
    private FirebaseAuth mAuth;
    DatabaseReference usersReference;
    User current_user;
    boolean user_added;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        user_added = false;

        mAuth = FirebaseAuth.getInstance();
        current_user = new User();


        usersReference = FirebaseDatabase.getInstance().getReference().child("users");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(!dataSnapshot.hasChild(mAuth.getCurrentUser().getUid()))
                {
                    User new_user = new User();
                    new_user.setEmail(mAuth.getCurrentUser().getEmail());
                    new_user.setID(mAuth.getCurrentUser().getUid());
                    new_user.setFirst_name("none");
                    new_user.setImage_url("none");
                    new_user.setLast_name("none");
                    new_user.setGender("none");
                    new_user.setFriends(null);
                    new_user.setPending(null);
                    new_user.setSent(null);
                    Map<String,Object> new_user_post = new_user.toMap();

                    usersReference.child(mAuth.getCurrentUser().getUid()).setValue(new_user_post);

                    Log.d("Demo","In if");
                }
                else
                {
                    for(DataSnapshot userSnapshot : dataSnapshot.getChildren())
                    {
                        if (userSnapshot.getRef().toString().equals(usersReference.child(mAuth.getCurrentUser().getUid()).toString()))
                        {
                            current_user = userSnapshot.getValue(User.class);
                        }
                    }
                    Log.d("Demo","In else"+"\n"+dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue());
                    Log.d("Demo",current_user.getEmail()+"\n in DataSnapshot");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        usersReference.addValueEventListener(postListener);





        signOut = (Button) findViewById(R.id.signOut);
        friends_button = (Button) findViewById(R.id.friends_button);
        profile_button = (Button) findViewById(R.id.profile_button);
        trips_button = (Button) findViewById(R.id.trips_button);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserHomeActivity.this, mAuth.getCurrentUser().getUid().toString(), Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                finish();
            }
        });

        friends_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHomeActivity.this,FriendsActivity.class));
            }
        });

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHomeActivity.this,ProfileActivity.class));
            }
        });

        trips_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHomeActivity.this,TripsActivity.class));
            }
        });
    }
}
