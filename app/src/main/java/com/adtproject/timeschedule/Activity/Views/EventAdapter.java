package com.adtproject.timeschedule.Activity.Views;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adtproject.timeschedule.Activity.Activity.DayFragment;
import com.adtproject.timeschedule.Activity.Activity.MainActivity;
import com.adtproject.timeschedule.Activity.Models.Event;
import com.adtproject.timeschedule.Activity.Models.Storage;
import com.adtproject.timeschedule.Activity.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by พศิน on 4/6/2559.
 */
public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> events;
    private Calendar calendar;

    public EventAdapter(List<Event> obj,Calendar calendar){
        events = obj;
        this.calendar = calendar;
    }



    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View v = layoutInflater.inflate(R.layout.event_cell, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, final int position) {
        holder.event_text.setText(events.get(position).getTitle());
        holder.time_text.setText(events.get(position).getTimeRangeText());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete Event")
                        .setMessage("Are you sure you want to delete this event?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Storage.getInstance().getDaily(calendar).getEvents().remove(position);
                                MainActivity.dailyFragment.loadEvent();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


}
