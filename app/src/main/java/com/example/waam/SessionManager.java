package com.example.waam;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.connectycube.users.model.ConnectycubeUser;

import static android.preference.PreferenceManager.*;

public class SessionManager {
    private static final String TOKEN = "";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public static void setConnectyUser(ConnectycubeUser user){

    }

    public static int getConnectyUser(){
        return -1;
    }

}
