package com.adtproject.timeschedule.Activity.Models;

import java.util.ArrayList;
import java.util.Calendar;
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
    }

    @Override
    public String toString() {
        return calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);
    }
}
