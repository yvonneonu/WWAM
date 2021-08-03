package com.example.waam;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Unfriend extends AppCompatActivity {
    TextView textView2, textView27, textView23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfriend);
        textView2 = findViewById(R.id.textView2);
        textView27 = findViewById(R.id.textView27);
        textView23 = findViewById(R.id.textView23);


        textView27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Unfriend.this, "This user is no longer a friend!",
                 Toast.LENGTH_LONG).show();
            }
        });
        textView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Unfriend.this, "This user has been blocked!",
                        Toast.LENGTH_LONG).show();
            }
        });



        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}