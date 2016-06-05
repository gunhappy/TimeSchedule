package com.adtproject.timeschedule.Activity.Activity;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adtproject.timeschedule.Activity.Views.OverviewAdapter;
import com.adtproject.timeschedule.Activity.Models.Daily;
import com.adtproject.timeschedule.Activity.Models.Storage;
import com.adtproject.timeschedule.Activity.R;
import com.marshalchen.ultimaterecyclerview.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private OverviewAdapter overviewAdapter;
    private List<Daily> dailyList;


    private OnFragmentInteractionListener mListener;

    public EventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventFragment newInstance(String param1, String param2) {
        EventFragment fragment = new EventFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dailyList = new ArrayList<Daily>();
        recyclerView = (RecyclerView)view.findViewById(R.id.overview_recycler_view);
        overviewAdapter = new OverviewAdapter(dailyList);
        loadDailyList();
        recyclerView.setAdapter(overviewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Daily daily = Storage.getInstance().getDailyList().get(position);
                Calendar c = daily.getCalendar();
                int years = c.get(Calendar.YEAR);
                int months = c.get(Calendar.MONTH);
                int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                Fragment day = new DayFragment();
                MainActivity.dailyFragment = (DayFragment)day;
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
            }
        }));

        initComponents();
    }



    private void initComponents(){
        MainActivity.toolbar.setTitle("Event");
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
           // throw new RuntimeException(context.toString()
             //       + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void loadDailyList(){
        dailyList.clear();
        for(Daily daily: Storage.getInstance().getDailyList()){
            dailyList.add(daily);
        }
        Collections.sort(dailyList, new Comparator<Daily>() {
            @Override
            public int compare(Daily lhs, Daily rhs) {
                return lhs.compareTo(rhs);
            }
        });
        overviewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDailyList();
    }

}
