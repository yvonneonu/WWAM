package com.example.waam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.hbb20.CountryCodePicker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verification1 extends AppCompatActivity {

    private Button getotp;
    private EditText userphoneno;
    private CountryCodePicker ccp;
    String phonenumber = "";
    int randonnumber;
    String otp_text;
    String API_KEY;
    private String token;
    UserService userService;
    String Fullname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification1);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        token = bundle.getString("token");


        ccp = findViewById(R.id.ccp);
        userphoneno = findViewById(R.id.edit_text);
        getotp = findViewById(R.id.savePhonenumber);

        final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

       // Intent intent = getIntent();


        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String me = ccp.getSelectedCountryCodeWithPlus();
                String phonenumbe = userphoneno.getText().toString();
                String phonenumber = me + phonenumbe;
                Log.d("tag", phonenumber);
                initialsendotp(phonenumber);

            }
        });

    }


    void initialsendotp(String phonenumber) {
        otprequest myotpreques = new otprequest(phonenumber);
       //if (userService == null)
           userService = new ApiClient().getService();
       Call<otpResponse> call = userService.requestortp(myotpreques, "Bearer "+token);
       call.enqueue(new Callback<otpResponse>() {
           @Override
           public void onResponse(Call<otpResponse> call, Response<otpResponse> response) {
               if (response.isSuccessful()){

                   //getIntent().getExtras();
                   //token = response.body().getMessage();

                   Log.d("INSIDE","I am inside response");
                   Log.d("INSIDE",""+response.body());
                  // initialsendotp(phonenumber);
                   Log.d("RESPONSE", response.body().getMessage());
                   Bundle bundle = new Bundle();

                   bundle.putString("phonenumber", phonenumber);
                   bundle.putString("token", token);
                   Log.d("TAG", "TOKENSHOW1 " +token);
                   bundle.putString("name", Fullname);

                   Intent mainactivity = new Intent(Verification1.this, Splash.class);
                   mainactivity.putExtras(bundle);
                   startActivity(mainactivity);
                   Toast.makeText(Verification1.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                   finish();
                  //Intent mainactivity = new Intent(Verification1.this, Splash.class);
                   //startActivity(mainactivity);
                  // token = response.body().getMessage();

               } else {
                   String message = "An error occured please try again";
                   Toast.makeText(Verification1.this, message, Toast.LENGTH_LONG).show();
               }
           }
           @Override
           public void onFailure(Call<otpResponse> call, Throwable t) {
               String message = "An error occured please try again";
               t.printStackTrace();
               Toast.makeText(Verification1.this, message, Toast.LENGTH_LONG).show();
           }
       });

   }


}