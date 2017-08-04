package com.project.uwm.mydiabitiestracker.Insertion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    AutoCompleteTextView text;
    private ArrayAdapter<String> adapter;
    String[] exercises
            ={"Super Squats","Archery","Gymnastics","MMA","Ballet","Triathlon","Wrestling", "Boxing", "Crossfit",
            "Powerlifting", "Football", "Hurdles", "Rock Climbing", "Pole Vaulting", "Walking", "Running"};
    public static final String EA = "ExerciseActivity";
    String userName;
    UserPreference pref;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        userName = pref.getUserName();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,exercises);
        text = (AutoCompleteTextView) findViewById(R.id.exercise_type_value);
        // set adapter for the auto complete field
        text.setAdapter(adapter);
        // specify the minimum type of characters before drop-down list is shown
        text.setThreshold(1);

        Date date = new Date();
        EditText dateOfExercise = (EditText) findViewById(R.id.exercise_date_value);
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        dateOfExercise.setText(df.format("yyyy-MM-dd",date));
        EditText startTimeOfExercise = (EditText) findViewById(R.id.exercise_start_time_value);
        startTimeOfExercise.setText(df.format("hh:mm",date));
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
        EditText typeOfExercise = (EditText) findViewById(R.id.exercise_type_value);
        EditText minutesOfExercise = (EditText) findViewById(R.id.exercise_duration_value);
        EditText dateOfExercise = (EditText) findViewById(R.id.exercise_date_value);
        EditText startTimeOfExercise = (EditText) findViewById(R.id.exercise_start_time_value);
        String timeString = startTimeOfExercise.getText().toString();
        String dateString = dateOfExercise.getText().toString();
        String typeOfExerciseString = typeOfExercise.getText().toString();
        int minutesOfExerciseInt = Integer.parseInt(minutesOfExercise.getText().toString());

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
        typeOfExercise.setText("");
        minutesOfExercise.setText("");
        typeOfExercise.requestFocus();
        dbManager.close();

    }
    public void exerciseToRegimen(View view){
        Intent intent = new Intent(this,VeiwRegimen.class);
        startActivity(intent);
    }
    public void goBack(View view){
        this.finish();
    }

}