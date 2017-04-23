package com.example.mahi.tripsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TabHost;

import com.example.mahi.tripsapp.adapters.DiscAdapter;
import com.example.mahi.tripsapp.adapters.FriendsAdapter;
import com.example.mahi.tripsapp.adapters.PendingAdapter;
import com.example.mahi.tripsapp.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FriendsActivity extends AppCompatActivity {

    RecyclerView friendsRecycler,pendingRecycler,discRecycler;
    private RecyclerView.Adapter friendsAdapter,pendingAdapter,discAdapter;

    Map<String,Boolean> friends = new HashMap<>();

    ArrayList<User> friends_list = new ArrayList<>();
    ArrayList<User> pending_list = new ArrayList<>();
    ArrayList<User> disc_list = new ArrayList<>();
    FirebaseAuth mAuth;
    StorageReference profImageRef;
    DatabaseReference userRef,usersRef;



    @Override
    protected void onResume() {
        super.onPostResume();
        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild("friends"))
                {
                    friends =(Map<String,Boolean>) dataSnapshot.child("friends").getValue();
                    Log.d("friends",friends.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        userRef.addValueEventListener(userListener);

        usersRef = FirebaseDatabase.getInstance().getReference().child("users");
        ValueEventListener listListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren())
                {
                    User user = postSnapshot.getValue(User.class);
                    if(!friends.isEmpty() && friends.containsKey(postSnapshot.getKey()) && friends.get(postSnapshot.getKey()))
                    {
                        friends_list.add(user);
                        Log.d("friendsif",friends_list.toString());
                    }
                    else if(!friends.isEmpty() && friends.containsKey(postSnapshot.getKey()) && !friends.get(postSnapshot.getKey()))
                    {
                        pending_list.add(user);
                    }
                    else if (!friends.containsKey(postSnapshot.getKey()) && !user.getID().equals(mAuth.getCurrentUser().getUid()))
                    {
                        disc_list.add(user);
                    }

                }

                friendsRecycler = (RecyclerView) findViewById(R.id.friends_list);
                friendsRecycler.setLayoutManager(new LinearLayoutManager(FriendsActivity.this));
                friendsAdapter = new FriendsAdapter(friends_list,FriendsActivity.this);
                friendsRecycler.setAdapter(friendsAdapter);

                pendingRecycler = (RecyclerView) findViewById(R.id.pending_list);
                pendingRecycler.setLayoutManager(new LinearLayoutManager(FriendsActivity.this));
                pendingAdapter = new PendingAdapter(pending_list,FriendsActivity.this);
                pendingRecycler.setAdapter(pendingAdapter);

                discRecycler = (RecyclerView) findViewById(R.id.discover_list);
                discRecycler.setLayoutManager(new LinearLayoutManager(FriendsActivity.this));
                discAdapter = new DiscAdapter(disc_list,FriendsActivity.this);
                discRecycler.setAdapter(discAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        usersRef.addValueEventListener(listListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        TabHost tabHost = (TabHost) findViewById(R.id.friendsTabs);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("friendListTab");
        tabSpec.setContent(R.id.friendListTab);
        tabSpec.setIndicator("Friends List");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("pendingListTab");
        tabSpec.setContent(R.id.pendingListTab);
        tabSpec.setIndicator("Pending Requests");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("discoverListTab");
        tabSpec.setContent(R.id.discoverListTab);
        tabSpec.setIndicator("Discover");
        tabHost.addTab(tabSpec);

    }

    public static void addFriend(final String ID)
    {
        Log.d("Demo",ID);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference requestRef = FirebaseDatabase.getInstance().getReference().child("users").child(ID);
        ValueEventListener requestListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("friends").child(mAuth.getCurrentUser().getUid()).setValue(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        requestRef.addValueEventListener(requestListener);
    }
}
