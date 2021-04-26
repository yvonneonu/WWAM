package com.example.waam;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.connectycube.auth.session.ConnectycubeSessionManager;
import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.LogLevel;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.users.model.ConnectycubeUser;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private TextView signup;
    private ImageView logm;
    private TextView text;
    private TextView pressback;
    private String loginToken;
    private EditText editPass;
    private EditText editEmail;
    String Email;
    String Password;

    final ConnectycubeUser user = new ConnectycubeUser();

    String token = ConnectycubeSessionManager.getInstance().getToken();

    final String url_Login = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        Email = ConnectycubeSessionManager.getInstance().getToken();

        Password = ConnectycubeSessionManager.getInstance().getToken();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initcomit();
        ConnectycubeChatService.ConfigurationBuilder chatServiceConfigurationBuilder = new ConnectycubeChatService.ConfigurationBuilder();


        ConnectycubeChatService chatService = ConnectycubeChatService.getInstance();


        chatServiceConfigurationBuilder.setSocketTimeout(60);
        chatServiceConfigurationBuilder.setKeepAlive(true);
        chatServiceConfigurationBuilder.setUseTls(true); //By default TLS is disabled.
        ConnectycubeChatService.setConfigurationBuilder(chatServiceConfigurationBuilder);



        ConnectycubeSettings.getInstance().setLogLevel(LogLevel.NOTHING);
        signup = findViewById(R.id.again);
        pressback = findViewById(R.id.back);
        Log.d("TAG", "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

        logm = findViewById(R.id.button);
        text = findViewById(R.id.forgetpass);

        editEmail = findViewById(R.id.editText2);
        editPass = findViewById(R.id.editText4);


        pressback.setOnClickListener(v -> GoBack());
        text.setOnClickListener(v -> AnotherActivity());
        signup.setOnClickListener(v -> SignUnpage());
      //  logm.setOnClickListener(v -> Themain());


        //user.getEmail();
      // user.getPassword();
      // user.setId(21);

       /// user.setId(4107218);
       // user.setPassword("12345678");
       // user.setLogin("dora@gmail.com");

       //user.getEmail()
        user.setId(23);
      //  user.setEmail(Email);
        user.setPassword(token);
       // user.setEmail(Email);
       //user.setPassword(Password);

        chatService.login(user, new EntityCallback() {
            @Override
            public void onSuccess(Object o, Bundle bundle) {
                Log.d("Tag", ""+user);
            }

            @Override
            public void onError(ResponseException e) {

            }
        });




        ConnectionListener connectionListener = new ConnectionListener() {
            @Override
            public void connected(XMPPConnection connection) {
                Log.d("Tag", ""+user);
            }

            @Override
            public void authenticated(XMPPConnection xmppConnection, boolean b) {

            }


            @Override
            public void connectionClosed() {

            }

            @Override
            public void connectionClosedOnError(Exception e) {

            }

            @Override
            public void reconnectingIn(int seconds) {

            }

            @Override
            public void reconnectionSuccessful() {

            }

            @Override
            public void reconnectionFailed(Exception e) {

            }
        };

        ConnectycubeChatService.getInstance().addConnectionListener(connectionListener);


        logm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //user = editEmail.getText().toString();
                Email = editEmail.getText().toString();
                Password = editPass.getText().toString();

                new loginAut().execute(Email, Password);


                if (TextUtils.isEmpty(editEmail.getText().toString()) || TextUtils.isEmpty(editPass.getText().toString())){
                    String message = "All inputs required";
                    Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();

                }else {

                    LoginRequest loginRequest = new LoginRequest("email", "password");
                    loginRequest.setEmail(editEmail.getText().toString());
                    loginRequest.setPassword(editPass.getText().toString());


                   loginUser(loginRequest);


                }
            }
        });

    }


    private void initcomit() {

        ConnectycubeSettings.getInstance().init(getApplicationContext(), Constant.APP_ID, Constant.AUTH_KEY, Constant.AUTH_SECRET);
        ConnectycubeSettings.getInstance().setAccountKey(Constant.ACCOUNT_KEY);

        ConnectycubeSettings.getInstance().setLogLevel(LogLevel.NOTHING);

    }


    public void loginUser(LoginRequest loginRequest){

        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()){
                    loginToken = response.body().getToken();
                 Intent intent = new Intent(Login.this, DiscoverDrawerLayerout.class);
                  //  Intent intent = new Intent(Login.this, Profile.class);
                    //Intent intent = new Intent(Login.this, DrawelayoutActivity.class);
                  //  Intent intent = new Intent(Login.this,finalProfile.class);
                    intent.putExtra("toking",loginToken);
                    startActivity(intent);
                    //startActivity(new Intent(Login.this, MainActivity.class).putExtra("name", loginResponse));
                    finish();

                }else {
                    String message = "An error occured please try again";
                    Toast.makeText(Login.this,message,Toast.LENGTH_LONG).show();
                    Toast.makeText(Login.this,"Email or Password mismatch!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                //String message = t.getLocalizedMessage();
                Toast.makeText(Login.this, t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
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