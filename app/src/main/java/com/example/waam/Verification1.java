package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

public class Verification1 extends AppCompatActivity {

    private Button getotp;
    private EditText userphoneno;
    private CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification1);

        init();
    }

    private void init() {
        ccp = findViewById(R.id.ccp);
        userphoneno = findViewById(R.id.edit_text);
        getotp = findViewById(R.id.savePhonenumber);

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getNumber();

                if (userphoneno.getText().toString().trim().isEmpty())
                {
                    userphoneno.setError("Enter Phone Number");
                }else {
                    Intent i = new Intent(Verification1.this, Splash.class);
                    i.putExtra("phone", userphoneno.getText().toString().trim());
                    startActivity(i);
                    finish();
                }
            }
        });
    }

   // private void getNumber() {
      //  String fullNumber = ccp.getFullNumber() + userphoneno.getText().toString();

   // }
}