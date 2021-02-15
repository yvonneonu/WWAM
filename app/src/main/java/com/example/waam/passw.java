package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class passw extends AppCompatActivity {
    private TextView tback;

    private TextView backtolog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_passw);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tback = findViewById(R.id.backto);
        backtolog = findViewById(R.id.login);

        backtolog.setOnClickListener(v -> loginPage());
        tback.setOnClickListener(v -> goBackhere());

}
    private void goBackhere() {
        Intent intent = new Intent(passw.this, Another.class);
        startActivity(intent);
    }
    private void loginPage() {
        Intent intent = new Intent(passw.this, Login.class);
        startActivity(intent);
    }
}