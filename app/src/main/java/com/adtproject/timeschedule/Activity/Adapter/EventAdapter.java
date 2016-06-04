package com.adtproject.timeschedule.Activity.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adtproject.timeschedule.Activity.Models.Event;
import com.adtproject.timeschedule.Activity.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;
import java.util.Random;

/**
 * Created by พศิน on 4/6/2559.
 */
public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> events;

    public EventAdapter(List<Event> obj){
        events = obj;
    }


    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View v = layoutInflater.inflate(R.layout.event_cell, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.event_text.setText(events.get(position).getTitle());
        holder.time_text.setText(events.get(position).getTimeRangeText());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


}
