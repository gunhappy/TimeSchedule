package com.adtproject.timeschedule.Activity.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.adtproject.timeschedule.Activity.R;

/**
 * Created by พศิน on 4/6/2559.
 */
public class OverviewHolder extends RecyclerView.ViewHolder {

    TextView dayText;
    TextView monthText;
    TextView eventCountText;

    public OverviewHolder(View itemView) {
        super(itemView);
        dayText = (TextView)itemView.findViewById(R.id.overview_day_textview);
        monthText = (TextView)itemView.findViewById(R.id.overview_mont_textview);
        eventCountText = (TextView)itemView.findViewById(R.id.overview_count_textview);
    }
}
