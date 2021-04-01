package com.example.waam;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
