package com.example.waam.Notification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {"Content-type:application/json",
             "Authorization:key=AAAARalF8EQ:APA91bFTLQw7uS9KzYIdOyDwsk_QAvx9hdAtSHUlPJEsn-aOZSLgpk_baF8YSrbJYpFGbtlbw6a02EMzqv9z8MA3BXX-In6uIbKekEKCoB2zVMYbYn867fE5P_flkALaHVbKX4Zg41De"
            }
            )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
