package com.example.mahi.tripsapp;

import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mahi.tripsapp.adapters.FriendsAdapter;
import com.example.mahi.tripsapp.adapters.TripAdapter;
import com.example.mahi.tripsapp.classes.Trip;
import com.example.mahi.tripsapp.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TripsActivity extends AppCompatActivity {

    DatabaseReference tripsRef;
    FirebaseAuth mAuth;
    ImageView addTrip;
    EditText tripName;
    ArrayList<Trip> trips_list = new ArrayList<>();
    RecyclerView tripsRecycler;
    TripAdapter tripsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        addTrip = (ImageView) findViewById(R.id.trip_add);
        tripName = (EditText) findViewById(R.id.trip_add_name);
        tripsRecycler = (RecyclerView) findViewById(R.id.trips_recycler);


        mAuth = FirebaseAuth.getInstance();
        tripsRef = FirebaseDatabase.getInstance().getReference().child("trips");
        final ValueEventListener tripsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren())
                    {
                        Trip trip = postSnapshot.getValue(Trip.class);
                        trips_list.add(trip);
                    }
                    tripsRecycler.setLayoutManager(new LinearLayoutManager(TripsActivity.this));
                    tripsAdapter = new TripAdapter(trips_list,TripsActivity.this);
                    tripsRecycler.setAdapter(tripsAdapter);
                }
                else
                {
                    trips_list.clear();
                    tripsRecycler.refreshDrawableState();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        tripsRef.addValueEventListener(tripsListener);


        addTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = tripsRef.push().getKey();
                Trip trip = new Trip();
                tripName.getText().toString();
                trip.setID(key);
                trip.setImage_url("none");
                trip.setTitle(tripName.getText().toString());
                trip.setCreated_by(mAuth.getCurrentUser().getUid());
                trip.setLocation("none");
                trip.setMembers(null);
                trip.setMessages(null);
                trip.setUnsubscribe_list(null);
                Map<String,Object> new_trip_post = trip.toMap();

                tripsRef.child(key).setValue(new_trip_post);
            }
        });
    }

}
