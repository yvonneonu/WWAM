package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verfy2 extends AppCompatActivity {
    private TextView shownum;


    String phonenumber, otp;
    int randonnumber;
    Button getotp, con;
    TextView resend;
    String otp_text;
    private String token;
    EditText first, secd, third, fourt, fifth, six;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfy2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getotp = findViewById(R.id.button2);
        resend = findViewById(R.id.textView17);
        shownum = findViewById(R.id.textView14);
        first = findViewById(R.id.editText9);
        secd = findViewById(R.id.editText10);
        third = findViewById(R.id.editText12);
        fourt = findViewById(R.id.editText15);
        fifth = findViewById(R.id.editText11);
        six = findViewById(R.id.editText13);

        String phon = getIntent().getStringExtra("phone");
        //shownum.setText(phon);

        //Bundle bundle = getIntent().getExtras();
       // token.getString("token");
        shownum.setText(phonenumber);


        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                otp_text = first.getText().toString().trim();
                otp_text = secd.getText().toString().trim();
                otp_text = third.getText().toString().trim();
                otp_text = fourt.getText().toString().trim();
                otp_text = fifth.getText().toString().trim();
                otp_text = six.getText().toString().trim();

                gottensendotp(otp);
                Intent mainactivity = new Intent(Verfy2.this, Successverified.class);
                startActivity(mainactivity);
                finish();
            }
        });
    }

    void gottensendotp( String otp) {
        myotprequest myotprequest2 = new myotprequest("otp");
        //if (userService == null)
        userService = new ApiClient().getService();

        Call<myotpresponse> call = userService.verifyOTP(myotprequest2);
           call.enqueue(new Callback<myotpresponse>() {
               @Override
               public void onResponse(Call<myotpresponse> call, Response<myotpresponse> response) {

                   try {
                       if (response.isSuccessful()) {
                           Intent i = new Intent(Verfy2.this, Successverified.class);
                           startActivity(i);
                       } else {
                           Log.d("Failure", response.isSuccessful() + "|||" + response.body().getOtp());
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }

               }


        @Override
               public void onFailure(Call<myotpresponse> call, Throwable t) {

            Log.e("ERROR", t.toString());
               }
           });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = shownum.getText().toString();

                initialsendotp(phonenumber);

                Intent mainactivity = new Intent(Verfy2.this, Splash.class);
                startActivity(mainactivity);
                finish();
            }
        });


    }

    void initialsendotp(String phonenumber) {
        otprequest myotpreques = new otprequest(phonenumber);
        //if (userService == null)
        userService = new ApiClient().getService();
        Call<otpResponse> call = userService.requestortp(myotpreques, "Bearer " + token);
        call.enqueue(new Callback<otpResponse>() {
            @Override
            public void onResponse(Call<otpResponse> call, Response<otpResponse> response) {
                if (response.isSuccessful()) {
                 //   Intent mainactivity = new Intent(Verfy2.this, Splash.class);
                   // startActivity(mainactivity);
                   // finish();
                    Toast.makeText(Verfy2.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    // token = response.body().getMessage();

                } else {
                    String message = "An error occured please try again";
                    Toast.makeText(Verfy2.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<otpResponse> call, Throwable t) {
                String message = "An error occured please try again";
                t.printStackTrace();
                Toast.makeText(Verfy2.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

}
