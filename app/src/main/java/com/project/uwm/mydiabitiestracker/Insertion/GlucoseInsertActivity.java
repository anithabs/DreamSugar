package com.project.uwm.mydiabitiestracker.Insertion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.uwm.mydiabitiestracker.DatabaseManager;
import com.project.uwm.mydiabitiestracker.Objects.GlucoseReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;

import java.util.Date;

/**
 * Created by Anitha on 7/15/2017.
 */

public class GlucoseInsertActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private DatabaseManager dbManager;
    public static final String GI = "GlucoseInsertActivity";
    String userName;
    int min = 60;
    int max = 450;
    int step = 1;
    UserPreference pref;
    Spinner menuSpinner;

        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setTitle("Log Glucose");
            pref = new UserPreference(this);
            setContentView(R.layout.activity_glucose);
            SeekBar simpleSeekBar = (SeekBar) findViewById(R.id.glucoseStartSeek); // initiate the Seek bar
            simpleSeekBar.setMax(max );
            int maxValue=simpleSeekBar.getMax();
            int seekBarValue= simpleSeekBar.getProgress();
            final EditText glucoseVlue =(EditText) findViewById(R.id.glucose_value) ;

            this.menuSpinner = (Spinner) findViewById(R.id.reading_taken_value);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.menu_reading, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            menuSpinner.setAdapter(adapter);
            menuSpinner.setOnItemSelectedListener(this);

            simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progressChangedValue = 60;

                public void onProgressChanged(SeekBar seekBar,int progress, boolean fromUser) {
                    if (progress > min){
                        progressChangedValue = progress;
                        glucoseVlue.setText(Integer.toString(progressChangedValue));
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    /*Toast.makeText(GlucoseInsertActivity.this, "Seek bar progress is :" + progressChangedValue,
                            Toast.LENGTH_SHORT).show();*/
                }
            });

            userName = pref.getUserName();
            Date date = new Date();
            EditText dateglu = (EditText) findViewById(R.id.date_value);
            android.text.format.DateFormat df = new android.text.format.DateFormat();
            dateglu.setText(df.format("yyyy-MM-dd",date));
            EditText timeglu = (EditText) findViewById(R.id.time_value);
            timeglu.setText(df.format("hh:mm",date));
        }
    protected void onStart() {
        super.onStart();
        Log.w(GI, "inside GlucoseInsertActivity:onStart()\n");
    }
    protected void onRestart() {
        super.onRestart();
        Log.v(GI, "inside GlucoseInsertActivity:onRestart()\n");
    }
    protected void onResume() {
        super.onResume();
        Log.v(GI, "inside GlucoseInsertActivity:onResume()\n");
    }
    protected void onPause() {
        super.onPause();
        Log.v(GI, "inside GlucoseInsertActivity:onPause()\n");
    }
    protected void onStop() {
        super.onStop();
        Log.v(GI, "inside GlucoseInsertActivity:onStop()\n");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.v(GI, "inside GlucoseInsertActivity:onDestroy()\n");
    }
    public void glucoseInsert(View v) {
        EditText glucoseValue = (EditText) findViewById(R.id.glucose_value);
        this.menuSpinner = (Spinner) findViewById(R.id.reading_taken_value);
        String mySpinnervalue =menuSpinner.getSelectedItem().toString();
        EditText dateglu = (EditText) findViewById(R.id.date_value);
        EditText timeglu = (EditText) findViewById(R.id.time_value);
        dbManager = new DatabaseManager(this);

        String timeString = timeglu.getText().toString();
        String dateString = dateglu.getText().toString();
        String glucoseValues = glucoseValue.getText().toString();
        String sReadingTaken =mySpinnervalue;
        int iGlucoseValue = Integer.parseInt(glucoseValues);
        pref.setGlucoseLevelField(glucoseValues);
        pref.setReadingTakenField(sReadingTaken);
        pref.setPreference(this);

        try{
            GlucoseReadingObject gco = new GlucoseReadingObject( 0,userName,iGlucoseValue,sReadingTaken,dateString,timeString );
            dbManager.insertGlucose(gco);
            Toast.makeText( this, "Details added", Toast.LENGTH_SHORT ).show( );
        } catch ( NumberFormatException nfe ) {
            Toast.makeText( this, "Food Insert error", Toast.LENGTH_LONG ).show( );
        }
        glucoseValue.setText("");
        //readingTaken.setText("");
        dbManager.close();
    }
    public void goBack(View view){
        this.finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

