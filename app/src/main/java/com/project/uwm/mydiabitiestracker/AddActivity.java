/*
package com.project.uwm.mydiabitiestracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.project.uwm.mydiabitiestracker.Alarm.ReminderEditActivity;
import com.project.uwm.mydiabitiestracker.Insertion.ExerciseActivity;
import com.project.uwm.mydiabitiestracker.Insertion.FoodInsertActivity;
import com.project.uwm.mydiabitiestracker.Insertion.GlucoseInsertActivity;
import com.project.uwm.mydiabitiestracker.Insertion.PrescriptionActivity;
import com.project.uwm.mydiabitiestracker.Insertion.RegimenActivity;
import com.project.uwm.mydiabitiestracker.Objects.RegimenReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;

*/
/**
 * Created by Anitha on 7/23/2017.
 *//*

 public class AddActivity extends AppCompatActivity {
    public static final String AA = "AddActivity";
    private RegimenReadingObject ra;
    TextView etTestedBGValue, etExercise,etPresValue,etDietValue ,etDateValue,etTimeValue;
    String userName;
    UserPreference pref;
    private DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_main);
        dbManager = new DatabaseManager(this);
        userName = pref.getUserName();
        etTestedBGValue = (TextView) findViewById(R.id.tested_bgl_value_main);
        etExercise = (TextView) findViewById(R.id.exercise_regimen_value_main);
        etPresValue = (TextView) findViewById(R.id.prescription_regimen_value_main);
        etDietValue = (TextView) findViewById(R.id.diet_regimen_value_main);

        etExercise.setText(pref.getRexerciseField());
        etDietValue.setText(pref.getRdietField());
        etTestedBGValue.setText(pref.getRtestedBGLField());
        etPresValue.setText(pref.getRprescriptionField());
        etTestedBGValue.setFocusable(false);
        etExercise.setFocusable(false);
        etPresValue.setFocusable(false);
        etDietValue.setFocusable(false);
        Log.v(AA, "inside AddActivity:onCreate\n");
    }
    protected void onStart() {
        super.onStart();
        Log.w(AA, "inside AddActivity:onStart()\n");
    }
    protected void onRestart() {
        super.onRestart();
        Log.v(AA, "inside AddActivity:onRestart()\n");
    }
    protected void onResume() {
        super.onResume();
        Log.v(AA, "inside AddActivity:onResume()\n");
    }
    protected void onPause() {
        super.onPause();
        Log.v(AA, "inside AddActivity:onPause()\n");
    }
    protected void onStop() {
        super.onStop();
        Log.v(AA, "inside AddActivity:onStop()\n");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.v(AA, "inside AddActivity:onDestroy()\n");
    }
    public void foodInsert(View v) {
        Intent intent = new Intent(this, FoodInsertActivity.class);
        startActivity(intent);
    }
    public void glucoseInsert(View v){
        Intent intent = new Intent(this, GlucoseInsertActivity.class);
        startActivity(intent);
    }
    public void exerciseInsert(View v){
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }
    public void prescriptionInsert(View v){
        Intent intent = new Intent(this, PrescriptionActivity.class);
        startActivity(intent);
    }
    public void regimenInsert(View v){
       Intent intent = new Intent(this, RegimenActivity.class);
        startActivity(intent);

    }
  */
/*  public void ViewRegimen(View v){
        Intent intent = new Intent(this, VeiwRegimen.class);
        startActivity(intent);
    }*//*

   public void RemindersCheck(View v){
        Intent intent = new Intent(this, ReminderEditActivity.class);
        startActivity(intent);
    }

    public void onClicked(View v){
        this.finish();
    }



}*/
