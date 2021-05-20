package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Interest extends AppCompatActivity {

    private TextView wipe;
    private CheckBox zeroRadio, oneRadio, twoRadio, threeRadio, fourRadio, fiveRadio, sixRadio, sevenRadio, eightRadio;
    private TextView zeroText, oneText, twoText, threeText, fourText, fiveText, sixText, sevenText, eightText;
    String Dinninout = "";
    String Travel = "";
    String Sport = "";
    String NightclubsorDancing = "";
    String CoffeeConversation = "";
    String MusicandArt = "";
    String MoviesorVideos = "";
    String MusicConcerts = "";
    String Winetesting = "";
    private Button saveInterest;
    private List<String> interest;
   // String Fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        interest = new ArrayList<>();


        String imageUri = getIntent().getStringExtra("profilepics");
//        Log.d("ImageUriIN",imageUri);
        String Fullname = getIntent().getStringExtra("name");
        String token = getIntent().getStringExtra("mytoken");

        wipe = findViewById(R.id.swipe);
        zeroRadio = findViewById(R.id.radioButton);
        zeroText = findViewById(R.id.textView20);
        oneRadio = findViewById(R.id.radioButton1);
        oneText = findViewById(R.id.textView21);
        twoRadio = findViewById(R.id.radioButton2);
        twoText = findViewById(R.id.textView2);
        threeRadio = findViewById(R.id.radioButton3);
        threeText = findViewById(R.id.textView3);
        fourRadio = findViewById(R.id.radioButton4);
        fourText = findViewById(R.id.textView4);
        fiveRadio = findViewById(R.id.radioButton5);
        fiveText = findViewById(R.id.textView5);
        sixRadio = findViewById(R.id.radioButton6);
        sixText = findViewById(R.id.textView6);
        sevenRadio = findViewById(R.id.radioButton8);
        sevenText = findViewById(R.id.textView8);
        eightRadio = findViewById(R.id.radioButton9);
        eightText = findViewById(R.id.textView9);
        saveInterest = findViewById(R.id.button4);


        wipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interest.this, CompleteProfile.class);

                intent.putExtra("nameprofile", Fullname);
                if (token != null){
                    intent.putExtra("token", token);
                }
                if (imageUri != null){
                    intent.putExtra("getProfilePics", imageUri);
                }

                Log.d("TAG", "TOKENSHOW6 " +token);
                startActivity(intent);
            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked){
                    Dinninout = zeroText.getText().toString();
                    interest.add(Dinninout);
                    Log.d("DIning", ""+Dinninout);
                }
                else {
                    interest.remove(Dinninout);
                   // Dinninout = "";
                    Log.d("non",""+Dinninout);
                }
                break;

            case R.id.radioButton1:
                if (checked){
                    Travel = oneText.getText().toString();
                    interest.add(Travel);
                    Log.d("DIning", ""+Travel);
                }
                else{
                    interest.remove(Travel);

                    //Travel = "";
                }
                break;

            case R.id.radioButton2:
                if (checked){
                    Sport = twoText.getText().toString();
                    interest.add(Sport);

                    Log.d("text", "show "+Sport);
                }
                else{
                    interest.remove(Sport);

                    //Sport = "";
                }
                break;

            case R.id.radioButton3:
                if (checked){
                    NightclubsorDancing = threeText.getText().toString();
                    interest.add(NightclubsorDancing);

                }else {
                    interest.remove(NightclubsorDancing);

                    // NightclubsorDancing = "";
                }
                break;

            case R.id.radioButton4:
                if (checked){
                    CoffeeConversation = fourText.getText().toString();
                    interest.add(CoffeeConversation);

                }else {
                   // CoffeeConversation = "";
                    interest.remove(CoffeeConversation);

                }
                break;

            case R.id.radioButton5:
                if (checked){
                    MusicandArt = fiveText.getText().toString();
                    interest.add(MusicandArt);

                }else {
                    //MusicandArt = "";
                    interest.remove(MusicandArt);

                }
                break;

            case R.id.radioButton6:
                if (checked){
                    MoviesorVideos = sixText.getText().toString();
                    interest.add(MoviesorVideos);

                }else {
                    //MoviesorVideos = "";
                    interest.remove(MoviesorVideos);

                }
                break;

            case R.id.radioButton8:
                if (checked){
                    MusicConcerts = sevenText.getText().toString();
                    interest.add(MusicConcerts);

                }else {
                  //  MusicConcerts = "";
                    interest.remove(MusicConcerts);

                }
                break;
            case R.id.radioButton9:
                if (checked){
                    Winetesting = eightText.getText().toString();
                    interest.add(Winetesting);

                }else {
                    //Winetesting = "";
                    interest.remove(Winetesting);

                }
                break;


        }
        saveInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onCheckboxClicked(view);
                for (String inter: interest){
                    Log.d("display", inter);
                }
                Log.d("interest", ""+interest);


            }
        });
    }
}