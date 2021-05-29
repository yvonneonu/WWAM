package com.example.waam;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SignUp extends AppCompatActivity {

    private TextView mangender;
    private TextView womangender;
    private TextView seekingman;
    private TextView wantwoman;
    //private String realGender, realInterest;
    private ImageView move;
    //private Button update;
    //private ProgressBar progressBar;

    //ConstraintLayout constraintLayou;
    private String chose;
    private String interest;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String relationship = "";
    public static final String INTEREST = "interest";
    public static final String GENDER = "interest";
    public static final String RELATION = "interest";
    public static String token;
    //final String url_Register = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initDatePicker();

        seekingman = findViewById(R.id.seekman);
        wantwoman = findViewById(R.id.seekwoman);
        mangender = findViewById(R.id.mangend);
        womangender = findViewById(R.id.womangend);
        //update = findViewById(R.id.forgetpass);
        //private DatePickerDialog datePickerDialog;
        TextView lologin = findViewById(R.id.gologin);
        TextView back = findViewById(R.id.backto);
        move = findViewById(R.id.logo);
        radioGroup = findViewById(R.id.radioGroup1);
        ConstraintLayout constraintLayout = findViewById(R.id.notshow);
        lologin.setOnClickListener(v -> Signinhere());
        back.setOnClickListener(v -> Signback());
        //update.setText(getTodaysDate());


        move.setOnClickListener(v -> {

          if(TextUtils.isEmpty(chose) || TextUtils.isEmpty(interest)){
              Log.d("Empty","Gender or interest cannot be null");
            }else{
              int selectedId = radioGroup.getCheckedRadioButtonId();
              radioButton = findViewById(selectedId);
              if (radioButton != null){
                  relationship = radioButton.getText().toString();
              }

              if(!TextUtils.isEmpty(relationship)){
                  Intent intent = new Intent(SignUp.this,SignUpSecond.class);
                  Bundle bundle = new Bundle();
                  Log.d("Relay",relationship);
                  bundle.putString(INTEREST, interest);
                  bundle.putString(GENDER,chose);
                  bundle.putString(RELATION,relationship);
                  intent.putExtras(bundle);
                  startActivity(intent);
              }else{
                  Log.d("Relationship","Please select the relationship you want");
                  Log.d("Relay",relationship);
                  String message = "Select an option";
                  Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
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

    /*public void requestUser(WaamUser waamUser) {
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
                    GeneralFactory.getGeneralFactory(SignUp.this).signUpForBase(waamUser.getEmail(), waamUser.getPassword(),progressBar, waamUser);
                    Intent intent = new Intent(SignUp.this, Verification1.class);
                    intent.putExtra("token", response.body().getToken());
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
    }*/


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
                //update.setText(date);
            }
        };
        java.util.Calendar c = java.util.Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        int styles = AlertDialog.THEME_HOLO_LIGHT;

        //datePickerDialog = new DatePickerDialog(this, styles, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

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

    /*public void openDatePicker(View view) {
        datePickerDialog.show();
    }*/

    /*private void register() {


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



        }*/
}
