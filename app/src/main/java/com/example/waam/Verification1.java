package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        token = bundle.getString("token");
        Fullname = bundle.getString("name");


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


// Whenever verification is triggered with the whitelisted number,
// The test phone number and code should be whitelisted in the console.
                String phoneNumber = "+16505554567";
                String smsCode = "123456";

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();

// Configure faking the auto-retrieval with the whitelisted numbers.
                firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phonenumber, smsCode);

                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phonenumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(Verification1.this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {
                                // Instant verification is applied and a credential is directly returned.
                                // ...
                                Log.d("hgcfg", ""+credential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Log.d("hgcfg", ""+e);


                            }

                            // ...
                        })
                        .build();
                PhoneAuthProvider.verifyPhoneNumber(options);


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


                  Log.d("TAG", ""+Fullname);
                   Intent mainactivity = new Intent(Verification1.this, Splash.class);
                   mainactivity.putExtras(bundle);

                   startActivity(mainactivity);
                   Toast.makeText(Verification1.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                   finish();
                  //Intent mainactivity = new Intent(Verification1.this, Splash.class);
                   //startActivity(mainactivity);
                  // token = response.body().getMessage();

               } else {
                   String message = "The number  is unverified";
                   Toast.makeText(Verification1.this, message, Toast.LENGTH_LONG).show();
               }
           }
           @Override
           public void onFailure(Call<otpResponse> call, Throwable t) {
               String message = "The number  is unverified";
               t.printStackTrace();
               Toast.makeText(Verification1.this, message, Toast.LENGTH_LONG).show();
           }
       });

   }


}