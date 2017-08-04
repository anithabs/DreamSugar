package com.project.uwm.mydiabitiestracker.Insertion;


/**
 * Created by Anitha on 7/14/2017.
 */

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
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;
import com.project.uwm.mydiabitiestracker.Objects.FoodConsumedObject;
import com.project.uwm.mydiabitiestracker.VeiwRegimen;

import java.util.Date;

public class FoodInsertActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    AutoCompleteTextView text;
    private ArrayAdapter<String> adapter;
    String userName;
    String[] food
            ={"banana","apple","french fries","corn","eclair","jello", "avocado", "egg",
            "yogurt", "steak", "peanut butter and jelly sandwich", "arugula", "celery", "starfruit", "lemon"};
    public static final String FI = "FoodInsertActivity";
    UserPreference pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        userName = pref.getUserName();
        dbManager = new DatabaseManager(this);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,food);
        text = (AutoCompleteTextView) findViewById(R.id.foodtypevalue);
        // set adapter for the auto complete field
        text.setAdapter(adapter);
        // specify the minimum type of characters before drop-down list is shown
        text.setThreshold(1);
        dbManager = new DatabaseManager(this);
        Date date = new Date();
        EditText datefood = (EditText) findViewById(R.id.date_value_f);
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        datefood.setText(df.format("yyyy-MM-dd",date));
        EditText timefood = (EditText) findViewById(R.id.time_value_f);
        timefood.setText(df.format("hh:mm",date));
    }
    protected void onStart() {
        super.onStart();
        Log.w(FI, "inside FoodInsertActivity:onStart()\n");
    }
    protected void onRestart() {
        super.onRestart();
        Log.v(FI, "inside FoodInsertActivity:onRestart()\n");
    }
    protected void onResume() {
        super.onResume();
        Log.v(FI, "inside FoodInsertActivity:onResume()\n");
    }
    protected void onPause() {
        super.onPause();
        Log.v(FI, "inside FoodInsertActivity:onPause()\n");
    }
    protected void onStop() {
        super.onStop();
        Log.v(FI, "inside FoodInsertActivity:onStop()\n");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.v(FI, "inside FoodInsertActivity:onDestroy()\n");
    }

    public void foodInsertDataBase( View v ) {
        EditText et_foodtypeamount = (EditText) findViewById(R.id.amount_food_value);
        EditText et_foodtype = (EditText) findViewById(R.id.foodtypevalue);
        EditText et_timemeasure = (EditText) findViewById(R.id.time_value_f);
        EditText et_datemeasure = (EditText) findViewById(R.id.date_value_f);
        String timeString = et_timemeasure.getText().toString();
        String dateString = et_datemeasure.getText().toString();

        String foodtypeamount = et_foodtypeamount.getText().toString();
        String foodtype = et_foodtype.getText().toString();


        int amountOfFood = Integer.parseInt(foodtypeamount);

        try{
            FoodConsumedObject fco = new FoodConsumedObject( 0,userName,foodtype, amountOfFood, dateString,timeString );

            dbManager.insertFood(fco);
            Toast.makeText( this, "Details added", Toast.LENGTH_SHORT ).show( );
        } catch ( NumberFormatException nfe ) {
            Toast.makeText( this, "Food Insert error", Toast.LENGTH_LONG ).show( );
        }
        et_foodtypeamount.setText("");
        et_foodtype.setText("");
        et_foodtype.requestFocus();
        dbManager.close();
    }
 /*   void onFoodClicked (View v){
        Intent intent = new Intent(this,FoodActivity.class);
        this.startActivity(intent);
    }*/
    public void goBack(View view){
        this.finish();
    }

    public void goBackFromFood(View view){
        Intent intent = new Intent(this,VeiwRegimen.class);
        startActivity(intent);
    }
}