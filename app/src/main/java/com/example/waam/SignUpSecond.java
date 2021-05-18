package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.BreakIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpSecond extends AppCompatActivity {

    private GeneralFactory generalFactory;
    private ProgressBar progressBar;
    private EditText name,email,zip,update,password,confrim;
    private String chose ="";
    private String  interest = "";
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_second);
        generalFactory = GeneralFactory.getGeneralFactory(this);
        progressBar = findViewById(R.id.cardview);
        name = findViewById(R.id.editText8);
        email = findViewById(R.id.editText2);
        zip = findViewById(R.id.editText4);
        //update = findViewById(R.id.);
        password = findViewById(R.id.editText);
        confrim = findViewById(R.id.editText88);
        if (isNetworkAvailableAndConnected()){
            register();
            //constraintLayout.setVisibility(View.INVISIBLE);
            //textView.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // constraintLayou.setVisibility(View.GONE);
                    // constraintLayout.setVisibility(View.VISIBLE);
                    // textView.setVisibility(View.VISIBLE);
                }
            }, 5000);
        }
        else {
            Toast.makeText(SignUpSecond.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }


    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();
        return isNetworkConnected;
    }

    private void register() {

        Log.d("UserService",""+userService);
        if (userService == null) {
            userService = new ApiClient().getService();
        }

        WaamUser waamUser = new WaamUser("name", "email", "zipcode", "gender", "seeking", "date", "pass");

        String Fullname = name.getText().toString();
        String Email = email.getText().toString();
        String Zip = zip.getText().toString();
        String Update = update.getText().toString();
        String Passwor = password.getText().toString();
        String Confirm = confrim.getText().toString();
        if(Fullname.isEmpty()) {
            name.setError("Full Name is required");
            name.requestFocus();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Enter a Valid email");
            email.requestFocus();

        }else if (Zip.isEmpty()) {
            zip.setError("Zip Code is required");
            zip.requestFocus();
        } else if (Update.isEmpty()) {
            update.setError("Birthday Date is required");
            update.requestFocus();
        }else if (!Passwor.equals(Confirm)) {
            confrim.setError("Wrong Password");
            confrim.requestFocus();
        }else if (chose.isEmpty()) {
            Toast.makeText(SignUpSecond.this, "Choose your gender", Toast.LENGTH_LONG).show();
        }else if (interest.isEmpty()) {
            Toast.makeText(SignUpSecond.this, "Choose your gender", Toast.LENGTH_LONG).show();
        }else if (Passwor.length() < 6) {
            // password.setError("Password should be at aleast 6 character long");
            password.requestFocus();

        }else if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(zip.getText().toString()) || TextUtils.isEmpty(update.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ||
                TextUtils.isEmpty(confrim.getText().toString())) {
            String message = "All inputs required";
            Toast.makeText(SignUpSecond.this, message, Toast.LENGTH_LONG).show();
        } else {

            //Log.d("meemmemememe", ""+user);
            waamUser.setFullname(Fullname);
            waamUser.setEmail(Email);
            waamUser.setZipcode(Zip);
            waamUser.setBirth_date(Update);
            waamUser.setPassword(Passwor);
            waamUser.setPassword_confirmation(Confirm);
            waamUser.setGender(chose);
            waamUser.setSeeking(interest);
            generalFactory.requestUser(waamUser,progressBar);
            //requestUser(waamUser);


        }



    }



}