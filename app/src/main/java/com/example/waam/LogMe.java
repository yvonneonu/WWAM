package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Pattern;

import retrofit2.Call;

public class LogMe extends AppCompatActivity implements View.OnClickListener{

    TextView emails, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_log_me);

        emails = findViewById(R.id.editText2);
        password = findViewById(R.id.editText4);

        findViewById(R.id.editText2).setOnClickListener(this);
        findViewById(R.id.editText4).setOnClickListener(this);
    }

    private void userLogin() {
        String email = emails.getText().toString().trim();
        String passw = password.getText().toString().trim();

        if (email.isEmpty()) {
            emails.setError("Emaill is required");
            emails.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emails.setError("Enter a Valid email");
            emails.requestFocus();
            return;
        }
        if (passw.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (passw.length() < 6) {
            password.setError("Password should be at aleast 6 character long");
            password.requestFocus();
            return;
        }
        //Call<LoginResponse> call =;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editText2:
                userLogin();
                break;
            case R.id.editText4:
                break;
        }
    }
}