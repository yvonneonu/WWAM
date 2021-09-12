package com.example.waam;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPref {

    public static final String TOKEN = "token";
    public static final String FULLNAME = "full_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String UID = "uid";
    public static final String VALUE = "50";
    public static final String AGE = "age";
    private static SharedPref pref;
    private final SharedPreferences sharedPref;

    private SharedPref(Context context) {
        sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

    }

    public static SharedPref getInstance(Context context) {
        if (pref == null) pref = new SharedPref(context);
        return pref;
    }

    public String getStoredToken() {
        return sharedPref.getString(TOKEN, null);
    }


    public void setStoredToken(String key, String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, token);
        editor.apply();

    }

    public String getStoredName() {
        return sharedPref.getString(FULLNAME, null);
    }

    public void setStoredName(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();

    }
    public String getStoredAge() {
        return sharedPref.getString(AGE, null);
    }

    public void setStoredAge(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();

    }

    public void setStoredEmail(String key, String email) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, email);
        editor.apply();
    }

    public String getStoredEmail() {
        return sharedPref.getString(EMAIL, null);
    }

    public void setStoredPassword(String key, String password) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, password);
        editor.apply();
    }

    public String getStoredPassword() {
        return sharedPref.getString(PASSWORD, null);
    }


    public void setStoredUid(String key, String uid) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, uid);
        editor.apply();
    }

    public String getStoredUid() {
        return sharedPref.getString(UID, null);
    }

    public void setStoredValue(String key, int token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(key, token);
        editor.apply();

    }

    public float getStoredValue() {
        return sharedPref.getFloat(VALUE, 50);
    }




}
