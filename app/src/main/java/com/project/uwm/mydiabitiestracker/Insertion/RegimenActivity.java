package com.project.uwm.mydiabitiestracker.Insertion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project.uwm.mydiabitiestracker.DatabaseManager;
import com.project.uwm.mydiabitiestracker.Objects.RegimenReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;

import java.util.Date;

/**
 * Created by Anitha on 7/30/2017.
 */

public class RegimenActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    EditText etTestedBGValue;
    EditText etExercise;
    EditText etPresValue;
    EditText etDietValue;
    EditText etDateValue;
    EditText etTimeValue;
    String userName;
    UserPreference pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regimen);
        dbManager = new DatabaseManager(this);
        userName = pref.getUserName();
        Date date = new Date();
        etDateValue = (EditText) findViewById(R.id.date_regimen_value);
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        etDateValue.setText(df.format("yyyy-MM-dd",date));
        etTimeValue = (EditText) findViewById(R.id.time_regimen_value);
        etTimeValue.setText(df.format("hh:mm",date));
    }
    public void regimenInsert(View view){
        etTestedBGValue = (EditText)findViewById(R.id.tested_bgl_value);
        etExercise = (EditText) findViewById(R.id.exercise_regimen_value);
        etPresValue = (EditText) findViewById(R.id.prescription_regimen_value);
        etDietValue = (EditText) findViewById(R.id.diet_regimen_value);
        etDateValue = (EditText)findViewById(R.id.date_regimen_value);
        etTimeValue = (EditText) findViewById(R.id.time_regimen_value);

        String sTestedBGValue = etTestedBGValue.getText().toString();
        String sExercise = etExercise.getText().toString();
        String sPresValue = etPresValue.getText().toString();
        String sDietValue = etDietValue.getText().toString();
        String sDateValue = etDateValue.getText().toString();
        String sTimeValue = etTimeValue.getText().toString();

        RegimenReadingObject rro = new RegimenReadingObject(0,userName,sTestedBGValue,sExercise,sPresValue,sDietValue,sDateValue,sTimeValue);
        try{
            dbManager.insertRegime(rro);
            Toast.makeText( this, "Details added", Toast.LENGTH_SHORT ).show( );
        } catch ( NumberFormatException nfe ) {
            Toast.makeText( this, "Regime Insert error", Toast.LENGTH_LONG ).show( );
        }

    }
    public void goBack(View view){
        this.finish();
    }
}
