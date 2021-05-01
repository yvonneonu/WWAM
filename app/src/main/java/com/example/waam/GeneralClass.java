package com.example.waam;

import android.app.Application;

import com.connectycube.auth.session.ConnectycubeSettings;
import com.stripe.android.PaymentConfiguration;

public class GeneralClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_51ITAgKBgd3O4ny7mXz17kU4QCrp3hQ0CQjpSdjHhXmDkoDYxRVgj9diGYnenw5cCVBigd0iFLv1kZ1LhDaTKlhlh00ZJpFHeVy"
        );
    }
}
