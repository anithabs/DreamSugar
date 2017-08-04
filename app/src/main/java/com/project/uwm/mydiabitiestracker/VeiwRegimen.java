package com.project.uwm.mydiabitiestracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.project.uwm.mydiabitiestracker.Objects.RegimenReadingObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;

public class VeiwRegimen extends AppCompatActivity {
    private DatabaseManager dbManager;
    private RegimenReadingObject ra;
    EditText etDoctor;
    EditText etTestedBGValue;
    EditText etTargetedBGValue;
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
        setContentView(R.layout.activity_veiw_regimen);
        dbManager = new DatabaseManager(this);
        userName = pref.getUserName();
        etTestedBGValue = (EditText)findViewById(R.id.tested_bgl_value_view);
        etTargetedBGValue= (EditText)findViewById(R.id.target_bgl_value_view);
        etExercise = (EditText)findViewById(R.id.exercise_regimen_value_view);
        etPresValue = (EditText)findViewById(R.id.prescription_regimen_value_view);
        etDietValue = (EditText)findViewById(R.id.diet_regimen_value_view);
        etDateValue = (EditText)findViewById(R.id.date_regimen_value_view);
        etTimeValue = (EditText)findViewById(R.id.regimen_value_time_view);

        ra = dbManager.selectRegimen(userName);
        etDoctor = (EditText)findViewById(R.id.regimen_doctor_value_view);

        etTestedBGValue.setText(ra.getTested());
        etExercise.setText(ra.getExercise());
        etPresValue.setText(ra.getMeds());
        etDietValue.setText(ra.getDiet());
        etDateValue.setText(ra.getDate());
        etTimeValue.setText(ra.getTime());

        etDoctor.setFocusable(false);
        etTestedBGValue.setFocusable(false);
        etTargetedBGValue.setFocusable(false);
        etExercise.setFocusable(false);
        etPresValue.setFocusable(false);
        etDietValue.setFocusable(false);
        etDateValue.setFocusable(false);
        etTimeValue.setFocusable(false);
    }

   public void goBack(View view){
       this.finish();
   }
}
