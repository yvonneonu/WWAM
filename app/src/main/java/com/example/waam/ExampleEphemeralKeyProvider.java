package com.example.waam;


import android.content.Context;
import android.util.Log;

import androidx.annotation.Size;

import com.google.gson.Gson;
import com.stripe.android.EphemeralKeyProvider;
import com.stripe.android.EphemeralKeyUpdateListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExampleEphemeralKeyProvider implements EphemeralKeyProvider {

    private String token;
    Context context;
    UserService userService;

    public ExampleEphemeralKeyProvider(String token, Context context) {
        this.context = context;
        this.token = token;
    }

    @Override
    public void createEphemeralKey(@NonNull @Size(min = 4) String apiVersion, @NotNull EphemeralKeyUpdateListener ephemeralKeyUpdateListener) {

        final Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put("api_version", apiVersion);

         user(apiVersion, ephemeralKeyUpdateListener);
        token = SharedPref.getInstance(context).getStoredToken();

       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService ephemeralPost = retrofit.create(UserService.class);
        Call<Ephemeral> ephemeralCall = ephemeralPost.getEphemeral(apiVersion, "Bearer " + token);

        ephemeralCall.enqueue(new Callback<Ephemeral>() {
            @Override
            public void onResponse(Call<Ephemeral> call, Response<Ephemeral> response) {
                if (!response.isSuccessful()) {
                    Log.d("Err", "" + response.code());
                    Log.d("Error", "An error occured");
                    return;
                }

                Log.d("connection", "Connection succesful");
                Ephemeral ephemeral = response.body();
                Log.d("EphemeralString", ephemeral.getEphemeralString());

                ephemeralKeyUpdateListener.onKeyUpdate(ephemeral.getEphemeralString());
            }

            @Override
            public void onFailure(Call<Ephemeral> call, Throwable t) {

                Log.d("EphemeralString", t.getMessage());
            }
        });*/

   // }


//}
    }
        public void user (String api, EphemeralKeyUpdateListener ephemeralKeyUpdateListener){
            EphemeralPost ephemeral = new EphemeralPost();

            userService = new ApiClient().getService();
            ephemeral.setStripeVersion(api);

            Call<Ephemeral> ephemeralCall = userService.getEphemeral(ephemeral, "Bearer " +token);
            ephemeralCall.enqueue(new Callback<Ephemeral>() {
                @Override
                public void onResponse(Call<Ephemeral> call, Response<Ephemeral> response) {
                    if (!response.isSuccessful()) {
                        Log.d("Err", "" + response.body());


                        Log.d("Error", "An error occured");

                        return;
                    }


                    Ephemeral eph = response.body();

                    ephemeralKeyUpdateListener.onKeyUpdate(new Gson().toJson(eph).toString());
                    String jzon = new Gson().toJson(eph);
                    Log.d("Anything", jzon);
                    Log.d("connection", "Connection succesful");
                    Ephemeral ephemeral = response.body();
                    Log.d("EphemeralString", ephemeral.getEphemeralString());

                    //Toast.makeText(ExampleEphemeralKeyProvider.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<Ephemeral> call, Throwable t) {

                    String message = "An error occured please try again";
                    t.printStackTrace();
                    Log.d("MYWORLD", "" + t.getMessage());

                }
            });
        }
    }



