package com.example.waam;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPref {

    private static final String TOKEN = "";
    private static final String FULLNAME = "";
    private static SharedPref pref;
    private final SharedPreferences sharedPref;
    private SharedPref(Context context){
       sharedPref = context.getSharedPreferences("name", Context.MODE_PRIVATE);
    }

    public static SharedPref getInstance(Context context){
        if( pref == null) pref =  new SharedPref(context);
        return  pref;
    }

    public  String getStoredToken(Context context) {
        return sharedPref.getString(TOKEN, null);
    }


    public void setStoredToken(Context context, String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("key", "Value");
        editor.apply();

    }
    public  String getStoredName(Context context) {
        return sharedPref.getString(FULLNAME, null);
    }
    public void setStoredName(Context context, String name) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("key", "Value");
        editor.apply();

    }










}
