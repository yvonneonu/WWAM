package com.example.waam;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    public static Retrofit getRetrofit() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

       OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://ec2-52-36-253-117.us-west-2.compute.amazonaws.com")
                .client(okHttpClient)
                .build();

        return retrofit;
    }
public static UserService getService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
}

    public static Call<ResponseBody> getSecret(String token) {
        return null;
    }
}
