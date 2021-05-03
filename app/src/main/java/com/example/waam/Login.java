package com.example.waam;

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

import androidx.appcompat.app.AppCompatActivity;

import com.connectycube.auth.session.ConnectycubeSessionManager;
import com.connectycube.auth.session.ConnectycubeSettings;
import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.core.EntityCallback;
import com.connectycube.core.LogLevel;
import com.connectycube.core.exception.ResponseException;
import com.connectycube.core.request.PagedRequestBuilder;
import com.connectycube.users.ConnectycubeUsers;
import com.connectycube.users.model.ConnectycubeAddressBookContact;
import com.connectycube.users.model.ConnectycubeAddressBookResponse;
import com.connectycube.users.model.ConnectycubeUser;

import java.util.ArrayList;
import java.util.List;

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
    PagedRequestBuilder pagedRequestBuilder = new PagedRequestBuilder();
    String loginToken;
    private EditText editPass;
    private EditText editEmail;
    String Email;
    String Password;
    String password;
    int id;
    boolean isCompact = true;

    // int id1;
   String UDID = null;
    boolean force = true;
    //int id4;
    String pasword;
    private ConnectycubeChatService chatService;

    boolean isSignedIn = ConnectycubeSessionManager.getInstance().getSessionParameters() != null;


    static final String APP_ID = "4663";
    static final String AUTH_KEY = "RWV8dBeCsCh6g2a";
    static final String AUTH_SECRET = "yhuExsebKPu8F8S";
    static final String ACCOUNT_KEY = "tBL4Vzzzj7fQMfzsHYii";
