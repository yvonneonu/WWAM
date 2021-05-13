package com.example.waam;

import android.app.Application;

import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.core.LogLevel;
import com.stripe.android.PaymentConfiguration;

public class GeneralClass extends Application {

    static final String APP_ID = "4663";
    static final String AUTH_KEY = "RWV8dBeCsCh6g2a";
    static final String AUTH_SECRET = "yhuExsebKPu8F8S";
    static final String ACCOUNT_KEY = "tBL4Vzzzj7fQMfzsHYii";

    @Override
    public void onCreate() {
        super.onCreate();

        ConnectycubeSettings.getInstance().init(getApplicationContext(), APP_ID, AUTH_KEY, AUTH_SECRET);
        ConnectycubeSettings.getInstance().setAccountKey(ACCOUNT_KEY);
        ConnectycubeSettings.getInstance().setLogLevel(LogLevel.NOTHING);
        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_51ITAgKBgd3O4ny7mXz17kU4QCrp3hQ0CQjpSdjHhXmDkoDYxRVgj9diGYnenw5cCVBigd0iFLv1kZ1LhDaTKlhlh00ZJpFHeVy"
        );
    }
}
