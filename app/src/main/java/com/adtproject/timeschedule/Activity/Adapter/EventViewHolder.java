package com.adtproject.timeschedule.Activity.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.adtproject.timeschedule.Activity.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

/**
 * Created by พศิน on 4/6/2559.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {

    TextView event_text;
    TextView time_text;

    public EventViewHolder(View itemView) {
        super(itemView);
        event_text = (TextView)itemView.findViewById(R.id.event_title_textview);
        time_text = (TextView)itemView.findViewById(R.id.event_time_textview);
    }
}