//


 // int b = 4152184;



    final String url_Login = "http://ec2-54-188-200-48.us-west-2.compute.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //initcomit();

        ConnectycubeSettings.getInstance().init(getApplicationContext(), APP_ID, AUTH_KEY, AUTH_SECRET);
        ConnectycubeSettings.getInstance().setAccountKey(ACCOUNT_KEY);

        pagedRequestBuilder.setPage(1);
        pagedRequestBuilder.setPerPage(50);

        ConnectycubeSettings.getInstance().setLogLevel(LogLevel.NOTHING);
        chatService = ConnectycubeChatService.getInstance();

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


        ConnectycubeChatService.ConfigurationBuilder chatServiceConfigurationBuilder = new ConnectycubeChatService.ConfigurationBuilder();
        chatServiceConfigurationBuilder.setSocketTimeout(60);
        chatServiceConfigurationBuilder.setKeepAlive(true);
        chatServiceConfigurationBuilder.setUseTls(true); //By default TLS is disabled.
        ConnectycubeChatService.setConfigurationBuilder(chatServiceConfigurationBuilder);


       //String loginsToken = ConnectycubeSessionManager.getInstance().getToken();
        //loginToken = ConnectycubeSessionManager.getInstance().getToken();
        Log.d("show", "show");


       /* ConnectionListener connectionListener = new ConnectionListener() {
            @Override
            public void connected(XMPPConnection connection) {

                Log.d("show", "show"+connection.isConnected());

            }

            @Override
            public void authenticated(XMPPConnection xmppConnection, boolean b) {
                Log.d("show", "show"+b);

            }


            @Override
            public void connectionClosed() {
                Log.d("show", "showmore");

            }

            @Override
            public void connectionClosedOnError(Exception e) {
                Log.d("show", "show"+e.getMessage());

            }

            @Override
            public void reconnectingIn(int seconds) {
                Log.d("show", "show"+seconds);

            }

            @Override
            public void reconnectionSuccessful() {
                Log.d("show", "here");

            }

            @Override
            public void reconnectionFailed(Exception e) {
                Log.d("show", ""+e.getMessage());

            }
        };

        ConnectycubeChatService.getInstance().addConnectionListener(connectionListener);*/

        List<Integer> usersIds = new ArrayList<>();
        usersIds.add(4134562);
        usersIds.add(4155439);

        Bundle params = new Bundle();

        ConnectycubeUsers.getUsersByIDs(usersIds, pagedRequestBuilder, params).performAsync(new EntityCallback<ArrayList<ConnectycubeUser>>() {
            @Override
            public void onSuccess(ArrayList<ConnectycubeUser> users, Bundle args) {

                Log.d("hereeee", ""+users.toString());
            }

            @Override
            public void onError(ResponseException error) {

            }
        });

        ArrayList<ConnectycubeAddressBookContact> contactsGlobal = new ArrayList<>();

        ConnectycubeAddressBookContact contact = new ConnectycubeAddressBookContact();
        contact.setPhone("13656516112");
        contact.setName("Bob Bobson");

        contactsGlobal.add(contact);

        ConnectycubeUsers.uploadAddressBook(contactsGlobal, UDID, force).performAsync(new EntityCallback<ConnectycubeAddressBookResponse>() {
            @Override
            public void onSuccess(ConnectycubeAddressBookResponse result, Bundle params) {

                Log.d("tt", ""+result.toString());
            }

            @Override
            public void onError(ResponseException responseException) {

                Log.d("ff", ""+responseException.getMessage());
            }
        });


        ConnectycubeUsers.getRegisteredUsersFromAddressBook(UDID, isCompact).performAsync(new EntityCallback<ArrayList<ConnectycubeUser>>() {
            @Override
            public void onSuccess(ArrayList<ConnectycubeUser> users, Bundle params) {

                Log.d("dd", ""+users.toString());
            }

            @Override
            public void onError(ResponseException responseException) {

                Log.d("gg", ""+responseException.getMessage());
            }
        });

        logm.setOnClickListener(v -> {
            //user = editEmail.getText().toString();
            Email = editEmail.getText().toString();
            Password = editPass.getText().toString();

            new loginAut().execute(Email, Password);


            if (TextUtils.isEmpty(editEmail.getText().toString()) || TextUtils.isEmpty(editPass.getText().toString())){
                String message = "All inputs required";
                Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();

            }else {


                final ConnectycubeUser user = new ConnectycubeUser();
               // user.setId(4152184);
             //   user.setLogin("Grace");
                user.setEmail(Email);
                user.setPassword(Password);


               // final ConnectycubeUser user1 = new ConnectycubeUser();
                // user.setId(4152184);

                user.setId(4152184);
                user.setPassword("435");
                chatService.login(user, new EntityCallback() {


                    @Override
                    public void onSuccess(Object o, Bundle bundle) {

                    }

                    @Override
                    public void onError(ResponseException errors) {

                    }
                });




             /*   user.setId(4152184);
                user.setPassword("12345678");
                chatService.login(user, new EntityCallback() {


                    @Override
                    public void onSuccess(Object o, Bundle bundle) {

                    }

                    @Override
                    public void onError(ResponseException errors) {

                    }
                });*/


                ConnectycubeUsers.signIn(user).performAsync(new EntityCallback<ConnectycubeUser>() {
                    @Override
                    public void onSuccess(ConnectycubeUser userj, Bundle args) {

                         id = user.getId();
                         password = user.getPassword().toString();

                        Log.d("doraaa", ""+userj.getId());
                        Log.d("doraaa", ""+userj.getLogin());
                        Log.d("doraaa", ""+userj.getEmail());
                        Log.d("doraa",userj.getFullName());
                    }

                    @Override
                    public void onError(ResponseException error) {

                        Log.d("error", ""+error.getMessage());
                    }
                });
                LoginRequest loginRequest = new LoginRequest("email", "password");
              //  loginRequest.se
                loginRequest.setEmail(editEmail.getText().toString());
                loginRequest.setPassword(editPass.getText().toString());


               loginUser(loginRequest);
            }
        });

    }



    public void loginUser(LoginRequest loginRequest){

        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()){
                    loginToken = response.body().getToken();
                    Intent intent = new Intent(Login.this, DiscoverDrawerLayerout.class);

                    //Log.d(int, id);
                    Log.d("LoginTOken",loginToken);

                  //  Intent intent = new Intent(Login.this, Profile.class);
                    //Intent intent = new Intent(Login.this, DrawelayoutActivity.class);
                  //  Intent intent = new Intent(Login.this,finalProfile.class);
                    intent.putExtra("toking",loginToken);
                    intent.putExtra("id", id);
                    //intent.putExtra(String.valueOf(id1), id);
                    intent.putExtra("password", password);
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