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
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.LogLevel;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.core.helper.StringifyArrayList;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;

import java.util.ArrayList;
import java.util.Iterator;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

//import com.sinch.android.rtc.SinchError;

public class SignUpSecond extends AppCompatActivity{
        //BaseActivity implements  SinchService.StartFailedListener {
        private static String applicationID = "4663";
    private static String authKey = "RWV8dBeCsCh6g2a";
    private static String authSecret = "yhuExsebKPu8F8S";
    private static String accountKey = "BqZHeqx5VVn9myVe4FY1";

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
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_second);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ConnectycubeSettings.getInstance().init(getApplicationContext(), applicationID, authKey, authSecret);
        ConnectycubeSettings.getInstance().setAccountKey(accountKey);

        ConnectycubeSettings.getInstance().setLogLevel(LogLevel.NOTHING);

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




    private boolean isUsersEmpty(ArrayList users) {
        Iterable $this$forEach$iv = (Iterable)users;
        boolean $i$f$forEach = false;
        Iterator var4 = $this$forEach$iv.iterator();

        String var10000;
        do {
            if (!var4.hasNext()) {
                return false;
            }

            Object element$iv = var4.next();
            ConnectycubeUser user = (ConnectycubeUser)element$iv;
            boolean var7 = false;
            var10000 = user.getLogin();
            Intrinsics.checkExpressionValueIsNotNull(var10000, "user.login");
            if (StringsKt.isBlank((CharSequence)var10000)) {
                break;
            }

            var10000 = user.getPassword();
            Intrinsics.checkExpressionValueIsNotNull(var10000, "user.password");
        } while(!StringsKt.isBlank((CharSequence)var10000));

        return true;
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




            final ConnectycubeUser user = new ConnectycubeUser(Email, Passwor);
            user.setLogin(Email);
            user.setPassword(Passwor);
            user.setEmail(Email);
            user.setFullName(fullname);
            user.setPhone("47802323143");
            user.setWebsite("https://dozensofdreams.com");
            StringifyArrayList<String> tags = new StringifyArrayList<String>();
            tags.add("iphone");
            tags.add("apple");
            user.setTags(tags);

            Log.d("timemail", "usersignup"+user.getEmail());


            ConnectycubeUsers.signUp(user).performAsync(new EntityCallback<ConnectycubeUser>() {
                @Override
                public void onSuccess(ConnectycubeUser user, Bundle args) {

                    Log.d("time", ""+user.getId());
                }

                @Override
                public void onError(ResponseException error) {
                    Log.d("time", ""+error);


                }
            });


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



   /* @Override
    protected void onPause() {
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
        super.onPause();
    }*/
}