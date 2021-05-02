package com.example.waam;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.connectycube.auth.session.ConnectycubeSessionManager;
import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.LogLevel;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private TextView lologin;
    String numberToPass = "1";
    private TextView back, mangender, womangender, seekingman, save, wantwoman, textView;
    private String realGender, realInterest;
    private ImageView move;
    private Button update;
    UserService userService;
    private CardView cardView1;
    private ProgressBar progressBar;
    private EditText name, email, zip, password, confrim;
    //ConstraintLayout constraintLayou;
    String chose = "";
    String interest = "";
    String Fullname;

    private ConnectycubeChatService chatService;

    static final String APP_ID = "4663";
    static final String AUTH_KEY = "RWV8dBeCsCh6g2a";
    static final String AUTH_SECRET = "yhuExsebKPu8F8S";
    static final String ACCOUNT_KEY = "tBL4Vzzzj7fQMfzsHYii";
//



    String Passwor;
    String Email;

    ConstraintLayout constraintLayout;

    private static String token;
    //final String url_Register = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ConnectycubeSettings.getInstance().init(getApplicationContext(), APP_ID, AUTH_KEY, AUTH_SECRET);
        ConnectycubeSettings.getInstance().setAccountKey(ACCOUNT_KEY);


        ConnectycubeSettings.getInstance().setLogLevel(LogLevel.NOTHING);
        chatService = ConnectycubeChatService.getInstance();
        initDatePicker();

        cardView1 = findViewById(R.id.cardview);
        seekingman = findViewById(R.id.seekman);
        save = findViewById(R.id.editText3);
        wantwoman = findViewById(R.id.seekwoman);
        mangender = findViewById(R.id.mangend);
        womangender = findViewById(R.id.womangend);
        update = findViewById(R.id.forgetpass);
        zip = findViewById(R.id.editText4);
        lologin = findViewById(R.id.gologin);
        textView = findViewById(R.id.textView);
        back = findViewById(R.id.backto);
        move = findViewById(R.id.logo);
        name = findViewById(R.id.editText8);
        constraintLayout = findViewById(R.id.notshow);
        email = findViewById(R.id.editText2);
        password = findViewById(R.id.editText);
        confrim = findViewById(R.id.editText88);
        lologin.setOnClickListener(v -> Signinhere());
        back.setOnClickListener(v -> Signback());
        update.setText(getTodaysDate());


        progressBar= new ProgressBar(SignUp.this);

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailableAndConnected()){
                    register();
                    cardView1.setVisibility(View.VISIBLE);
                    constraintLayout.setVisibility(View.INVISIBLE);
                    //textView.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           // constraintLayou.setVisibility(View.GONE);
                            cardView1.setVisibility(View.GONE);
                            constraintLayout.setVisibility(View.VISIBLE);
                           // textView.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                }
                else {
                    Toast.makeText(SignUp.this, "No Internet Connection", Toast.LENGTH_LONG).show();
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

    private String getTodaysDate() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int year = c.get(Calendar.YEAR);
        return makeDateString(day, month, year);
    }

    public void requestUser(WaamUser waamUser) {
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(waamUser);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    //response.body().getToken();
                    //response.body();
                    String message = "Successful";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();

                    //Register users on firebase......
                    GeneralFactory.getGeneralFactory().signUpForBase(waamUser.getEmail(), waamUser.getPassword(),SignUp.this,progressBar, waamUser);
                    Intent intent = new Intent(SignUp.this, Verification1.class);
                    intent.putExtra("token", response.body().getToken());
                    intent.putExtra("name", name.getText().toString());
                    startActivity(intent);
                   // startActivity(new Intent(SignUp.this, Verification1.class).putExtra("token", response.body().getToken()));
                   // intent.putExtra("profilepics", imageUri);
                    finish();
                } else {
                    response.errorBody();
                   // Toast.makeText(SignUp.this,response.body().getErrors(),Toast.LENGTH_LONG).show();
                   // response.body();
                   // Toast.makeText(SignUp.this, (CharSequence) response.body(), Toast.LENGTH_LONG).show();
                    //response.errorBody();
                   // response.errorBody();

                    String message = "The email has already been taken";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();

                    // String message = "An error occured please try again";
                    //Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = "The email has already been taken";
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


            WaamUser waamUser = new WaamUser("name", "email", "zipcode", "gender", "seeking", "date", "pass");

            Fullname = name.getText().toString();
            Email = email.getText().toString();
            String Zip = zip.getText().toString();
            String Update = update.getText().toString();
            Passwor = password.getText().toString();
            String Confirm = confrim.getText().toString();
            if(Fullname.isEmpty()) {
                name.setError("Full Name is required");
                name.requestFocus();
            }else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
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
            }else if (chose.isEmpty()) {
                Toast.makeText(SignUp.this, "Choose your gender", Toast.LENGTH_LONG).show();
            }else if (interest.isEmpty()) {
                Toast.makeText(SignUp.this, "Choose your gender", Toast.LENGTH_LONG).show();
            }else if (Passwor.length() < 6) {
                // password.setError("Password should be at aleast 6 character long");
                password.requestFocus();

            }else if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(zip.getText().toString()) || TextUtils.isEmpty(update.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ||
                    TextUtils.isEmpty(confrim.getText().toString()) || TextUtils.isEmpty(mangender.getText().toString()) || TextUtils.isEmpty(womangender.getText().toString()) || TextUtils.isEmpty(seekingman.getText().toString()) || TextUtils.isEmpty(wantwoman.getText().toString())) {
                String message = "All inputs required";
                Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
            } else {

                final ConnectycubeUser user = new ConnectycubeUser("marvin18", "supersecurepwd");
                user.setLogin(Fullname);
                user.setPassword(Passwor);
                user.setEmail(Email);
                user.setFullName(Fullname);
                user.setPhone("47802323143");
                user.setWebsite("https://dozensofdreams.com");

                String token = ConnectycubeSessionManager.getInstance().getToken();

                ConnectycubeUsers.signUp(user).performAsync(new EntityCallback<ConnectycubeUser>() {
                    @Override
                    public void onSuccess(ConnectycubeUser user, Bundle args) {
                        Log.d("cheicbirbv", ""+user.getId());
                        Log.d("giry", ""+token);
                    }

                    @Override
                    public void onError(ResponseException error) {

                        Log.d("errw", "error");
                    }
                });
                Log.d("meemmemememe", ""+user);

                waamUser.setFullname(Fullname);
                waamUser.setEmail(Email);
                waamUser.setZipcode(Zip);
                waamUser.setBirth_date(Update);
                waamUser.setPassword(Passwor);
                waamUser.setPassword_confirmation(Confirm);
                waamUser.setGender(chose);
                waamUser.setSeeking(interest);
                requestUser(waamUser);


            }



        }
}
