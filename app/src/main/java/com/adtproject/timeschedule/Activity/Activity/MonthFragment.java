package com.adtproject.timeschedule.Activity.Activity;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adtproject.timeschedule.Activity.Models.CalendarName;
import com.adtproject.timeschedule.Activity.R;
import com.marcohc.robotocalendar.RobotoCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MonthFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MonthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthFragment extends Fragment implements RobotoCalendarView.RobotoCalendarListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //private RobotoCalendarView calendarView;
    private RobotoCalendarView robotoCalendarView;
    private int currentMonthIndex;
    private Calendar currentCalendar;
    private CalendarName parseToName;
    private String month;
    private int month_num;
    private int year_num;


    private OnFragmentInteractionListener mListener;

    public MonthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthFragment newInstance(String param1, String param2) {
        MonthFragment fragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void initComponents(){
        parseToName = new CalendarName();
        month_num = Integer.parseInt(MainActivity.month);
        month = parseToName.getMonthName(month_num);
        year_num = Integer.parseInt(MainActivity.year);
        MainActivity.toolbar.setTitle(month+" "+year_num);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        robotoCalendarView = (RobotoCalendarView )view.findViewById(R.id.calendarView);
        robotoCalendarView.setRobotoCalendarListener(this);
        currentMonthIndex = 0;
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        robotoCalendarView.markDayAsCurrentDay(currentCalendar.getTime());
        initComponents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_month, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
              //      + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDateSelected(Date date) {
        // Mark calendar day
        robotoCalendarView.markDayAsSelectedDay(date);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(date.getTime());
                int years = calendar.get(Calendar.YEAR);
                int months = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                 Log.i("test","y: "+years+"m: "+months+" day:"+dayOfMonth);
                calendar.set(years, months, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                Fragment day = new DayFragment();
                Bundle bundle = new Bundle();
                bundle.putString("year",years+"");
                bundle.putString("month",months+"");
                bundle.putString("day",dayOfMonth+"");
                bundle.putString("week",dayOfWeek+"");
                day.setArguments(bundle);

                MainActivity.day = dayOfMonth+"";
                MainActivity.dayofweek = dayOfWeek+"";
                MainActivity.month = months+"";
                MainActivity.year = years+"";

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,day).commit();


        // Mark that day with random colors
//        final Random random = new Random(System.currentTimeMillis());
//        final int style = random.nextInt(3);
//        switch (style) {
//            case 0:
//                robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.BLUE_COLOR, date);
//                break;
//            case 1:
//                robotoCalendarView.markSecondUnderlineWithStyle(RobotoCalendarView.GREEN_COLOR, date);
//                break;
//            case 2:
//                robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.RED_COLOR, date);
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void onRightButtonClick() {
        currentMonthIndex++;
        month_num++;
        if(month_num>11){
            month_num = 0;
            year_num++;
        }
        updateCalendar();
    }

    @Override
    public void onLeftButtonClick() {
        currentMonthIndex--;
        month_num--;
        if(month_num<0){
            month_num = 11;
            year_num--;
        }
        updateCalendar();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void updateCalendar() {
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        currentCalendar.add(Calendar.MONTH, currentMonthIndex);
        robotoCalendarView.initializeCalendar(currentCalendar);
        month = parseToName.getMonthName(month_num);
        MainActivity.toolbar.setTitle(month+" "+year_num);
    }
}
