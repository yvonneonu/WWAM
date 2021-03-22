package com.example.waam;



import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Size;

import com.stripe.android.EphemeralKeyProvider;
import com.stripe.android.EphemeralKeyUpdateListener;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExampleEphemeralKeyProvider implements EphemeralKeyProvider {

    @Override
    public void createEphemeralKey(@NonNull @Size(min = 4) String apiVersion, @NotNull EphemeralKeyUpdateListener ephemeralKeyUpdateListener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService ephemeralPost  = retrofit.create(UserService.class);

        String token = "";
        Call<Ephemeral> ephemeralCall = ephemeralPost.getEphemeral("2020-08-27","Bearer "+token);

        ephemeralCall.enqueue(new Callback<Ephemeral>() {
            @Override
            public void onResponse(Call<Ephemeral> call, Response<Ephemeral> response) {
                if(!response.isSuccessful()){
                    Log.d("Error","An error occured");
                    return;
                }

                Ephemeral ephemeral = response.body();

                Log.d("EphemeralString",ephemeral.getEphemeralString());
                ephemeralKeyUpdateListener.onKeyUpdate(ephemeral.getEphemeralString());

            }
            @Override
            public void onFailure(Call<Ephemeral> call, Throwable t) {

                Log.d("EphemeralString",t.getMessage());
            }
        });




    }
}
