package com.example.mahi.tripsapp.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mahi.tripsapp.R;
import com.example.mahi.tripsapp.classes.Message;
import com.example.mahi.tripsapp.classes.Trip;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mahi on 4/22/2017.
 */

public class TripChatAdapter extends RecyclerView.Adapter {

    ArrayList<Message> message_list = new ArrayList<Message>();
    LayoutInflater mInflater;
    Context context;
    boolean isme;

    public TripChatAdapter(ArrayList<Message> message_list, Context context)
    {
        this.message_list = message_list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView message_text;
        public TextView sent_by;
        public TextView time_sent;
        public ImageView delete_text;
        public View rootView;

        public ViewHolder(View view) {
            super(view);
            message_text=(TextView) view.findViewById(R.id.isme_message);
            sent_by=(TextView) view.findViewById(R.id.isme_text_by);
            time_sent=(TextView) view.findViewById(R.id.isme_text_time);
            delete_text=(ImageView) view.findViewById(R.id.trip_image);
            rootView = view;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = mInflater.from(parent.getContext()).inflate(R.layout.ismine_message , parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {

        final Message message = message_list.get(position);
        ((ViewHolder)holder).message_text.setText(message.getMessage());
        ((ViewHolder)holder).sent_by.setText(message.getSent_by());
        ((ViewHolder)holder).time_sent.setText(message.getTime());


        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(message.getSent_by()))
        {
            ((ViewHolder)holder).rootView.setForegroundGravity(Gravity.LEFT);
        }
        else
        {
            ((ViewHolder)holder).rootView.setForegroundGravity(Gravity.RIGHT);
        }




    }

    @Override
    public int getItemCount() {
        return message_list.size();
    }
}
