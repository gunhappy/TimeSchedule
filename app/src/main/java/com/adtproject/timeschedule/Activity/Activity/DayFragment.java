package com.adtproject.timeschedule.Activity.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adtproject.timeschedule.Activity.Views.EventAdapter;
import com.adtproject.timeschedule.Activity.Models.CalendarName;
import com.adtproject.timeschedule.Activity.Models.Event;
import com.adtproject.timeschedule.Activity.Models.Storage;
import com.adtproject.timeschedule.Activity.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String day;
    private String dayofweek;
    private String month;
    private String year;
    private int day_num,month_num,year_num;
    private TextView title;
    private Calendar calendar;
    private RecyclerView recyclerView;
    private List<Event> eventList;
    private EventAdapter eventAdapter;

    private OnFragmentInteractionListener mListener;

    public DayFragment() {
        // Required empty public constructor
    }

    public static DayFragment newInstance(String param1, String param2) {
        DayFragment fragment = new DayFragment();
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
        CalendarName parseToName = new CalendarName();
        dayofweek = parseToName.getDayName(Integer.parseInt(dayofweek));
        month = parseToName.getMonthName(Integer.parseInt(month));
        MainActivity.toolbar.setTitle(month+" "+year);
        title.setText(day+" "+dayofweek);
        MainActivity.navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = (TextView)view.findViewById(R.id.fragment_day_title);
        year_num = Integer.parseInt(year);
        month_num = Integer.parseInt(month);
        day_num = Integer.parseInt(day);
        calendar = Calendar.getInstance();
        calendar.set(year_num,month_num,day_num);
        eventList = new ArrayList<Event>();
        recyclerView = (RecyclerView) view.findViewById(R.id.event_recycler_view);
        eventAdapter = new EventAdapter(eventList,calendar);
        loadEvent();
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initComponents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        savedInstanceState = this.getArguments();
        day = savedInstanceState.getString("day");
        month = savedInstanceState.getString("month");
        year = savedInstanceState.getString("year");
        dayofweek = savedInstanceState.getString("week");
        return inflater.inflate(R.layout.fragment_day, container, false);
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

    public void loadEvent(){
        eventList.clear();
        for(Event event: Storage.getInstance().getDaily(calendar).getEvents()){
            eventList.add(event);
        }
        eventAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadEvent();
    }

}
