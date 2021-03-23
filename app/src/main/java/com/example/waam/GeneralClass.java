package com.example.waam;

import android.app.Application;

import com.stripe.android.PaymentConfiguration;

public class GeneralClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_TYooMQauvdEDq54NiTphI7jx"
        );
    }
}
