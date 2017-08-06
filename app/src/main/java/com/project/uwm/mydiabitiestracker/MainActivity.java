package com.project.uwm.mydiabitiestracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.project.uwm.mydiabitiestracker.Alarm.ReminderListActivity;
import com.project.uwm.mydiabitiestracker.Objects.UserPreference;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String MA = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyLogin();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void verifyLogin() {
        UserPreference pref = new UserPreference(this);
        String userName,password;
        userName = pref.getUserName();
        password = pref.getPassword();



        DatabaseManager dbManager = new DatabaseManager(this);

        int statusUser = dbManager.verifyLogin(userName,password);
        if (statusUser <= 0) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }


    protected void onStart() {
        super.onStart();
        Log.w(MA, "inside MainActivity:onStart()\n");
    }
    protected void onRestart() {
        super.onRestart();
        Log.v(MA, "inside MainActivity:onRestart()\n");
    }
    protected void onResume() {
        super.onResume();
        Log.v(MA, "inside MainActivity:onResume()\n");
    }
    protected void onPause() {
        super.onPause();
        Log.v(MA, "inside MainActivity:onPause()\n");
    }
    protected void onStop() {
        super.onStop();
        Log.v(MA, "inside MainActivity:onStop()\n");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.v(MA, "inside MainActivity:onDestroy()\n");
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.view_records) {
            Intent intent = new Intent(this,ListMainRecords.class);
            startActivity(intent);


        } else if (id == R.id.view_graphs) {
            Intent intent = new Intent(this,ViewGraphs.class);
            startActivity(intent);

        } else if (id == R.id.delete) {
            Intent intent = new Intent(this, ReminderListActivity.class);
            startActivity(intent);

        } else if (id == R.id.user_details){

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void selectAddDetails(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }
}
