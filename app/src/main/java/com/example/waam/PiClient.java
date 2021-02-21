package com.example.waam;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PiClient {
    public static final String BASE_URL = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/";


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
           retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;

    }
}
