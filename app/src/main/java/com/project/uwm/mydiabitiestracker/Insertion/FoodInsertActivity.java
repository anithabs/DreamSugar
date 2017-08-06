package com.project.uwm.mydiabitiestracker.Insertion;
/**
 * Created by Anitha on 7/14/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.uwm.mydiabitiestracker.DatabaseManager;
import com.project.uwm.mydiabitiestracker.Objects.FoodConsumedObject;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;
import com.project.uwm.mydiabitiestracker.R;

import java.util.Date;

public class FoodInsertActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private DatabaseManager dbManager;
    EditText aofText,tofText ,dateFood, timeFood;
    private ArrayAdapter<String> adapter;
    String userName;
    Context context;

    public static final String FI = "FoodInsertActivity";
    UserPreference pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = new UserPreference(this);

        setTitle("Add Food");
        setContentView(R.layout.activity_food);


       /* if ((savedInstanceState != null)
                && (savedInstanceState.getSerializable("aof") != null)) {
                 aofText = (EditText) savedInstanceState
                         .getSerializable("aof");
             }
        if ((savedInstanceState != null)
                && (savedInstanceState.getSerializable("tof") != null)) {
                 tofText = (EditText) savedInstanceState
                         .getSerializable("tof");
             }*/
        userName = pref.getUserName();

        aofText = (EditText) findViewById(R.id.amount_food_value);
        tofText = (EditText) findViewById(R.id.foodtypevalue);
        aofText.setText(pref.getAmountOfFoodField());
        tofText.setText(pref.getTypeoffoodField());

        Date date = new Date();
        dateFood = (EditText) findViewById(R.id.date_value_f);
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        dateFood.setText(df.format("yyyy-MM-dd",date));
        timeFood = (EditText) findViewById(R.id.time_value_f);
        timeFood.setText(df.format("hh:mm",date));
        aofText.requestFocus();
        aofText.setOnEditorActionListener(this);

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.w(FI, "inside FoodInsertActivity:onStart()\n");
    }
    @Override
    public void onRestart() {
        super.onRestart();
        Log.v(FI, "inside FoodInsertActivity:onRestart()\n");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.v(FI, "inside FoodInsertActivity:onResume()\n");
    }
    @Override
    public void onPause() {

        super.onPause();
        Log.v(FI, "inside FoodInsertActivity:onPause()\n");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.v(FI, "inside FoodInsertActivity:onStop()\n");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(FI, "inside FoodInsertActivity:onDestroy()\n");

    }

   /* @Override
    public	void onRestoreInstanceState(Bundle savedInstanceState)	{
        super.onRestoreInstanceState(savedInstanceState);
        aofText = (EditText) savedInstanceState.getSerializable("aof");
        tofText = (EditText) savedInstanceState.getSerializable("tof");
    }
    @Override
    public	void onSaveInstanceState(Bundle savedInstanceState)	{
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("aof", aofText.getText().toString());
        savedInstanceState.putSerializable("tof", tofText.getText().toString());
    }*/

    public void foodInsertDataBase( View v ) {
        context = v.getContext();
        aofText = (EditText) findViewById(R.id.amount_food_value);
        tofText = (EditText) findViewById(R.id.foodtypevalue);
        timeFood = (EditText) findViewById(R.id.time_value_f);
        dateFood = (EditText) findViewById(R.id.date_value_f);

        String timeString = timeFood.getText().toString();
        String dateString = dateFood.getText().toString();

        String foodtypeamount = aofText.getText().toString();
        String foodtype = tofText.getText().toString();

        int amountOfFood = Integer.parseInt(foodtypeamount);
        pref.setAmountOfFoodField(foodtypeamount);
        pref.setTypeoffoodField(foodtype);
        pref.setPreference(this);

        dbManager = new DatabaseManager(this);
        try{
            FoodConsumedObject fco = new FoodConsumedObject( 0,userName,foodtype, amountOfFood, dateString,timeString );

            dbManager.insertFood(fco);
            Toast.makeText( this, "Details added", Toast.LENGTH_SHORT ).show( );
        } catch ( NumberFormatException nfe ) {
            Toast.makeText( this, "Food Insert error", Toast.LENGTH_LONG ).show( );
        }
        aofText.setText("");
        tofText.setText("");
        aofText.requestFocus();
        dbManager.close();

    }
 /*   void onFoodClicked (View v){
        Intent intent = new Intent(this,FoodActivity.class);
        this.startActivity(intent);
    }*/
    public void goBack(View view){
        this.finish();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if(i== EditorInfo.IME_ACTION_DONE){
            return  true;
        }
        return false;
    }
}