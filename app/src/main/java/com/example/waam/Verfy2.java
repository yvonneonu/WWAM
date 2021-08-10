package com.example.waam;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verfy2 extends AppCompatActivity {
    private TextView shownum;


    private FirebaseAuth mAuth;
    // String phonenumber, otp;
    int randonnumber;
    Button getotp, con;
    TextView resend;
    String otp_text;
    String phonenumber = "";
    String token;
    private String verificationid; ;
    private String bearer;
    EditText first, secd, third, fourt, fifth, six;
    UserService userService;
    String Fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfy2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            bearer = bundle.getString("token");
            Fullname = bundle.getString("name");

        }


        getotp = findViewById(R.id.button2);
        resend = findViewById(R.id.textView17);
        shownum = findViewById(R.id.textView14);
        first = findViewById(R.id.editText9);
        secd = findViewById(R.id.editText10);
        third = findViewById(R.id.editText12);
        fourt = findViewById(R.id.editText15);
        fifth = findViewById(R.id.editText11);
        six = findViewById(R.id.editText13);

        String phonenumber = getIntent().getStringExtra("number");
        // Fullname = getIntent().getStringExtra("name");
        shownum.setText(phonenumber);
        final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        sendVerificaionCode(phonenumber);
        mAuth = FirebaseAuth.getInstance();
        mAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstotp = first.getText().toString();
                String Secondotp = secd.getText().toString();
                String Thirdotp = third.getText().toString();
                String Fouthotp = fourt.getText().toString();
                String Fifthotp = fifth.getText().toString();
                String Sixotp = six.getText().toString();
                String completeotp = firstotp + Secondotp + Thirdotp + Fouthotp + Fifthotp + Sixotp;

                if (completeotp.isEmpty()){
                    secd.setError("entr");
                    secd.requestFocus();
                }
                verifyCode(completeotp);

                //gottensendotp();

            }
        });


        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = shownum.getText().toString();
                Log.d("tag", phonenumber);
                Bundle bundle = new Bundle();

                bundle.putString("phonenumber", phonenumber);
                bundle.putString("bearer", token);
                bundle.putString("name", Fullname);
                Log.d("TAG", "" + Fullname);

                // bundle.putString("name", Fullname);

                otpresend();
            }
        });

    }
    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, code);
        signInWithCredential(credential);




    }



    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser user = task.getResult().getUser();
                           String  ud = FirebaseAuth.getInstance().getAccessToken(true).toString();


                           Log.d("use", ""+user);

                            Log.d("yri", ud);
                            Intent intent = new Intent(Verfy2.this, Successverified.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else {
                            Log.d("task is not successful", ""+task.getException());
                        }
                    }
                });
    }

    private void gottensendotp() {
        // Log.d("UserService", "" + userService);
        if (userService == null) {
            userService = new ApiClient().getService();
        }
        Myotprequest myotprequest = new Myotprequest("otp");
        String firstotp = first.getText().toString();
        String Secondotp = secd.getText().toString();
        String Thirdotp = third.getText().toString();
        String Fouthotp = fourt.getText().toString();
        String Fifthotp = fifth.getText().toString();
        String Sixotp = six.getText().toString();
        String completeotp = firstotp + Secondotp + Thirdotp + Fouthotp + Fifthotp + Sixotp;
        if (Secondotp.isEmpty()) {
            secd.setError("entr");
            secd.requestFocus();
        } else {
            myotprequest.setOtp(completeotp);
            // myotprequest.setOtp(completeotp);
            //requestotp(myotprequest, "Bearer");
            //requestotp(myotprequest, "");
            requestotp(myotprequest);

            Log.d("Bearering", "" + bearer);
            //requestotp(completeotp, bearer);
        }
    }

        private void otpresend () {
            if (userService == null) {
                userService = new ApiClient().getService();
            }
            Resendotprequest resendotprequest = new Resendotprequest("phonenumber");
            Log.d("UserService", "" + userService);
            //if (userService == null) {
            String number = shownum.getText().toString();
            if (number.isEmpty()) {
                shownum.setError("error");
            } else {
                resendotprequest.setPhone_number(number);
                otpuser(resendotprequest);
            }
        }

    private void otpuser(Resendotprequest resendotprequest) {
        Call<Resendotpresponse> resendotpresponseCall = ApiClient.getService().resendotpgotten(resendotprequest, "Bearer "+bearer);
        resendotpresponseCall.enqueue(new Callback<Resendotpresponse>() {
            @Override
            public void onResponse(Call<Resendotpresponse> call, Response<Resendotpresponse> response) {
                if (response.isSuccessful()) {
                    // response.body().getToken();

                    //response.body();
                    String message = "Successful";
                    Toast.makeText(Verfy2.this, message, Toast.LENGTH_LONG).show();

                    //startActivity(new Intent(Verfy2.this, Verification1.class).putExtra("token", response.body().getToken()));
                    // finish();

                } else {
                    response.body();
                    String message = "Invalid OTP given";
                    Toast.makeText(Verfy2.this, message, Toast.LENGTH_LONG).show();

                    // String message = "An error occured please try again";
                    //Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Resendotpresponse> call, Throwable t) {
                String message = "Invalid OTP given";
                t.printStackTrace();
                Toast.makeText(Verfy2.this, message, Toast.LENGTH_LONG).show();

            }
        });
    }


    private void requestotp(Myotprequest myotprequest) {
    Call<Myotpresponse> myotpresponseCall = ApiClient.getService().otpgotten(myotprequest, "Bearer "+bearer);
    myotpresponseCall.enqueue(new Callback<Myotpresponse>() {
        @Override
        public void onResponse(Call<Myotpresponse> call, Response<Myotpresponse> response) {
            if (response.isSuccessful()) {
                // response.body().getToken();

                String email = SharedPref.getInstance(Verfy2.this).getStoredEmail();
                String pass = SharedPref.getInstance(Verfy2.this).getStoredPassword();
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            String uid = FirebaseAuth.getInstance().getUid();
                            String message = "Successful";
                            Toast.makeText(Verfy2.this, message, Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Verfy2.this, Successverified.class);
                            intent.putExtra("token", response.body().getOtp());
                            intent.putExtra("tokenbearer", bearer);
                            Log.d("TAG", "TOKENSHOW2 " +bearer);
                            intent.putExtra("name", Fullname);
                            SharedPref.getInstance(Verfy2.this).setStoredUid(SharedPref.UID,uid);
                            Log.d("TAG", ""+Fullname);
                            startActivity(intent);
                            //startActivity(new Intent(Verfy2.this, Successverified.class).putExtra("token", response.body().getOtp()));
                            finish();

                        }else {
                            Log.d("failed", "input otp");
                        }
                    }
                });

                //response.body();

            } else {
                response.body();
                //response.errorBody();
                // String message = "An error occured please try again";
                //Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
            }


        }

        @Override
        public void onFailure(Call<Myotpresponse> call, Throwable t) {
           // String message = t.getLocalizedMessage();
            Toast.makeText(Verfy2.this, t.getMessage(), Toast.LENGTH_LONG).show();
        }

    });



    }
    private void sendVerificaionCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                Verfy2.this,
                mCallback

        );

    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationid = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            verifyCode(code);

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Log.d("onverificationfailed", ""+e.getMessage());

        }
    };
}
