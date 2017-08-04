package com.project.uwm.mydiabitiestracker.Objects;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Anitha on 7/25/2017.
 */

public class UserPreference {

    private static String userNameField;
    private static String passwordField;
    private static final String USER_KEY = "userKey";
    private static final String PASSWORD_KEY ="passwordKey";

    public UserPreference(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        setUserName(pref.getString(USER_KEY,"name"));
        setPassword(pref.getString(PASSWORD_KEY,"password"));
    }

    public void setPreference(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_KEY, userNameField);
        editor.putString(PASSWORD_KEY,passwordField);
        editor.commit();
    }

    public static void setPassword(String password) {
        userNameField= password;
    }

    public static void setUserName(String userName) {
        passwordField= userName;
    }

    public static String getPassword() {
        return passwordField;
    }

    public static String getUserName() {
        return userNameField;
    }
}

