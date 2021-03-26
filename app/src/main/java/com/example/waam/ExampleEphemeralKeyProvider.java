package com.example.waam;



import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Size;

import com.stripe.android.EphemeralKeyProvider;
import com.stripe.android.EphemeralKeyUpdateListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExampleEphemeralKeyProvider implements EphemeralKeyProvider {

    private final String token;

    public ExampleEphemeralKeyProvider(String token) {
        this.token = token;
    }

    @Override
    public void createEphemeralKey(@NonNull @Size(min = 4) String apiVersion, @NotNull EphemeralKeyUpdateListener ephemeralKeyUpdateListener) {

        final Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put("api_version", apiVersion);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService ephemeralPost  = retrofit.create(UserService.class);
        Call<Ephemeral> ephemeralCall = ephemeralPost.getEphemeral(apiVersion,"Bearer "+token);



        ephemeralCall.enqueue(new Callback<Ephemeral>() {
            @Override
            public void onResponse(Call<Ephemeral> call, Response<Ephemeral> response) {
                if(!response.isSuccessful()){
                    Log.d("Err",""+response.code());
                    Log.d("Error","An error occured");
                    return;
                }

                Log.d("connection","Connection succesful");
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
