package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Verfy2 extends AppCompatActivity {
    private TextView shownum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfy2);

        shownum = findViewById(R.id.textView14);

        String phon = getIntent().getStringExtra("phone");
        shownum.setText(phon);
    }
}