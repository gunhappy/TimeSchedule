package com.adtproject.timeschedule.Activity.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adtproject.timeschedule.Activity.Models.CalendarName;
import com.adtproject.timeschedule.Activity.Models.Daily;
import com.adtproject.timeschedule.Activity.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by พศิน on 4/6/2559.
 */
public class OverviewAdapter extends RecyclerView.Adapter<OverviewHolder> {
    private List<Daily> dailyList;

    public OverviewAdapter(List<Daily> obj){
        this.dailyList = obj;
    }

    @Override
    public OverviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View v = layoutInflater.inflate(R.layout.overview_cell, parent, false);
        return new OverviewHolder(v);
    }

    @Override
    public void onBindViewHolder(OverviewHolder holder, int position) {
        Daily daily = dailyList.get(position);
        Calendar calendar = daily.getCalendar();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        CalendarName cn = new CalendarName();
        holder.dayText.setText(calendar.get(Calendar.DAY_OF_MONTH)+" "+cn.getDayName(dayOfWeek));
        holder.monthText.setText(cn.getMonthName(calendar.get(Calendar.MONTH))+" "+calendar.get(Calendar.YEAR));
        holder.eventCountText.setText(daily.getEvents().size()+"   events");
    }

    @Override
    public int getItemCount() {
        return dailyList.size();
    }
}
