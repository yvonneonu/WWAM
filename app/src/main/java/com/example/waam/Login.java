package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    private TextView signup;
    private ImageView log;
    private TextView text;
    private TextView pressback;

    private TextInputEditText editPass;
    private TextInputEditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signup = findViewById(R.id.again);
        pressback = findViewById(R.id.back);
        log = findViewById(R.id.button);
        text = findViewById(R.id.forgetpass);

        editPass = findViewById(R.id.password);
        editEmail = findViewById(R.id.email);

        pressback.setOnClickListener(v -> GoBack());
        text.setOnClickListener(v -> AnotherActivity());
        signup.setOnClickListener(v -> SignUnpage());
    }

    private void AnotherActivity() {
        Intent intent = new Intent(Login.this, Another.class);
        startActivity(intent);
        // finish();
    }

    private void SignUnpage() {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
        // finish();
    }


    private void GoBack() {
        Intent intent = new Intent(Login.this, ViewPager.class);
        startActivity(intent);
    }
}