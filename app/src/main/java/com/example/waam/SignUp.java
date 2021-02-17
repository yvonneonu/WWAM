package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private TextView lologin, name, email, password, confrim;
    private TextView back, gender, zip, iam, seeking, want, save;
    private ImageView move;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initDatePicker();

        seeking = findViewById(R.id.textView12);
        save = findViewById(R.id.editText3);
        want = findViewById(R.id.editText6);
        gender = findViewById(R.id.textView9);
        iam = findViewById(R.id.editText7);
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
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(zip.getText().toString()) || TextUtils.isEmpty(update.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(confrim.getText().toString()) || TextUtils.isEmpty(gender.getText().toString()) || TextUtils.isEmpty(iam.getText().toString()) ||
                        TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(confrim.getText().toString())) {
                    String message = "All inputs required";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                } else {
                    RegisterRequest registerRequest = new RegisterRequest("name", "email", "zipcode", "gender", "seeking", "birth date", "password", "confirm password");
                    registerRequest.setFullname(name.getText().toString());
                    registerRequest.setEmail(email.getText().toString());
                    registerRequest.setZipcode(zip.getText().toString());
                    registerRequest.setBirth_date(update.getText().toString());
                    registerRequest.setPassword(password.getText().toString());
                    registerRequest.setPassword_confirmation(confrim.getText().toString());
                    registerRequest.setGender(gender.getText().toString());
                    registerRequest.setGender(iam.getText().toString());
                    registerRequest.setSeeking(seeking.getText().toString());
                    registerRequest.setSeeking(want.getText().toString());
                    requestUser(registerRequest);
                }
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
                    //response.body();
                    String message = "Successful";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();

                    startActivity(new Intent(SignUp.this, Login.class));
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
        if (view.getId() == gender.getId()) {
            gender.setBackgroundResource(R.color.blue);
            iam.setBackgroundResource(R.color.bpTransparent);
        }

    }

    public void woooo(View view) {
        if (view.getId() == iam.getId()) {
            iam.setBackgroundResource(R.color.blue);
            gender.setBackgroundResource(R.color.bpTransparent);
        }
    }

    public void who(View view) {
        if (view.getId() == seeking.getId()) {
            seeking.setBackgroundResource(R.color.blue);
            want.setBackgroundResource(R.color.bpTransparent);
        }
    }

    public void sek(View view) {
        if (view.getId() == want.getId()) {
            want.setBackgroundResource(R.color.blue);
            seeking.setBackgroundResource(R.color.bpTransparent);
        }
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}
