package com.adtproject.timeschedule.Activity.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by พศิน on 17/4/2559.
 */
public class Storage {

    private Storage instance;
    private List<Event> events;

    private Storage(){
        events = new ArrayList<Event>();
    }

    public Storage getInstance() {
        if(instance==null) return new Storage();
        else return instance;
    }

    public  List<Event> getEvents(){
        return events;
    }

    public void addEvent(Event event){
        events.add(event);
    }
}
