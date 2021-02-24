package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private TextView lologin;
    String numberToPass = "1";
    private TextView back, bender, iam, seeking, save, want;
    private ImageView move;
    private Button update;
    UserService userService;
    private EditText name, email, zip, password, confrim;
    String gende = "";
    String wo = "woman";


    private static String token;
    //final String url_Register = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initDatePicker();

        seeking = findViewById(R.id.textView12);
        save = findViewById(R.id.editText3);
        want = findViewById(R.id.editText6);
        bender = findViewById(R.id.textView9);
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

        bender.setText("Man");
        iam.setText("Woman");


        lologin.setOnClickListener(v -> Signinhere());
        back.setOnClickListener(v -> Signback());
        update.setText(getTodaysDate());

        bender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bender.setText("Man");
               // gende.setText(gender.getText().toString());
                bender.setBackgroundResource(R.drawable.section_tabs_active_1);
                //  String Gender = gender.getText().toString();

                iam.setBackgroundResource(R.drawable.border);



                //if (Gender.equals(gender)){

                //gender.setText(gende);

                //} else {
                // gender.setText("female");
                // }


            }
        });


        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

                String Fullname = name.getText().toString();
                String Email = email.getText().toString();
                String Zip = zip.getText().toString();
                String Update = update.getText().toString();
                String Passwor = password.getText().toString();
                String Confirm = confrim.getText().toString();
               String Gender = bender.getText().toString();
                String Iam = iam.getText().toString();
               String Seeking = seeking.getText().toString();
                String Want = want.getText().toString();

                if (Fullname.isEmpty()) {
                    //name.setError("Full Name is required");
                    name.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    // email.setError("Enter a Valid email");
                    email.requestFocus();
                    return;
                }

                if (Zip.isEmpty()) {
                   // zip.setError("Zip Code is required");
                    zip.requestFocus();
                    return;
                }
                if (Update.isEmpty()) {
                   // update.setError("Birthday Date is required");
                    update.requestFocus();
                    return;
                }
                if (!Passwor.equals(Confirm)) {
                   // confrim.setError("Mismatch Password");
                    confrim.requestFocus();
                    return;
                }

                if (Passwor.length() < 6) {
                    // password.setError("Password should be at aleast 6 character long");
                    password.requestFocus();
                    return;
                }

                //new RegisterUser().execute(Fullname, Email, Zip, Update, Passwor, Confirm, Gender, Iam,Seeking, Want);
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(zip.getText().toString()) || TextUtils.isEmpty(update.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ||
                        TextUtils.isEmpty(confrim.getText().toString())  || TextUtils.isEmpty(seeking.getText().toString()) || TextUtils.isEmpty(want.getText().toString())) {
                    String message = "All inputs required";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                } else {
                    RegisterRequest registerRequest = new RegisterRequest("name", "email", "zipcode", "", "", "date", "", "password");
                    registerRequest.setFullname(name.getText().toString());
                    registerRequest.setEmail(email.getText().toString());
                    registerRequest.setZipcode(zip.getText().toString());
                    registerRequest.setBirth_date(update.getText().toString());
                    registerRequest.setPassword(password.getText().toString());
                    registerRequest.setPassword_confirmation(password.getText().toString());
                    //registerRequest.getGender();
                    //gende = registerRequest.getGender();
                    //gender.setText(gende);
                  //  iam.setText(gende);

                    //gende = registerRequest.getSeeking();
                   // registerRequest.getSeeking();
                  //  gender.setText(gende);
                   // iam.setText(gende);

                    registerRequest.setGender(bender.getText().toString());
                  // registerRequest.setGender(iam.getText().toString());
                    registerRequest.setSeeking(seeking.getText().toString());
                   //registerRequest.setSeeking(want.getText().toString());
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
                    response.body().getToken();

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




    /*public void colorme(View view) {
        if (view.getId() == gender.getId()) {

            gender.setBackgroundResource(R.drawable.section_tabs_active_1);
            iam.setBackgroundResource(R.drawable.border);

        }

    }*/

    public void woooo(View view) {
        if (view.getId() == iam.getId()) {
            iam.setBackgroundResource(R.drawable.sectiontabsactive2);
            bender.setBackgroundResource(R.drawable.border2);

        }
    }

    public void who(View view) {
        if (view.getId() == seeking.getId()) {
            seeking.setBackgroundResource(R.drawable.section_tabs_active_1);
            want.setBackgroundResource(R.drawable.border);

        }
    }

    public void sek(View view) {
        if (view.getId() == want.getId()) {
            want.setBackgroundResource(R.drawable.sectiontabsactive2);
            seeking.setBackgroundResource(R.drawable.border2);
           
        }
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private void login() {
        if (userService == null)
            userService = new ApiClient().getService();

        RegisterRequest registerRequest = new RegisterRequest("name", "email", "zipcode", "", "", "date", "password", "password");
        Call<RegisterResponse> call = userService.registerUsers(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(SignUp.this, response.body().getToken(), Toast.LENGTH_LONG).show();
                    token = response.body().getToken();

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
