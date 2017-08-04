package com.project.uwm.mydiabitiestracker.RecordFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.project.uwm.mydiabitiestracker.Adapters.GlucoseAdapter;
import com.project.uwm.mydiabitiestracker.DatabaseManager;
import com.project.uwm.mydiabitiestracker.Objects.GlucoseReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;

import java.util.ArrayList;

public class GlucoseRecordsFragment extends Fragment /*implements View.OnClickListener,DatePickerDialog.OnDateSetListener*/  {
    //private BottomSheetDialog bottomSheetDialog;
    private OnFragmentInteractionListener gmListener;
    ArrayList<GlucoseReadingObject> glucoseList = new ArrayList<>();
    DatabaseManager dbManager;
    private RecyclerView rvGlucose;
    private RecyclerView.Adapter gAdaptor;
    private RecyclerView.LayoutManager gLayoutManager;
    private Switch checkSwitch;
    private CheckBox weekChkBox;
    private CheckBox dayChkBox;
    String userName;
    UserPreference pref;
    EditText editTextFromDate,editTextToDate;
    private int day;
    private int month;
    private int year;
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
      /*  editTextFromDate =(EditText) getActivity().findViewById(R.id.editTextFromDate);
        this.editTextFromDate.setOnClickListener(this);
        editTextToDate =(EditText) getActivity().findViewById(R.id.editTextToDate);
        this.editTextToDate.setOnClickListener(this);*/
        View rootView = inflater.inflate(R.layout.fragment_glucose_records, container, false);
        rvGlucose = (RecyclerView) rootView.findViewById(R.id.recycleViewGlucose);
        editTextSearch =(EditText)getActivity().findViewById(R.id.editTextSearchKeyWord);
        rvGlucose.setHasFixedSize(true);

        userName = pref.getUserName();

        gLayoutManager = new LinearLayoutManager(getActivity());
        Context context =getActivity();
        dbManager = new DatabaseManager(context);
        rvGlucose.setLayoutManager(gLayoutManager);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                //Field2.setText("");
            }
        });
        checkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton button,boolean isChecked){
                if(isChecked){
                    glucoseList= dbManager.selectAllGlucoseDetails(userName);
                    gAdaptor= new GlucoseAdapter(getActivity(), glucoseList);
                    rvGlucose.setAdapter(gAdaptor);
                }else{
                    weekChkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(view.isEnabled()) {
                                dayChkBox.setChecked(false);
                                glucoseList= dbManager.selectWeekGlucoseDetails(userName);
                                gAdaptor= new GlucoseAdapter(getActivity(), glucoseList);
                                rvGlucose.setAdapter(gAdaptor);
                            }
                        }
                    });
                    dayChkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(view.isEnabled()) {
                                weekChkBox.setChecked(false);
                                glucoseList= dbManager.selectOneGlucoseDetails(userName);
                                gAdaptor= new GlucoseAdapter(getActivity(), glucoseList);
                                rvGlucose.setAdapter(gAdaptor);
                            }
                        }
                    });
                }
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
/*
    public void onDateSet(DatePicker datePicker, int yr, int mnth, int monthday) {
        year =yr;
        month = mnth;
        day = monthday;
        updateDisplay();
    }
    private void updateDisplay(){
        editTextFromDate.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(day));
        editTextToDate.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(day));
    }

    @Override
    public void onClick(View view) {
        Calendar calender = Calendar.getInstance(TimeZone.getDefault());
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),this,calender.get(calender.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }*/

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}