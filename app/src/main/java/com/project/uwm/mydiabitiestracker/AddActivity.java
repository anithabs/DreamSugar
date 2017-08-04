package com.project.uwm.mydiabitiestracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.project.uwm.mydiabitiestracker.Insertion.ExerciseActivity;
import com.project.uwm.mydiabitiestracker.Insertion.FoodInsertActivity;
import com.project.uwm.mydiabitiestracker.Insertion.GlucoseInsertActivity;
import com.project.uwm.mydiabitiestracker.Insertion.PrescriptionActivity;
import com.project.uwm.mydiabitiestracker.Insertion.RegimenActivity;

/**
 * Created by Anitha on 7/23/2017.
 */
 public class AddActivity extends AppCompatActivity {
    public static final String AA = "AddActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_main);
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
    public void ViewRegimen(View v){
        Intent intent = new Intent(this, VeiwRegimen.class);
        startActivity(intent);
    }
  /*  public void RemindersCheck(View v){
        Intent intent = new Intent(this, ReminderActivity.class);
        startActivity(intent);
    }*/

    public void onClicked(View v){
        this.finish();
    }



}