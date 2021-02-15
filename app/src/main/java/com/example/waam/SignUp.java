package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private TextView lologin, name, email, password, confrim;
    private TextView back;
    private Button firstGen, secondGender, thirdGender, fourthGender;
    private ImageView move;
    int[] color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        lologin = findViewById(R.id.gologin);
        back = findViewById(R.id.backto);
        move = findViewById(R.id.logo);
        name = findViewById(R.id.editText8);
        email = findViewById(R.id.editText2);
        password = findViewById(R.id.editText);
        confrim = findViewById(R.id.editText88);
        firstGen = findViewById(R.id.textView9);
        secondGender = findViewById(R.id.editText7);
        thirdGender = findViewById(R.id.textView12);
        fourthGender = findViewById(R.id.editText6);


        lologin.setOnClickListener(v -> Signinhere());
        back.setOnClickListener(v -> Signback());

        firstGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // firstGen.setBackgroundDrawable(firstGen.getResources().getDrawable(R.drawable.buttoncolor));
               // firstGen.setTextColor(getResources().getColor(R.color.white));
              //  firstGen.setBackgroundColor(getResources().getColor(R.color.blue));
                //firstGen.setTextColor(getResources().getColor(R.color.blue));
             // firstGen.setBackgroundColor(firstGen.getContext().getResources().getColor(R.color.black));
              //  firstGen.setTextColor(firstGen.getContext().getResources().getColor(R.color.white));
               firstGen.setBackgroundColor(Color.parseColor("#1A369C"));
               firstGen.setBackgroundColor(R.drawable.buttoncolor);
//               firstGen.setBackgroundResource(R.drawable.buttoncolor);
              // firstGen.setBackgroundColor(firstGen.getContext().getResources().getColor(R.color.black));
                //secondGender.setBackgroundColor(secondGender.getContext().getResources().getColor(R.color.white));
            }
        });
        secondGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondGender.setBackgroundColor(secondGender.getContext().getResources().getColor(R.color.blue));
                //firstGen.setBackgroundColor(firstGen.getContext().getResources().getColor(R.color.white));
            }
        });


        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) ||
                        TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(confrim.getText().toString())){
                    String message = "All inputs required";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                }else {
                    RegisterRequest registerRequest = new RegisterRequest("name", "email", "password", "password_confirmation");
                    registerRequest.setName(name.getText().toString());
                    registerRequest.setEmail(email.getText().toString());
                    registerRequest.setPassword(password.getText().toString());
                    registerRequest.setPassword_confirmation(confrim.getText().toString());
                    requestUser(registerRequest);
                }
            }
        });
    }


    public void requestUser(RegisterRequest registerRequest){

        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

              if (response.isSuccessful()) {
                  //response.body();
                  String message = "Successful";
                  Toast.makeText(SignUp.this,message,Toast.LENGTH_LONG).show();

                  startActivity(new Intent(SignUp.this, Login.class));
                  finish();
            }else {
                  //response.errorBody();
                 String message = "An error occured please try again";
                 Toast.makeText(SignUp.this,message,Toast.LENGTH_LONG).show();
              }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(SignUp.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
    private void Signinhere() {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
        // finish();
    }
    private void Signback() {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
        // finish();
    }
}