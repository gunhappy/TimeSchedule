package com.adtproject.timeschedule.Activity.Models;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by พศิน on 17/4/2559.
 */
public class Event {
    private Calendar calendar;
    private Time startTime;
    private int duration;
    private String title;

    public Event(Calendar calendar, Time startTime, int duration, String title) {
        this.calendar = calendar;
        this.startTime = startTime;
        this.duration = duration;
        this.title = title;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Time getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }
}
