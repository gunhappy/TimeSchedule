package com.adtproject.timeschedule.Activity.Activity;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.adtproject.timeschedule.Activity.Models.CalendarName;
import com.adtproject.timeschedule.Activity.Models.Daily;
import com.adtproject.timeschedule.Activity.Models.Event;
import com.adtproject.timeschedule.Activity.Models.Storage;
import com.adtproject.timeschedule.Activity.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateEventActivity extends AppCompatActivity {

    private Button selectDateBtn,selectTimeBtn;
    private SimpleDateFormat dateFormatter;
    private Calendar calendar;
    private int day=0,month=0,year=0,dayofweek=0;
    private int hour=0,minute=0;
    private String duration="";
    private String title;
    private EditText editText;
    private Spinner durationSpinner;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        day = Integer.parseInt(MainActivity.day);
        month = Integer.parseInt(MainActivity.month);
        year = Integer.parseInt(MainActivity.year);
        dayofweek = Integer.parseInt(MainActivity.dayofweek);
        initComponents();
    }

    private void initComponents() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(year,month,day);
                title = editText.getText().toString();
                duration = durationSpinner.getSelectedItem().toString();
                int duration_val = Integer.parseInt(duration.charAt(0)+"");
                Event event = new Event(calendar,hour,minute,duration_val,title);
                Storage.getInstance().addEvent(event);
                finish();
            }
        });
        this.setTitle("Create New Event");
        calendar = Calendar.getInstance();

        editText = (EditText)findViewById(R.id.event_title_editText);

        selectDateBtn = (Button) findViewById(R.id.selectDateBtn);
        selectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               DateDialog();

            }
        });


        setDateText();

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        selectTimeBtn = (Button)findViewById(R.id.selectTimeBtn);
        selectTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeDialog();
            }
        });
        hour = today.hour;
        minute = today.minute;
        setTimeText();

        durationSpinner = (Spinner)findViewById(R.id.duration_spinner);
    }

    public void setDateText(){
        CalendarName cdn = new CalendarName();
        String month_name = cdn.getMonthName(month);
        selectDateBtn.setText(cdn.getDayName(dayofweek)+" "+day+" "+month_name+" "+year);
    }

    public void setTimeText(){
        String hr = hour+"";
        String min = minute+"";
        if(hour<10) hr = "0"+hour;
        if(minute<10) min = "0"+minute;
        selectTimeBtn.setText(hr+" : "+min);
    }

    private void TimeDialog() {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
                hour = hourOfDay;
                minute = minutes;
                setTimeText();
            }
        };
        TimePickerDialog tpDialog = new TimePickerDialog(this,listener,hour,minute,true);
        tpDialog.show();
        Log.i("Time",hour+":"+minute);
    }

    private void DateDialog(){

        OnDateSetListener listener=new OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int years, int monthOfYear,int dayOfMonth)
            {

                day = dayOfMonth;
                month = monthOfYear;
                year = years;
                Calendar c = Calendar.getInstance();
                c.set(year,month,day);
                dayofweek = c.get(Calendar.DAY_OF_WEEK);
                setDateText();
            }};

        DatePickerDialog dpDialog=new DatePickerDialog(this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dpDialog.show();
    }

}
