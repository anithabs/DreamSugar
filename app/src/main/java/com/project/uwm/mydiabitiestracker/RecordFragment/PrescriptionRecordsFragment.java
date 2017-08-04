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

import com.project.uwm.mydiabitiestracker.Adapters.PrescriptionAdaptor;
import com.project.uwm.mydiabitiestracker.DatabaseManager;
import com.project.uwm.mydiabitiestracker.Objects.PrescriptionReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;

import java.util.ArrayList;


public class PrescriptionRecordsFragment extends Fragment /*implements View.OnClickListener,DatePickerDialog.OnDateSetListener*/ {
    private GlucoseRecordsFragment.OnFragmentInteractionListener gmListener;
    ArrayList<PrescriptionReadingObject> presList = new ArrayList<>();
    DatabaseManager dbManager;
    private RecyclerView rvPres;
    private RecyclerView.Adapter pAdaptor;
    private RecyclerView.LayoutManager pLayoutManager;
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

    public PrescriptionRecordsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /* editTextFromDate =(EditText) getActivity().findViewById(R.id.editTextFromDate);
        this.editTextFromDate.setOnClickListener(this);
        editTextToDate =(EditText) getActivity().findViewById(R.id.editTextToDate);
        this.editTextToDate.setOnClickListener(this);*/
        View rootView= inflater.inflate(R.layout.fragment_prescription_records, container, false);

        rvPres = (RecyclerView) rootView.findViewById(R.id.rvprescription);
        rvPres.setHasFixedSize(true);
        editTextSearch =(EditText)getActivity().findViewById(R.id.editTextSearchKeyWord);
        weekChkBox =(CheckBox) getActivity().findViewById(R.id.cbWeek);
        dayChkBox =(CheckBox) getActivity().findViewById(R.id.cbDay);
        userName = pref.getUserName();

        pLayoutManager = new LinearLayoutManager(getActivity());
        Context context =getActivity();
        dbManager = new DatabaseManager(context);
        rvPres.setLayoutManager(pLayoutManager);

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
                    presList= dbManager.selectAllPrescriptionDetails(userName);
                    pAdaptor= new PrescriptionAdaptor(getActivity(), presList);
                    rvPres.setAdapter(pAdaptor);
                }else{
                    weekChkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(view.isEnabled()) {
                                dayChkBox.setChecked(false);
                                presList= dbManager.selectWeekPrescriptionRecord(userName);
                                pAdaptor= new PrescriptionAdaptor(getActivity(), presList);
                                rvPres.setAdapter(pAdaptor);
                            }
                        }
                    });
                    dayChkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(view.isEnabled()) {
                                weekChkBox.setChecked(false);
                                presList= dbManager.selectOneDayPrescriptionRecord(userName);
                                pAdaptor= new PrescriptionAdaptor(getActivity(), presList);
                                rvPres.setAdapter(pAdaptor);
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
        } /*else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

/*    public void onDateSet(DatePicker datePicker, int yr, int mnth, int monthday) {
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
