package com.adtproject.timeschedule.Activity.Models;

import java.util.Calendar;

/**
 * Created by พศิน on 19/4/2559.
 */
public class CalendarName {

    public String getDayName(int dayOfWeek){
        if (Calendar.MONDAY == dayOfWeek) return "Monday";
        else if (Calendar.TUESDAY == dayOfWeek) return "Tuesday";
        else if (Calendar.WEDNESDAY == dayOfWeek) return "Wednesday";
        else if (Calendar.THURSDAY == dayOfWeek) return "Thursday";
        else if (Calendar.FRIDAY == dayOfWeek) return "Friday";
        else if (Calendar.SATURDAY == dayOfWeek) return "Saturday";
        else if (Calendar.SUNDAY == dayOfWeek) return "Sunday";
        return "";
    }

    public String getMonthName(int month){
        if(month==0)return "January";
        else if(month==1)return "February";
        else if(month==2)return "March";
        else if(month==3)return "April";
        else if(month==4)return "Mar";
        else if(month==5)return "June";
        else if(month==6)return "July";
        else if(month==7)return "August";
        else if(month==8)return "September";
        else if(month==9)return "October";
        else if(month==10)return "November";
        else if(month==11)return "December";
        return "";
    }
}
