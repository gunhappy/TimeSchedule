package com.adtproject.timeschedule.Activity.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by พศิน on 17/4/2559.
 */
public class Storage {

    private static Storage instance;
    private List<Daily> dailyList;

    private Storage(){
        dailyList = new ArrayList<Daily>();
    }

    public static Storage getInstance() {
        if(instance==null) instance =  new Storage();
        return instance;
    }

    public  List<Daily> getDailyList(){
        return dailyList;
    }

    public void addDaily(Daily daily){
        dailyList.add(daily);
    }

    public void addEvent(Event event){
        boolean found = false;
        for(Daily daily:dailyList){
            if(isSameDate(daily.getCalendar(),event.getCalendar())){
                daily.addEvent(event);
                found = true;
                break;
            }
        }
        if(!found){
            Daily daily = new Daily(event.getCalendar());
            daily.addEvent(event);
            dailyList.add(daily);
            Collections.sort(dailyList, new Comparator<Daily>() {
                @Override
                public int compare(Daily lhs, Daily rhs) {
                    return lhs.compareTo(rhs);
                }
            });
        }
    }

    public Daily getDaily(Calendar calendar){
        for(Daily daily:dailyList){
            if(isSameDate(daily.getCalendar(),(calendar))){
                return daily;
            }
        }
        return new Daily(calendar);
    }

    public void removeDailyFromCalendar (Calendar calendar){
        for(int i=0;i<dailyList.size();i++){
            if(isSameDate(dailyList.get(i).getCalendar(),(calendar))){
                dailyList.remove(i);
            }
        }
    }

    public boolean isSameDate(Calendar c1,Calendar c2){
        if(c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) return true;
        return false;
    }

    public int getTotalEventNum(){
        int total = 0;
        for(Daily daily : dailyList){
            total += daily.getEvents().size();
        }
        return total;
    }

    public void removeEvent(Calendar calendar,int position){
        getDaily(calendar).getEvents().remove(position);
        if(getDaily(calendar).getEvents().size()==0)removeDailyFromCalendar(calendar);
    }

    public boolean canCreateEvent(Event event){
        Calendar c = event.getCalendar();
        Daily d = getDaily(c);
        for(Event e : d.getEvents()){
            if(event.getStartTime()>=e.getStartTime() && event.getStartTime() <= e.getEndTime()
                    || event.getEndTime() >= e.getStartTime()){
                return false;
            }
        }
        return true;
    }
}
