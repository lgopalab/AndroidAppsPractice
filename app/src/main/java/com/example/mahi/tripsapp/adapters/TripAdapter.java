package com.example.mahi.tripsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mahi.tripsapp.FriendsActivity;
import com.example.mahi.tripsapp.R;
import com.example.mahi.tripsapp.classes.Trip;
import com.example.mahi.tripsapp.classes.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mahi on 4/22/2017.
 */

public class TripAdapter extends RecyclerView.Adapter {

    ArrayList<Trip> trips_list = new ArrayList<Trip>();
    LayoutInflater mInflater;
    Context context;

    public TripAdapter(ArrayList<Trip> trips_list, Context context)
    {
        this.trips_list = trips_list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView trip_name;
        public ImageView trip_image;
        public ImageView add_friend;
        public ImageView remove_trip;
        public View rootView;

        public ViewHolder(View view) {
            super(view);
            trip_name=(TextView) view.findViewById(R.id.trip_name);
            trip_image=(ImageView) view.findViewById(R.id.trip_image);
            add_friend=(ImageView) view.findViewById(R.id.friend_add);
            remove_trip=(ImageView) view.findViewById(R.id.trip_remove);
            rootView = view;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.from(parent.getContext()).inflate(R.layout.trips_layout , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {

        final Trip trip = trips_list.get(position);

        ((ViewHolder)holder).trip_name.setText(trip.getTitle());

        Picasso.with(((ViewHolder)holder).trip_image.getContext())
                .load(trip.getImage_url())
                .into(((ViewHolder)holder).trip_image);

        ((ViewHolder)holder).add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Demo11",trip.getID());

            }
        });



    }

    @Override
    public int getItemCount() {
        return trips_list.size();
    }
}
