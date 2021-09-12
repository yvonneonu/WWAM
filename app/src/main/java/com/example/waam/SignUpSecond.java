package com.example.waam;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.sinch.android.rtc.SinchError;


public class SignUpSecond extends BaseActivity implements SinchService.StartFailedListener, View.OnClickListener {

    private GeneralFactory generalFactory;
    private CardView progressBar;
    private EditText name,email,zip,password,confrim;
    private Button update;
    private String gender;
    private String  interest;
    private String relationship ;
    private DatePickerDialog datePickerDialog;
    private UserService userService;
    private ProgressDialog mSpinner;
    ImageView imageView, showpass, confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_second);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE},100);
        }
        Bundle bundle = getIntent().getExtras();

        initDatePicker();

        gender = bundle.getString(SignUp.GENDER);
        interest = bundle.getString(SignUp.INTEREST);
        relationship = bundle.getString(SignUp.RELATION);
        update = findViewById(R.id.dateofbirth);
        generalFactory = GeneralFactory.getGeneralFactory(this);
        progressBar = findViewById(R.id.cardview);
        name = findViewById(R.id.editText8);
        email = findViewById(R.id.editText2);
        zip = findViewById(R.id.editText4);
        password = findViewById(R.id.editText);
        confrim = findViewById(R.id.editText88);
        imageView = findViewById(R.id.logo);
        showpass = findViewById(R.id.show_pass_btn);
        confirm = findViewById(R.id.confirm);

        showpass.setOnClickListener(this);
        confirm.setOnClickListener(this);

        update.setText(getTodaysDate());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailableAndConnected()){
                    register();
                    //constraintLayout.setVisibility(View.INVISIBLE);
                    //textView.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // constraintLayou.setVisibility(View.GONE);
                            // constraintLayout.setVisibility(View.VISIBLE);
                            // textView.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                }
                else {
                    Toast.makeText(SignUpSecond.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();
        return isNetworkConnected;
    }

    private void register() {

        Log.d("UserService",""+userService);
        if (userService == null) {
            userService = new ApiClient().getService();
        }

        WaamUser waamUser = new WaamUser("name", "email", "zipcode", "gender", "seeking", "date", "pass");


        String fullname = name.getText().toString();
        String Email = email.getText().toString();
        String Zip = zip.getText().toString();
        String Update = update.getText().toString();
        String Passwor = password.getText().toString();
        String Confirm = confrim.getText().toString();


        if(fullname.isEmpty()) {
            name.setError("Full Name is required");
            name.requestFocus();
        }else if(!generalFactory.validateName(fullname)){
            name.setError("Pls submit full name");
            name.requestFocus();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Enter a Valid email");
            email.requestFocus();

        }else if (Zip.isEmpty()) {
            zip.setError("Zip Code is required");
            zip.requestFocus();
        } else if (Update.isEmpty()) {
            update.setError("Birthday Date is required");
            update.requestFocus();
        }else if (!Passwor.equals(Confirm)) {
            confrim.setError("Wrong Password");
            confrim.requestFocus();
        }else if (gender.isEmpty()) {
            Toast.makeText(SignUpSecond.this, "Choose your gender", Toast.LENGTH_LONG).show();
        }else if (interest.isEmpty()) {
            Toast.makeText(SignUpSecond.this, "Choose your gender", Toast.LENGTH_LONG).show();
        }else if (Passwor.length() < 6) {
            // password.setError("Password should be at aleast 6 character long");
            password.requestFocus();

        }else if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(zip.getText().toString()) || TextUtils.isEmpty(update.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ||
                TextUtils.isEmpty(confrim.getText().toString())) {
            String message = "All inputs required";
            Toast.makeText(SignUpSecond.this, message, Toast.LENGTH_LONG).show();
        } else {

         //   SharedPref.getInstance(this).setStoredToken(SharedPref.TOKEN, Token);
            //Log.d("meemmemememe", ""+user);
            SharedPref.getInstance(this).setStoredName(SharedPref.FULLNAME,fullname);
            waamUser.setFullname(fullname);
            waamUser.setEmail(Email);
            waamUser.setZipcode(Zip);
            waamUser.setBirth_date(Update);
            waamUser.setPassword(Passwor);
            waamUser.setPassword_confirmation(Confirm);
            waamUser.setGender(gender);
            waamUser.setSeeking(interest);
            waamUser.setRelationship(relationship);


            if (!Email.equals(getSinchServiceInterface().getUserName())) {
                getSinchServiceInterface().stopClient();

            }
            if (!getSinchServiceInterface().isStarted()){
                getSinchServiceInterface().startClient(Email);
                Log.d("chek", Email);

            }

            generalFactory.requestUser(waamUser,progressBar);
            //requestUser(waamUser);

        }

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

   /* private void showSpinner() {
        mSpinner = new ProgressDialog(this);
        mSpinner.setTitle("Logging in");
        mSpinner.setMessage("Please wait...");
        mSpinner.show();
    }*/
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

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        // return getMonthFormat(month) + "-" + dayOfMonth + "-" + year;
        return year + "-" + getMonthFormat(month) + "-" + dayOfMonth;
    }

    private String getTodaysDate() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int year = c.get(Calendar.YEAR);
        return makeDateString(day, month, year);
    }

    public void login(View view) {
        Intent intent = new Intent(SignUpSecond.this, Login.class);
        startActivity(intent);
    }


    @Override
    public void onStartFailed(SinchError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
        // if (mSpinner != null) {
        //   mSpinner.dismiss();

    }

    @Override
    public void onStarted() {

    }
    @Override
    protected void onServiceConnected() {
        imageView.setEnabled(true);
        getSinchServiceInterface().setStartListener(this);

    }

    @Override
    public void onClick(View v) {
        final int hidepass = R.id.show_pass_btn;
        final int hideComfirm = R.id.confirm;

        switch (v.getId()){
            case hidepass:
                if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                        showpass.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else {
                    showpass.setImageResource(R.drawable.ic_baseline_visibility_24);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                password.setSelection(password.getText().length());

                break;
            case hideComfirm:
                if (confrim.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    confirm.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    confrim.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    confirm.setImageResource(R.drawable.ic_baseline_visibility_24);
                    confrim.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                confrim.setSelection(confrim.getText().length());

        }


    }
   /* @Override
    protected void onPause() {
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
        super.onPause();
    }*/
}