package com.example.mahi.tripsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mahi.tripsapp.R;
import com.example.mahi.tripsapp.classes.User;

import java.util.ArrayList;

/**
 * Created by mahi on 4/22/2017.
 */

public class FriendsAdapter extends RecyclerView.Adapter {

    private ArrayList<User> friends_list;
    private LayoutInflater mInflater;
    Context context;

    public FriendsAdapter(ArrayList<User> friends_list, Context context)
    {
        this.friends_list = friends_list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView friend_name;
        public ImageView friend_image;
        public ImageView remove_friend;
        public ImageView add_friend;
        public View rootView;

        public ViewHolder(View view) {
            super(view);
            friend_name=(TextView) view.findViewById(R.id.friend_name);
            friend_image=(ImageView) view.findViewById(R.id.friend_image);
            remove_friend=(ImageView) view.findViewById(R.id.friend_remove);
            rootView = view;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.from(parent.getContext()).inflate(R.layout.friends_list_layout , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder)holder).friend_name.setText(friends_list.get(position).getFirst_name()+" "+friends_list.get(position).getFirst_name());
        Log.d("friends",friends_list.get(position).getFirst_name());

    }

    @Override
    public int getItemCount() {
        return friends_list.size();
    }
}
