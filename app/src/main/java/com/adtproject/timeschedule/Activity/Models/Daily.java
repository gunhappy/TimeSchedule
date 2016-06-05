package com.adtproject.timeschedule.Activity.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by พศิน on 4/6/2559.
 */
public class Daily {

    private Calendar calendar;
    private List<Event> events;

    public Daily(Calendar calendar){

        this.calendar = calendar;
        this.events = new ArrayList<Event>();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public List<Event> getEvents() {
        return this.events;
    }


    public void addEvent(Event event){
        events.add(event);
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event lhs, Event rhs) {
                return lhs.compareTo(rhs);
            }
        });
    }

    @Override
    public String toString() {
        return calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);
    }

    public int compareTo(Daily d){
        if(this.getCalendar().getTimeInMillis() > d.getCalendar().getTimeInMillis())return 1;
        else return -1;
    }
}
