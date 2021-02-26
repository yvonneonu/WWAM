package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private TextView lologin;
    String numberToPass = "1";
    private TextView back, mangender, womangender, seekingman, save, wantwoman;
    private String realGender, realInterest;
    private ImageView move;
    private Button update;
    UserService userService;
    private EditText name, email, zip, password, confrim;

    String chose = "";
    String interest = "";


    private static String token;
    //final String url_Register = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        initDatePicker();

        seekingman = findViewById(R.id.seekman);
        save = findViewById(R.id.editText3);
        wantwoman = findViewById(R.id.seekwoman);
        mangender = findViewById(R.id.mangend);
        womangender = findViewById(R.id.womangend);
        update = findViewById(R.id.forgetpass);
        zip = findViewById(R.id.editText4);
        lologin = findViewById(R.id.gologin);
        back = findViewById(R.id.backto);
        move = findViewById(R.id.logo);
        name = findViewById(R.id.editText8);
        email = findViewById(R.id.editText2);
        password = findViewById(R.id.editText);
        confrim = findViewById(R.id.editText88);
        lologin.setOnClickListener(v -> Signinhere());
        back.setOnClickListener(v -> Signback());
        update.setText(getTodaysDate());

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });
    }

    private String getTodaysDate() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int year = c.get(Calendar.YEAR);
        return makeDateString(day, month, year);
    }

    public void requestUser(RegisterRequest registerRequest) {
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()) {
                   // response.body().getToken();

                    //response.body();
                    String message = "Successful";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();

                    startActivity(new Intent(SignUp.this, Verification1.class).putExtra("token", response.body().getToken()));
                    finish();
                } else {
                    //response.errorBody();
                    String message = "An error occured please try again";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
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

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                update.setText(date);
            }
        };
        java.util.Calendar c = java.util.Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        int styles = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, styles, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        // return getMonthFormat(month) + "-" + dayOfMonth + "-" + year;
        return year + "-" + getMonthFormat(month) + "-" + dayOfMonth;
    }


    private String getMonthFormat(int month) {
        if (month == 1)
            return "01";
        if (month == 2)
            return "02";
        if (month == 3)
            return "03";
        if (month == 4)
            return "04";
        if (month == 5)
            return "05";
        if (month == 6)
            return "06";
        if (month == 7)
            return "07";
        if (month == 8)
            return "08";
        if (month == 9)
            return "9";
        if (month == 10)
            return "10";
        if (month == 11)
            return "11";
        if (month == 12)
            return "12";
        return "01";
    }


    public void colorme(View view) {
        if (view.getId() == mangender.getId()) {
            mangender.setBackgroundResource(R.drawable.section_tabs_active_1);
            womangender.setBackgroundResource(R.drawable.border);
            chose = mangender.getText().toString();
            Log.d("UserService","my gender is"+chose);
        }

    }

    public void woooo(View view) {
        if (view.getId() == womangender.getId()) {
            womangender.setBackgroundResource(R.drawable.sectiontabsactive2);
            mangender.setBackgroundResource(R.drawable.border2);
            chose = womangender.getText().toString();
            Log.d("UserService","my gender is"+chose);
        }
    }

    public void who(View view) {
        //RegisterRequest registerRequest = new RegisterRequest("name", "email", "coed", "gender", "seek", "daqte", "oass");
        if (view.getId() == seekingman.getId()) {
            seekingman.setBackgroundResource(R.drawable.section_tabs_active_1);
            wantwoman.setBackgroundResource(R.drawable.border);
            interest = seekingman.getText().toString();
            Log.d("UserService","I am seeking"+interest);
            //registerRequest.setSeeking(chose);

        }
    }

    public void sek(View view) {
        //RegisterRequest registerRequest = new RegisterRequest("name", "email", "coed", "gender", "seek", "daqte", "oass");
        if (view.getId() == wantwoman.getId()) {
            wantwoman.setBackgroundResource(R.drawable.sectiontabsactive2);
            seekingman.setBackgroundResource(R.drawable.border2);
            interest = wantwoman.getText().toString();
            Log.d("UserService","I am seeking"+interest);
            //registerRequest.setSeeking(chose);
           
        }
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private void register() {
        Log.d("UserService",""+userService);
        if (userService == null) {
            userService = new ApiClient().getService();
        }
            RegisterRequest registerRequest = new RegisterRequest("name", "email", "zipcode", "gender", "seeking", "date", "pass");
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
                // email.setError("Enter a Valid email");
                email.requestFocus();

            }else if (Zip.isEmpty()) {
                zip.setError("Zip Code is required");
                zip.requestFocus();
            } else if (Update.isEmpty()) {
                update.setError("Birthday Date is required");
                update.requestFocus();
            }else if (!Passwor.equals(Confirm)) {
                confrim.setError("Mismatch Password");
                confrim.requestFocus();
            }else if (Passwor.length() < 6) {
                // password.setError("Password should be at aleast 6 character long");
                password.requestFocus();

            }else if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(zip.getText().toString()) || TextUtils.isEmpty(update.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ||
                    TextUtils.isEmpty(confrim.getText().toString()) || TextUtils.isEmpty(mangender.getText().toString()) || TextUtils.isEmpty(womangender.getText().toString()) || TextUtils.isEmpty(seekingman.getText().toString()) || TextUtils.isEmpty(wantwoman.getText().toString())) {
                String message = "All inputs required";
                Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
            } else {
                registerRequest.setFullname(Fullname);
                registerRequest.setEmail(Email);
                registerRequest.setZipcode(Zip);
                registerRequest.setBirth_date(Update);
                registerRequest.setPassword(Passwor);
                registerRequest.setPassword_confirmation(Confirm);
                registerRequest.setGender(chose);
                registerRequest.setSeeking(interest);
                requestUser(registerRequest);
            }

            Call<RegisterResponse> call = userService.registerUsers(registerRequest);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful()) {
                     // Toast.makeText(SignUp.this, response.body().getToken(), Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(SignUp.this, Verification1.class);
                        startActivity(intent);

                    } else {
                        String message = "An error occured please try again";
                        Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    String message = "An error occured please try again";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                }
            });

        }





    private void getSecret() {

        Call<ResponseBody> call = ApiClient.getSecret(token);
        if (userService == null)
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {
                        try {
                            Toast.makeText(SignUp.this, response.body().string(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "error", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(SignUp.this, "error", Toast.LENGTH_LONG).show();

                }
            });

    }

}
