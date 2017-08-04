package com.project.uwm.mydiabitiestracker.Insertion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project.uwm.mydiabitiestracker.DatabaseManager;
import com.project.uwm.mydiabitiestracker.Objects.GlucoseReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;

import java.util.Date;

/**
 * Created by Anitha on 7/15/2017.
 */

public class GlucoseInsertActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    public static final String GI = "GlucoseInsertActivity";
    String userName;
    UserPreference pref;

        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_glucose);
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
            dbManager = new DatabaseManager(this);
            EditText glucoseValue = (EditText) findViewById(R.id.glucose_value);
            EditText readingTaken = (EditText) findViewById(R.id.reading_taken_value);
            EditText dateglu = (EditText) findViewById(R.id.date_value);
            EditText timeglu = (EditText) findViewById(R.id.time_value);
            String timeString = timeglu.getText().toString();
            String dateString = dateglu.getText().toString();
            String sReadingTaken = readingTaken.getText().toString();
            int iGlucoseValue = Integer.parseInt(glucoseValue.getText().toString());

            try{
                GlucoseReadingObject gco = new GlucoseReadingObject( 0,userName,iGlucoseValue,sReadingTaken,dateString,timeString );
                dbManager.insertGlucose(gco);
                Toast.makeText( this, "Details added", Toast.LENGTH_SHORT ).show( );
            } catch ( NumberFormatException nfe ) {
                Toast.makeText( this, "Food Insert error", Toast.LENGTH_LONG ).show( );
            }
            glucoseValue.setText("");
            readingTaken.setText("");
            dbManager.close();
        }
    public void goBack(View view){
        this.finish();
    }



    }

