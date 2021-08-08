package com.example.waam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.LogLevel;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

//import com.sinch.android.rtc.SinchError;

public class Login extends BaseActivity{
        //BaseActivity implements  SinchService.StartFailedListener{
    private TextView signup;
    private ImageView logm;
    private TextView text;
    private TextView pressback;
    private String loginToken;
    private EditText editPass;
    private EditText editEmail;
    private String Email, token;
    private String Password;
    private ProgressDialog mSpinner;
    FirebaseAuth mAuth;

    private static String applicationID = "4663";
    private static String authKey = "RWV8dBeCsCh6g2a";
    private static String authSecret = "yhuExsebKPu8F8S";
    private static String accountKey = "BqZHeqx5VVn9myVe4FY1";

    private int id = 0;

    final String url_Login = "http://ec2-52-36-253-117.us-west-2.compute.amazonaws.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        token = SharedPref.getInstance(this).getStoredToken();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//I stopped here thank you
       // this.makeLogin();
        ConnectycubeSettings.getInstance().init(getApplicationContext(), applicationID, authKey, authSecret);
        ConnectycubeSettings.getInstance().setAccountKey(accountKey);

        ConnectycubeSettings.getInstance().setLogLevel(LogLevel.NOTHING);

       id = SharedPref.getInstance(this).getStoredConnectid();


        signup = findViewById(R.id.again);
        pressback = findViewById(R.id.back);
        Log.d("TAG", "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

        logm = findViewById(R.id.button);
        text = findViewById(R.id.dateofbirth);

        editEmail = findViewById(R.id.editText2);
        editPass = findViewById(R.id.editText4);


        pressback.setOnClickListener(v -> GoBack());
        text.setOnClickListener(v -> AnotherActivity());
        signup.setOnClickListener(v -> SignUnpage());

        Log.d("show", "show");




        logm.setOnClickListener(v -> {
            //user = editEmail.getText().toString();
            Email = editEmail.getText().toString();
            Password = editPass.getText().toString();

            new loginAut().execute(Email, Password);

            if (TextUtils.isEmpty(editEmail.getText().toString()) || TextUtils.isEmpty(editPass.getText().toString())) {
                String message = "All inputs required";
                Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();


            } else {
                LoginRequest loginRequest = new LoginRequest("email", "password");
                loginRequest.setEmail(editEmail.getText().toString());
                loginRequest.setPassword(editPass.getText().toString());



                Log.d("accesstoken", mAuth
                        .getInstance()
                        .getCurrentUser()
                        .getIdToken(true)
                        .getResult().getToken());

                String projectId = "waam-96a1b";
                    /*String accessToken = mAuth
                            .getInstance()
                            .getCurrentUser()
                            .getIdToken(true);*/

                String phon = mAuth.getInstance().getCurrentUser().getPhoneNumber();

                Log.d("currentUserPhonenumbe", ""+phon);
                mAuth.getInstance()
                        .getCurrentUser()
                        .getIdToken(true)
                        .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                            @Override
                            public void onComplete(@NonNull Task<GetTokenResult> task) {

                                if (task.isSuccessful()){
                                    String idToken = task.getResult().getToken();

                                    ConnectycubeUsers.signInUsingFirebase(projectId, idToken).performAsync(new EntityCallback<ConnectycubeUser>() {
                                        @Override
                                        public void onSuccess(ConnectycubeUser user, Bundle args) {


                                          //  assert response.body() != null;
                                          //  String loginToken = response.body().getToken();
                                         //   SharedPref.getInstance(context).setStoredToken(SharedPref.TOKEN, loginToken);


                                            Log.d("Connecticubeusers", user.getEmail());
                                            //Log.d("show", loginToken);
                                            Intent intent = new Intent(Login.this, DiscoverDrawerLayerout.class);
                                         //   Log.d("LoginToken", loginToken);
                                            intent.putExtra("toking", loginToken);
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onError(ResponseException error) {
                                            Log.d("Connecticubeusers", error.getMessage());


                                        }
                                    });


                                }else {
                                    Log.d("Connecticubeusers", "there was an error");

                                }
                            }
                        });
                /*String projectId = "...";
                String accessToken = "...";

               /* ConnectycubeUsers.signInByEmail(Email, Password).performAsync(new EntityCallback<ConnectycubeUser>() {
                    @Override
                    public void onSuccess(ConnectycubeUser user, Bundle args) {

                        Log.d("suscesssignin", ""+user.getFullName());

                    }

                    @Override
                    public void onError(ResponseException error) {
                        Log.d("suscesssignin", ""+error);


                    }
                });*/


              /* final ConnectycubeUser user = new ConnectycubeUser();
                user.setLogin(Email);
                user.setPassword(Password);

                ConnectycubeUsers.signIn(user).performAsync(new EntityCallback<ConnectycubeUser>() {
                    @Override
                    public void onSuccess(ConnectycubeUser user, Bundle args) {
                        Log.d("suscesssignin", ""+user.getFullName());

                        Log.d("suscesssignin", ""+user.getId());

                        Intent intent = new Intent(Login.this, DiscoverDrawerLayerout.class);
//                        Log.d("LoginToken", loginToken);
                        intent.putExtra("toking", loginToken);
                       startActivity(intent);
                    }

                    @Override
                    public void onError(ResponseException error) {
                        Log.d("suscesssignin3", ""+user.getFullName());


                    }
                });*/

                GeneralFactory.getGeneralFactory(Login.this).loginToFireBase(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest);
                //GeneralFactory.getGeneralFactory(Login.this).loginToFireBase(loginRequest.getEmail(),loginRequest.getPassword(),loginRequest,mRtmClient);
                //loginUser(loginRequest);

            }

        });




        ///mSpinner.dismiss();
    }

    private void showSpinner() {
        mSpinner = new ProgressDialog(this);
        mSpinner.setTitle("Logging in");
        mSpinner.setMessage("Please wait...");
        mSpinner.show();
    }


    private void Themain() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        // finish();
    }
    private void AnotherActivity() {
        Intent intent = new Intent(Login.this, Another.class);
        startActivity(intent);
        // finish();
    }

    private void SignUnpage() {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
        // finish();
    }


    private void GoBack() {
        Intent intent = new Intent(Login.this, ViewPager.class);
        startActivity(intent);
    }



    @Override
    protected void onPause() {
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
        super.onPause();
    }

    private void openPlaceCallActivity() {

    }

    private class loginAut extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String Email = strings[0];
            String Password = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("user_id", Email)
                    .add("user_password", Password)
                    .build();

            Request request = new Request.Builder()
                    .url(url_Login)
                    .post(formBody)
                    .build();

            okhttp3.Response response = null;

            try {
                response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()){
                    String result = response.body().string();
                    if (result.equalsIgnoreCase("login")){
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }else {
                        Toast.makeText(Login.this,"Email or Password mismatch!", Toast.LENGTH_LONG).show();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }



}