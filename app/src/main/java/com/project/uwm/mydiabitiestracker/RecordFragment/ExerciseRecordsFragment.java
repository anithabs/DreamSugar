package com.project.uwm.mydiabitiestracker.RecordFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
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

import com.project.uwm.mydiabitiestracker.Adapters.ExerciseAdapter;
import com.project.uwm.mydiabitiestracker.DatabaseManager;
import com.project.uwm.mydiabitiestracker.Objects.ExerciseReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;

import java.util.ArrayList;
import java.util.Calendar;


public class ExerciseRecordsFragment extends Fragment /*implements View.OnClickListener,DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener*/ {
    private BottomSheetDialog bottomSheetDialog;
    ArrayList<ExerciseReadingObject> exerciseList = new ArrayList<>();
    DatabaseManager dbManager;
    private RecyclerView rvExercise;
    private RecyclerView.Adapter eAdaptor;
    private RecyclerView.LayoutManager eLayoutManager;
    private Switch checkSwitch;
    private CheckBox weekChkBox;
    private CheckBox dayChkBox;
    private OnFragmentInteractionListener mListener;
    String userName;
    UserPreference pref;
    EditText editTextFromDate, editTextToDate,editTextFromTime,editTextToTime;
    TextView editTextSearch;
    private static final int        DIALOG_DATE_PICKER  = 100;
    private int                     datePickerInput;
    private int fromDay,toDay;

    private int fromMonth,toMonth;
    private int fromYear, toYear;
    private int fromMinute, toMinute;
    private int fromHour,toHour;
    Calendar fromDate,toDate;

    public ExerciseRecordsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        editTextFromDate =(EditText) getActivity().findViewById(R.id.editTextFromDate);
        editTextToDate =(EditText) getActivity().findViewById(R.id.editTextToDate);
        editTextFromTime =(EditText) getActivity().findViewById(R.id.editTextFromTime);
        editTextToTime =(EditText)getActivity().findViewById(R.id.editTextToTime);
        editTextSearch =(EditText)getActivity().findViewById(R.id.editTextSearchKeyWord);

        View rootView = inflater.inflate(R.layout.fragment_exercise_records, container, false);
        rvExercise = (RecyclerView) rootView.findViewById(R.id.rvExercise);
        rvExercise.setHasFixedSize(true);
        weekChkBox =(CheckBox) getActivity().findViewById(R.id.cbWeek);
        dayChkBox =(CheckBox) getActivity().findViewById(R.id.cbDay);

        eLayoutManager = new LinearLayoutManager(getActivity());
        Context context =getActivity();
        checkSwitch.setChecked(false);
        dbManager = new DatabaseManager(context);
        rvExercise.setLayoutManager(eLayoutManager);
        userName = pref.getUserName();

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
                    dayChkBox.setChecked(false);
                    weekChkBox.setChecked(false);
                    exerciseList = dbManager.selectAllExerciseDetails(userName);
                    eAdaptor = new ExerciseAdapter(getActivity(), exerciseList);
                    rvExercise.setAdapter(eAdaptor);
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
}
