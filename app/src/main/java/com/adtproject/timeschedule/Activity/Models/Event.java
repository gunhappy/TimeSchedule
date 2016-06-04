package com.adtproject.timeschedule.Activity.Models;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by พศิน on 17/4/2559.
 */
public class Event {
    private Calendar calendar;
    private int duration,hour,minute,endHour;
    private String title;

    public Event(Calendar calendar, int hour,int minute, int duration, String title) {
        this.calendar = calendar;
        this.hour = hour;
        this.minute = minute;
        this.duration = duration;
        this.endHour = hour+duration;
        this.title = title;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public String getTimeRangeText(){
        String hr = hour+"";
        String min = minute+"";
        String hrEnd = endHour+"";
        if(hour<10) hr = "0"+hour;
        if(endHour<10) hrEnd = "0"+endHour;
        if(minute<10) min = "0"+minute;
        return String.format("%s.%s - %s.%s",hr,min,hrEnd,min);
    }
}
