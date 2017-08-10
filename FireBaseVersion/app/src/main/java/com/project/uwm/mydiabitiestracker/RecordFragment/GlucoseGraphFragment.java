package com.project.uwm.mydiabitiestracker.RecordFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.project.uwm.mydiabitiestracker.DatabaseManager;
import com.project.uwm.mydiabitiestracker.Objects.GlucoseReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;

import java.util.ArrayList;

public class GlucoseGraphFragment extends Fragment {

    LineChart glouseLineChart;
    DatabaseManager dbManager;
    ArrayList<String> dateStringArray;
    ArrayList<Float> date , month, year;
    ArrayList<Integer> glucoseLevel;
    ArrayList<GlucoseReadingObject> glucoseObjectArray;
    UserPreference pref;
    String userName;

    public GlucoseGraphFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        pref = new UserPreference(this.getContext());
        userName = pref.getUserName();
        View  rootView = inflater.inflate(R.layout.fragment_glucose_graph, container, false);
        glouseLineChart = (LineChart) getActivity().findViewById(R.id.foodBarChart);
        dbManager = new DatabaseManager(this.getContext());
        dateStringArray = new ArrayList<String>();
        glucoseLevel = new ArrayList<Integer>();
        glucoseObjectArray = dbManager.selectAllGlucoseDetails(userName);

        for(int i = 0; i < glucoseObjectArray.size(); i++){
            glucoseLevel.add(glucoseObjectArray.get(i).getGlucose_level());
            dateStringArray.add(glucoseObjectArray.get(i).getGdate());
        }

        for(int i = 0; i < dateStringArray.size(); i++ ){
            String[] parts = dateStringArray.get(i).split("-");
            String day = parts[0]; // 004
            String part2 = parts[1]; // 034556
        }


        ArrayList<Entry> xAxisDate = new ArrayList<>();
        ArrayList<Entry> yAxisLevel = new ArrayList<>();

        for(int i = 0; i < glucoseLevel.size();i++){
            yAxisLevel.add( new Entry(i, glucoseLevel.get(i)));
        }
        for (int i = 0;i<date.size();i++){
            xAxisDate.add(new Entry((float)i, date.get(i)));
        }

        
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
