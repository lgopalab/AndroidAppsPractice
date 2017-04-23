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
import com.example.mahi.tripsapp.classes.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mahi on 4/22/2017.
 */

public class DiscAdapter extends RecyclerView.Adapter {

    ArrayList<User> friends_list = new ArrayList<User>();
    LayoutInflater mInflater;
    Context context;

    public DiscAdapter(ArrayList<User> friends_list, Context context)
    {
        this.friends_list = friends_list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView friend_name;
        public ImageView friend_image;
        public ImageView add_friend;
        public View rootView;

        public ViewHolder(View view) {
            super(view);
            friend_name=(TextView) view.findViewById(R.id.friend_name);
            friend_image=(ImageView) view.findViewById(R.id.friend_image);
            add_friend=(ImageView) view.findViewById(R.id.friend_accept);
            rootView = view;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.from(parent.getContext()).inflate(R.layout.disc_list_layout , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {

        final User user = friends_list.get(position);
        ((ViewHolder)holder).friend_name.setText(user.getFirst_name()+" "+user.getLast_name());
        Picasso.with(((ViewHolder)holder).friend_image.getContext())
                .load(user.getImage_url())
                .into(((ViewHolder)holder).friend_image);

        ((ViewHolder)holder).add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Demo11",user.getID());
                FriendsActivity.addFriend(user.getID());

            }
        });



    }

    @Override
    public int getItemCount() {
        return friends_list.size();
    }
}
