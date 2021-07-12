package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Interest extends AppCompatActivity {

    private TextView Swipe;
   // private CheckBox zeroRadio, oneRadio, twoRadio, threeRadio, fourRadio, fiveRadio, sixRadio, sevenRadio, eightRadio;
  //  private TextView zeroText, oneText, twoText, threeText, fourText, fiveText, sixText, sevenText, eightText;

    private int Dinninout = 1;

    private int Travel = 2;
    private int Sport = 3;
    private int NightclubsorDancing = 4;
    int CoffeeConversation = 5;
    int MusicandArt = 6;
    int MoviesorVideos = 7;
    int MusicConcerts = 8;
    int Winetesting = 9;
    private Button saveInterest;
    private List<String> interest;
    private List<InterestRequest> interestRequestList;
   private String message;
   private String token;
   private String Fullname;
    String imageUri;
   //private String tok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        interest = new ArrayList<>();


      //  token = SharedPref.getInstance(this).getStoredToken(this);

        interestRequestList = new ArrayList<>();

        imageUri = getIntent().getStringExtra("profilepics");
//        Log.d("ImageUriIN",imageUri);
        Fullname = getIntent().getStringExtra("name");
        //token = getIntent().getStringExtra("mytoken");
//        Log.d("display", token);

        token = SharedPref.getInstance(this).getStoredToken();





        Swipe = findViewById(R.id.swipe);

        Swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interest.this, CompleteProfile.class);
                startActivity(intent);
            }
        });
       /*zeroRadio = findViewById(R.id.radioButton);
       // zeroText = findViewById(R.id.textView20);
      //  oneRadio = findViewById(R.id.radioButton1);
        oneText = findViewById(R.id.textView21);
       // twoRadio = findViewById(R.id.radioButton2);
        twoText = findViewById(R.id.textView2);
       // threeRadio = findViewById(R.id.radioButton3);
        threeText = findViewById(R.id.textView3);
        //fourRadio = findViewById(R.id.radioButton4);
        fourText = findViewById(R.id.textView4);
       // fiveRadio = findViewById(R.id.radioButton5);
        fiveText = findViewById(R.id.textView5);
       // sixRadio = findViewById(R.id.radioButton6);
        sixText = findViewById(R.id.textView6);
       // sevenRadio = findViewById(R.id.radioButton8);
        sevenText = findViewById(R.id.textView8);
      //  eightRadio = findViewById(R.id.radioButton9);
        eightText = findViewById(R.id.textView9);*/
        saveInterest = findViewById(R.id.button4);


       /* wipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (interestRequestList != null){
                    Intent intent = new Intent(Interest.this, CompleteProfile.class);

                    // intent.putExtra("nameprofile", Fullname);

                    if (token != null){
                        intent.putExtra("token", token);
                    }
                    if (imageUri != null){
                        intent.putExtra("getProfilePics", imageUri);
                    }
                    intent.putExtra("name", Fullname);

                    Log.d("TAG", ""+Fullname);

                    Log.d("TAG", "TOKENSHOW6 " +token);
                    startActivity(intent);
                }else {
                    Toast.makeText(Interest.this, "please select an Interest of Choice", Toast.LENGTH_LONG).show();
                    Log.d("Swip", "wipe");
                }

            }
        });*/
    }


    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked){

                   // Dinninout = zeroText.getText().toString();

                    interestRequestList.add(new InterestRequest(Dinninout));
                    Log.d("DIning", ""+Dinninout);
                }
                else {
              //      interest.remove(Dinninout);
                    interestRequestList.remove(new InterestRequest(Dinninout));
                   // Dinninout = "";
                    Log.d("non",""+Dinninout);
                }
                break;

            case R.id.radioButton1:
                if (checked){
                   // Travel = oneText.getText().toString();
                    //interest.add(Travel);
                    interestRequestList.add(new InterestRequest(Travel));
                    Log.d("DIning", ""+Travel);
                }
                else{
//                    interest.remove(Travel);
                    interestRequestList.remove(new InterestRequest(Travel));

                    //Travel = "";
                }
                break;

            case R.id.radioButton2:
                if (checked){
                   // Sport = twoText.getText().toString();
                    interestRequestList.add(new InterestRequest(Sport));
                    //interest.add(Sport);

                    Log.d("text", "show "+Sport);
                }
                else{
                  //  interest.remove(Sport);
                    interestRequestList.remove(new InterestRequest(Sport));

                    //Sport = "";
                }
                break;

            case R.id.radioButton3:
                if (checked){
                  //  NightclubsorDancing = threeText.getText().toString();
                    //interest.add(NightclubsorDancing);
                    interestRequestList.add(new InterestRequest(NightclubsorDancing));

                }else {
                   // interest.remove(NightclubsorDancing);
                    interestRequestList.remove(new InterestRequest(NightclubsorDancing));

                    // NightclubsorDancing = "";
                }
                break;

            case R.id.radioButton4:
                if (checked){
                  //  CoffeeConversation = fourText.getText().toString();
                   // interest.add(CoffeeConversation);
                    interestRequestList.add(new InterestRequest(CoffeeConversation));

                }else {
                   // CoffeeConversation = "";
                   // interest.remove(CoffeeConversation);
                    interestRequestList.remove(new InterestRequest(CoffeeConversation));

                }
                break;

            case R.id.radioButton5:
                if (checked){
                   // MusicandArt = fiveText.getText().toString();
                   // interest.add(MusicandArt);
                    interestRequestList.add(new InterestRequest(MusicandArt));

                }else {
                    //MusicandArt = "";
                  //  interest.remove(MusicandArt);
                    interestRequestList.remove(new InterestRequest(MusicandArt));

                }
                break;

            case R.id.radioButton6:
                if (checked){
                   // MoviesorVideos = sixText.getText().toString();
                 //   interest.add(MoviesorVideos);
                    interestRequestList.add(new InterestRequest(MoviesorVideos));

                }else {
                    //MoviesorVideos = "";
                   // interest.remove(MoviesorVideos);
                    interestRequestList.remove(new InterestRequest(MoviesorVideos));

                }
                break;

            case R.id.radioButton8:
                if (checked){
                   // MusicConcerts = sevenText.getText().toString();
                   // interest.add(MusicConcerts);
                    interestRequestList.add(new InterestRequest(MusicConcerts));

                }else {
                  //  MusicConcerts = "";
                 //   interest.remove(MusicConcerts);
                    interestRequestList.remove(new InterestRequest(MusicConcerts));

                }
                break;
            case R.id.radioButton9:
                if (checked){
                   // Winetesting = eightText.getText().toString();
                    //interest.add(Winetesting);
                    interestRequestList.add(new InterestRequest(Winetesting));

                }else {
                    //Winetesting = "";
                 //   interest.remove(Winetesting);
                    interestRequestList.remove(new InterestRequest(Winetesting));

                }
                break;


        }

        saveInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interestRequestList != null){
                    for (String inter: interest){
                        Log.d("display", inter);
                    }
                    for (InterestRequest interestRequest: interestRequestList){
                        Log.d("Request", String.valueOf(interestRequest.getInterest_id()));
                        saveInterest(interestRequestList);

                    }

                } else {
                String message = "Select Interest of choice";
                Toast.makeText(Interest.this, message, Toast.LENGTH_LONG).show();
                //Log.d("imageshow", ""+r);
                // Log.d("Body",new Gson().toJson(response.body()));
            }
               // onCheckboxClicked(view);

            }
        });

    }
    public void saveInterest(List<InterestRequest> interestRequest){
        Call<InterestResponds> interestRespondsCall = ApiClient.getService().interest(interestRequest, "Bearer "+token);
        interestRespondsCall.enqueue(new Callback<InterestResponds>() {
            @Override
            public void onResponse(Call<InterestResponds> call, Response<InterestResponds> response) {
                if (response.isSuccessful()){
                    message = response.body().getMessage();
                    String message = "Successful";
                    Toast.makeText(Interest.this, message, Toast.LENGTH_LONG).show();
                    Intent nextActivity = new Intent(Interest.this, CompleteProfile.class);
                    if (token != null){
                        nextActivity.putExtra("token", token);
                        nextActivity.putExtra("getProfilePics", imageUri);
                        nextActivity.putExtra("name", Fullname);
                        nextActivity.putExtra("getProfilePics", imageUri);

                        Log.d("TAG", ""+Fullname);

                        
                        startActivity(nextActivity);
                    }
                }else {
                    Toast.makeText(Interest.this, "please select an interest", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<InterestResponds> call, Throwable t) {
                Toast.makeText(Interest.this, "please select an interest", Toast.LENGTH_LONG).show();

            }
        });

    }
}