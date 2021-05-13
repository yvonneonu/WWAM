package com.example.waam;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.connectycube.users.model.ConnectycubeUser;

import static android.preference.PreferenceManager.*;

public class SessionManager {

    private  final String TOKEN = "login_token";
    private  final String USER_ID = "user_id";
    private final SharedPreferences sharedPreferencesToken;
    private final SharedPreferences sharedPreferencesUser;
    private final SharedPreferences.Editor editor;
    private static SessionManager sessionManager;

    private SessionManager(Context context){
        sharedPreferencesToken = context.getSharedPreferences(TOKEN,Context.MODE_PRIVATE);
        sharedPreferencesUser = context.getSharedPreferences(USER_ID,Context.MODE_PRIVATE);
        editor = sharedPreferencesUser.edit();
    }


    public static SessionManager getSessionManager(Context context){
        if(sessionManager == null){
            sessionManager = new SessionManager(context);
        }
        return sessionManager;
    }

    public void setConnectyUser(ConnectycubeUser user){
        int id = user.getId();
        Log.d("SettingID",""+id);
        editor.putInt(USER_ID,id)
                .commit();
    }

    public int getConnectyUser(){
        int id = sharedPreferencesUser.getInt(USER_ID,-1);
        Log.d("Id",""+id);
        return id;
    }


    public void setTOKEN(String TOKEN) {
        editor.putString(USER_ID,TOKEN)
        .apply();
    }

    public String getTOKEN() {
        return sharedPreferencesToken.getString(TOKEN,null);
    }
}
