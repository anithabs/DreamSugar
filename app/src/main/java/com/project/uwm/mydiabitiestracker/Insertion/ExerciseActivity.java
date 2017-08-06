package com.project.uwm.mydiabitiestracker.Insertion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project.uwm.mydiabitiestracker.DatabaseManager;
import com.project.uwm.mydiabitiestracker.Objects.ExerciseReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;
import com.project.uwm.mydiabitiestracker.VeiwRegimen;

import java.util.Date;

/**
 * Created by Ron on 7-23-2017
 */

public class ExerciseActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    private static final String EXERCISE_TYPE_KEY ="exerciseType";
    private static final String DURATION_KEY = "duration";
    public static final String EA = "ExerciseActivity";
    String userName;
    UserPreference pref;
    EditText exTime, exDate, exType, exDuration;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("Log Exercise");
        pref = new UserPreference(this);
        setContentView(R.layout.activity_exercise);
        userName = pref.getUserName();
        exType = (EditText) findViewById(R.id.exercise_type_value);
        exDuration= (EditText) findViewById(R.id.exercise_duration_value);

        exType.setText(pref.getExerciseField());
        exDuration.setText(pref.getDurationField());

        Date date = new Date();
        exDate = (EditText) findViewById(R.id.exercise_date_value);
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        exDate.setText(df.format("yyyy-MM-dd",date));
        exTime = (EditText) findViewById(R.id.exercise_start_time_value);
        exTime.setText(df.format("hh:mm",date));
    }
    protected void onStart() {
        super.onStart();
        Log.w(EA, "inside ExerciseActivity:onStart()\n");
    }
    protected void onRestart() {
        super.onRestart();
        Log.v(EA, "inside ExerciseActivity:onRestart()\n");
    }
    protected void onResume() {
        super.onResume();
        Log.v(EA, "inside ExerciseActivity:onResume()\n");
    }
    protected void onPause() {
        super.onPause();
        Log.v(EA, "inside ExerciseActivity:onPause()\n");
    }
    protected void onStop() {
        super.onStop();
        Log.v(EA, "inside ExerciseActivity:onStop()\n");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.v(EA, "inside ExerciseActivity:onDestroy()\n");
    }
    
    public void exerciseInsert(View v) {
        dbManager = new DatabaseManager(this);
        exType = (EditText) findViewById(R.id.exercise_type_value);
        exDuration= (EditText) findViewById(R.id.exercise_duration_value);
        exDate = (EditText) findViewById(R.id.exercise_date_value);
        exTime = (EditText) findViewById(R.id.exercise_start_time_value);
        String timeString = exTime.getText().toString();
        String dateString = exDate.getText().toString();
        String typeOfExerciseString = exType.getText().toString();
        int minutesOfExerciseInt = Integer.parseInt(exDuration.getText().toString());

        try{
            ExerciseReadingObject eco = new ExerciseReadingObject(
                    0,userName,
                    typeOfExerciseString,
                    minutesOfExerciseInt,
                    dateString,
                    timeString );
            dbManager.insertExercise(eco);
            Toast.makeText( this, "Details added", Toast.LENGTH_SHORT ).show( );
        } catch ( NumberFormatException nfe ) {
            Toast.makeText( this, "Food Insert error", Toast.LENGTH_LONG ).show( );
        }
        exType.setText("");
        exDuration.setText("");
        exType.requestFocus();
        dbManager.close();
        pref.setExerciseField(typeOfExerciseString);
        pref.setDurationField(exDuration.getText().toString());
        pref.setPreference(this);

    }
    public void exerciseToRegimen(View view){
        Intent intent = new Intent(this,VeiwRegimen.class);
        startActivity(intent);
    }
    public void goBack(View view){
        this.finish();
    }

}