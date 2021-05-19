package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Another extends AppCompatActivity {
    private TextView back;
    private Button send;
    private TextView ret;
    private EditText enterEmail;
    private String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        back = findViewById(R.id.goback);
        send = findViewById(R.id.sendEmail);
        ret = findViewById(R.id.returnlogin);
        enterEmail = findViewById(R.id.editText3);


        ret.setOnClickListener(v -> pressBack());
        back.setOnClickListener(v -> pressBack());
        send.setOnClickListener(v -> sed());
    }

    private void pressBack() {
        Intent intent = new Intent(Another.this, Login.class);
        startActivity(intent);
    }
    private void sed() {
        Email = enterEmail.getText().toString();

        if (TextUtils.isEmpty(enterEmail.getText().toString())){
            String message = "Enter Email Address";
            Toast.makeText(Another.this, message, Toast.LENGTH_LONG).show();



        }else{
            emailAddress getEmailAddress = new emailAddress("email");
            getEmailAddress.setEmail(enterEmail.getText().toString());
            GeneralFactory.getGeneralFactory(Another.this).changePassword(Email, getEmailAddress);
            //String email = enterEmail.getText().toString()
        }


    }
}