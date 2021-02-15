package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Another extends AppCompatActivity {
    private TextView back;
    private Button send;
    private TextView ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        back = findViewById(R.id.goback);
        send = findViewById(R.id.sendEmail);
        ret = findViewById(R.id.returnlogin);


        ret.setOnClickListener(v -> pressBack());
        back.setOnClickListener(v -> pressBack());
        send.setOnClickListener(v -> sed());
    }

    private void pressBack() {
        Intent intent = new Intent(Another.this, Login.class);
        startActivity(intent);
    }
    private void sed() {
        Intent intent = new Intent(Another.this, passw.class);
        startActivity(intent);
    }
}