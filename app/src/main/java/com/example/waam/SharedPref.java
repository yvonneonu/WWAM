package com.example.waam;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPref {

    public static final String TOKEN = "token";
    public static final String FULLNAME = "full_name";
    private static SharedPref pref;
    private final SharedPreferences sharedPref;
    private SharedPref(Context context){
       sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static SharedPref getInstance(Context context){
        if( pref == null) pref =  new SharedPref(context);
        return  pref;
    }

    public  String getStoredToken() {
        return sharedPref.getString(TOKEN, null);
    }


    public void setStoredToken(String key, String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, token);
        editor.apply();

    }
    public  String getStoredName() {
        return sharedPref.getString(FULLNAME, null);
    }
    public void setStoredName(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();

    }










}
