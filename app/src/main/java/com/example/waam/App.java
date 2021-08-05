package com.example.waam;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

public class App extends Application {
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant((Timber.Tree)(new Timber.DebugTree()));
        }

        SettingsProvider.INSTANCE.initConnectycubeCredentials((Context)this);
        SettingsProvider.INSTANCE.initChatConfiguration();
    }
}
