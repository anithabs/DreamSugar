package com.project.uwm.mydiabitiestracker.RecordFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.project.uwm.mydiabitiestracker.Adapters.GlucoseAdapter;
import com.project.uwm.mydiabitiestracker.DatabaseManager;
import com.project.uwm.mydiabitiestracker.Objects.GlucoseReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class GlucoseRecordsFragment extends Fragment /*implements View.OnClickListener,DatePickerDialog.OnDateSetListener*/  {
    //private BottomSheetDialog bottomSheetDialog;
    private OnFragmentInteractionListener gmListener;
    ArrayList<GlucoseReadingObject> glucoseList = new ArrayList<>();
    ArrayList<GlucoseReadingObject> glucoseListTemp = new ArrayList<>();
    ArrayList<GlucoseReadingObject> glucoseListFromDate = new ArrayList<>();
    ArrayList<GlucoseReadingObject> glucoseListToDate = new ArrayList<>();
    ArrayList<GlucoseReadingObject> glucoseListFromTime = new ArrayList<>();
    ArrayList<GlucoseReadingObject> glucoseListToTime = new ArrayList<>();
    ArrayList<GlucoseReadingObject> glucoseListText = new ArrayList<>();

    DatabaseManager dbManager;
    private RecyclerView rvGlucose;
    private RecyclerView.Adapter gAdaptor;
    private RecyclerView.LayoutManager gLayoutManager;

    String userName;
    UserPreference pref;
    public EditText editTextFromDate,editTextToDate,editTextFromTime,editTextToTime, editTextToGL, editTextFromGL;
    private int day;
    private int month;
    private int year, hour,minute;
    TextView editTextSearch;

    private OnFragmentInteractionListener mListener;

    public GlucoseRecordsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.selectRecords);
        spinner.setSelection(1);

        editTextFromDate =(EditText) getActivity().findViewById(R.id.editTextFromDate);
        editTextToDate =(EditText) getActivity().findViewById(R.id.editTextToDate);
        editTextFromTime =(EditText) getActivity().findViewById(R.id.editTextFromTime);
        editTextToTime =(EditText)getActivity().findViewById(R.id.editTextToTime);
        editTextSearch =(EditText)getActivity().findViewById(R.id.editTextSearchKeyWord);


        View rootView = inflater.inflate(R.layout.fragment_glucose_records, container, false);
        rvGlucose = (RecyclerView) rootView.findViewById(R.id.recycleViewGlucose);
        editTextFromGL = (EditText) rootView.findViewById(R.id.editTextFromGlucoseLevel);
        editTextToGL =(EditText) rootView.findViewById(R.id.editTextToGlucoseLevel);
        rvGlucose.setHasFixedSize(true);
        Context context =getActivity();

        dbManager = new DatabaseManager(context);
        userName = pref.getUserName();

        final DatePickerDialog.OnDateSetListener from_dateListener,to_dateListener;
        final TimePickerDialog.OnTimeSetListener from_timeListener,to_timeListener;
        from_timeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hr, int min) {
                hour = hr;
                minute = min ;
                updateDisplayFromTime();
                glucoseList = FromTime(hour,minute);
                gAdaptor = new GlucoseAdapter(getActivity(), glucoseList);
                rvGlucose.setAdapter(gAdaptor);
                editTextToTime.setText("");
            }
        };
        to_timeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hr, int min) {
                hour = hr;
                minute = min ;
                updateDisplayToTime();
                glucoseList = ToTime(hour,minute);
                gAdaptor = new GlucoseAdapter(getActivity(), glucoseList);
                rvGlucose.setAdapter(gAdaptor);
            }
        };
        from_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yr, int mnth, int monthday) {
                year =yr;
                month = mnth+1;
                day = monthday;
                updateFromDisplay();
                glucoseList = FromDate(year,  month,  day);
                gAdaptor = new GlucoseAdapter(getActivity(), glucoseList);
                rvGlucose.setAdapter(gAdaptor);
                editTextToTime.setText("");
                editTextFromTime.setText("");
                editTextToDate.setText("");
            }
        };
        to_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yr, int mnth, int monthday) {
                year =yr;
                month = mnth+1;
                day = monthday;
                updateToDisplay();
                glucoseList = ToDate(year,  month,  day);
                gAdaptor = new GlucoseAdapter(getActivity(), glucoseList);
                rvGlucose.setAdapter(gAdaptor);
                editTextToTime.setText("");
                editTextFromTime.setText("");
            }
        };

        editTextFromDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),from_dateListener,calender.get(calender.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        editTextToDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),to_dateListener,calender.get(calender.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        editTextToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender = Calendar.getInstance(TimeZone.getDefault());
                TimePickerDialog dialog = new TimePickerDialog(getActivity(),to_timeListener,calender.get(calender.HOUR),calender.get(Calendar.MINUTE),true);
                dialog.show();
            }
        });
        editTextFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender = Calendar.getInstance(TimeZone.getDefault());
                TimePickerDialog dialog = new TimePickerDialog(getActivity(),from_timeListener,calender.get(calender.HOUR),calender.get(Calendar.MINUTE),true);
                dialog.show();
            }
        });
        rvGlucose.setLayoutManager(gLayoutManager);
        editTextToGL.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextFromGL.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextSearch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                String  firsthalf =null;
                String secondhalf =null;
                glucoseList.clear();
                glucoseListTemp = dbManager.selectAllGlucoseDetails(userName);
                if(s.toString().contains(" ")) {
                    String[] stringArray = s.toString().split(" ");
                    if(stringArray.length > 2){
                        firsthalf = stringArray[0];
                        secondhalf = stringArray[2];
                    }
                }
                if(s.toString().contains(" and ") && secondhalf !=null  ){
                    for(int i = 0 ; i < glucoseListTemp.size() ; i++)
                        if(glucoseListTemp.get(i).getReading_taken().contains(firsthalf) && glucoseListTemp.get(i).getReading_taken().contains(secondhalf)) {
                            glucoseListText.add(glucoseListTemp.get(i));
                        }
                } else if(s.toString().contains(" or ") && secondhalf !=null  ){
                    for(int i = 0 ; i < glucoseListTemp.size() ; i++)
                        if(glucoseListTemp.get(i).getReading_taken().contains(firsthalf) || glucoseListTemp.get(i).getReading_taken().contains(secondhalf)) {
                            glucoseListText.add(glucoseListTemp.get(i));
                        }
                }else {
                    for(int i = 0 ; i < glucoseListTemp.size() ; i++)
                        if(glucoseListTemp.get(i).getReading_taken().contains(s.toString()) )
                            glucoseListText.add(glucoseListTemp.get(i));
                }
                glucoseList = glucoseListText;
                gAdaptor = new GlucoseAdapter(getActivity(), glucoseList);
                rvGlucose.setAdapter(gAdaptor);

                editTextFromDate.setText("");
                editTextToDate.setText("");
                editTextFromTime.setText("");
                editTextToTime.setText("");

            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    private void updateFromDisplay(){
        if(month <10 && day <10 ) {
            editTextFromDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-0").append(day));
        }else if(month <10){
            editTextFromDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-").append(day));
        }else if(day <10){
            editTextFromDate.setText(new StringBuilder().append(year).append("-").append(month).append("-0").append(day));
        }else
            editTextFromDate.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(day));
    }
    private void updateToDisplay(){
        if(month <10 && day <10 ) {
            editTextToDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-0").append(day));
        }else if(month <10){
            editTextToDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-").append(day));
        }else if(day <10){
            editTextToDate.setText(new StringBuilder().append(year).append("-").append(month).append("-0").append(day));
        }else
            editTextToDate.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(day));
    }
    private void updateDisplayToTime(){
        if(hour <10 && minute <10){
            editTextToTime.setText(new StringBuilder().append("0").append(hour).append(":0").append(minute));
        }else if(hour <10){
            editTextToTime.setText(new StringBuilder().append("0").append(hour).append(":").append(minute));
        }else if(minute <10) {
            editTextToTime.setText(new StringBuilder().append(hour).append(":0").append(minute));
        }else {
            editTextToTime.setText(new StringBuilder().append(hour).append(":").append(minute));
        }
    }
    private void updateDisplayFromTime(){
        if(hour <10 && minute <10){
            editTextFromTime.setText(new StringBuilder().append("0").append(hour).append(":0").append(minute));
        }else if(hour <10){
            editTextFromTime.setText(new StringBuilder().append("0").append(hour).append(":").append(minute));
        }else if(minute <10) {
            editTextFromTime.setText(new StringBuilder().append(hour).append(":0").append(minute));
        }else {
            editTextFromTime.setText(new StringBuilder().append(hour).append(":").append(minute));
        }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    
    public ArrayList<GlucoseReadingObject> FromDate(int year, int month, int date){
        String y, m, d;
        glucoseListFromDate.clear();
        ArrayList<GlucoseReadingObject> FromDate = new ArrayList<>();
        if(glucoseListText.size() !=0 || editTextSearch.getText().toString()!= null){
            FromDate = glucoseListText;
        }else
            FromDate = dbManager.selectAllGlucoseDetails(userName);
        for( int i = 0 ; i < FromDate.size() ; i++){
            String sdate = FromDate.get(i).getGdate();
            String[] dateArray = sdate.split("-");
            y = dateArray[0];
            m = dateArray[1];
            d = dateArray[2];
            if(Integer.parseInt(y) > year) {
                glucoseListFromDate.add(FromDate.get(i));
            }else if(Integer.parseInt(y) >= year && Integer.parseInt(m) > month ){
                glucoseListFromDate.add(FromDate.get(i));
            }else if (Integer.parseInt(y) >= year && Integer.parseInt(m) >= month && Integer.parseInt(d) >= date){
                glucoseListFromDate.add(FromDate.get(i));
            }
        }
        return  glucoseListFromDate;
    }
    public ArrayList<GlucoseReadingObject> ToDate(int year, int month, int date){
        String y, m, d;
        glucoseListToDate.clear();
        ArrayList<GlucoseReadingObject> ToDate = new ArrayList<>();
        if(glucoseListFromDate.size() !=0 ||  editTextFromDate.getText().toString()!= null) {
            ToDate = glucoseListFromDate;
        }else if(glucoseListText.size() !=0 || editTextSearch.getText().toString()!= null){
            ToDate = glucoseListText;
        }else
            ToDate = dbManager.selectAllGlucoseDetails(userName);

        for( int i = 0 ; i < ToDate.size() ; i++){
            String sdate = ToDate.get(i).getGdate();
            String[] dateArray = sdate.split("-");
            y = dateArray[0];
            m = dateArray[1];
            d = dateArray[2];
            if(Integer.parseInt(y) < year) {
                glucoseListToDate.add(ToDate.get(i));
            }else if(Integer.parseInt(y) <= year && Integer.parseInt(m) < month ){
                glucoseListToDate.add(ToDate.get(i));
            }else if (Integer.parseInt(y) <= year && Integer.parseInt(m) <= month && Integer.parseInt(d) <= date){
                glucoseListToDate.add(ToDate.get(i));
            }
        }
        return  glucoseListToDate;
    }
    public ArrayList<GlucoseReadingObject> FromTime(int hour, int min){
        String h, m;
        glucoseListFromTime.clear();
        ArrayList<GlucoseReadingObject> FromTime = new ArrayList<>();
        if(glucoseListToDate.size() !=0 || editTextToDate.getText().toString()!= null){
            FromTime =glucoseListToDate;
        }else if(glucoseListFromDate.size() !=0 ||  editTextFromDate.getText().toString()!= null) {
            FromTime = glucoseListFromDate;
        }else if(glucoseListText.size() !=0 || editTextSearch.getText().toString()!= null){
            FromTime = glucoseListText;
        }else
            FromTime = dbManager.selectAllGlucoseDetails(userName);

        for( int i = 0 ; i < FromTime.size() ; i++){
            String sdate = FromTime.get(i).getGtime();
            String[] dateArray = sdate.split(":");
            h = dateArray[0];
            m = dateArray[1];
            if(Integer.parseInt(h) > hour) {
                glucoseListFromTime.add(FromTime.get(i));
            }else if(Integer.parseInt(h) >= hour && Integer.parseInt(m) >= min ){
                glucoseListFromTime.add(FromTime.get(i));
            }
        }
        return  glucoseListFromTime;
    }
    public ArrayList<GlucoseReadingObject> ToTime(int hour, int min){
        String h, m;
        glucoseListToTime.clear();
        ArrayList<GlucoseReadingObject> ToTime = new ArrayList<>();
        if(glucoseListFromTime.size() !=0 || editTextFromTime.getText().toString()!= null){
            ToTime =glucoseListFromTime;
        } else if(glucoseListToDate.size() !=0 || editTextToDate.getText().toString()!= null){
            ToTime =glucoseListToDate;
        }else if(glucoseListFromDate.size() !=0 ||  editTextFromDate.getText().toString()!= null) {
            ToTime = glucoseListFromDate;
        }else if(glucoseListText.size() !=0 || editTextSearch.getText().toString()!= null){
            ToTime = glucoseListText;
        }else
            ToTime = dbManager.selectAllGlucoseDetails(userName);

        for( int i = 0 ; i < ToTime.size() ; i++){
            String sdate = ToTime.get(i).getGtime();
            String[] dateArray = sdate.split(":");
            h = dateArray[0];
            m = dateArray[1];
            if(Integer.parseInt(h) < hour) {
                glucoseListToTime.add(ToTime.get(i));
            }else if(Integer.parseInt(h) <= hour && Integer.parseInt(m) < min ){
                glucoseListToTime.add(ToTime.get(i));
            }
        }
        return  glucoseListToTime;
    }

}