package com.example.waam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class Interest extends AppCompatActivity {

    private Button wipe;
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
   // String Fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);



        String imageUri = getIntent().getStringExtra("profilepics");
        Log.d("ImageUriIN",imageUri);
        String Fullname = getIntent().getStringExtra("name");

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


        wipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interest.this, CompleteProfile.class);
                intent.putExtra("getProfilePics", imageUri);
                intent.putExtra("name", Fullname);
                startActivity(intent);
            }
        });
    }

    public void dinning_out(View view) {
        if (view.getId() == zeroRadio.getId()) {
            //zeroRadio.setBackgroundResource(R.drawable.selectedbubble);
            Dinninout = zeroText.getText().toString();

        }else {
           // zeroRadio.setBackgroundResource(R.drawable.flag_transparent);
            Dinninout = "";
        }
    }

    public void travel(View view) {
        if (view.getId() == oneRadio.getId()) {
          //  oneRadio.setBackgroundResource(R.drawable.selectedbubble);
            Travel = oneText.getText().toString();

        } else {
           // oneRadio.setBackgroundResource(R.drawable.flag_transparent);
            Travel = "";
        }
    }

    public void sport(View view) {
        if (view.getId() == twoRadio.getId()) {
            //twoRadio.setBackgroundColor(Color.YELLOW);
            //setBackgroundTintMode(R.color.yellow);
           // Log.d("text", "showtext "+sport);
            Sport = twoText.getText().toString();
            Log.d("text", "show "+Sport);

        } else {

            Sport = "";
            Log.d("text", "sho "+Sport);


        }
    }

    public void nightclubs(View view) {
        if (view.getId() == threeRadio.getId()){
            NightclubsorDancing = threeText.getText().toString();
        }else {
            NightclubsorDancing = "";
        }

    }

    public void coffeeconversation(View view) {
        if (view.getId() == fourRadio.getId()){
            CoffeeConversation = fourText.getText().toString();
        }else {
            CoffeeConversation = "";
        }
    }

    public void museumart(View view) {
        if (view.getId() == fiveRadio.getId()){
            MusicandArt = fiveText.getText().toString();
        }else {
           MusicandArt = "";
        }
    }

    public void moviesorVideos(View view) {
        if (view.getId() == sixRadio.getId()){
            MoviesorVideos = sixText.getText().toString();
        }else {
           MoviesorVideos = "";
        }
    }

    public void musicconcept(View view) {
        if (view.getId() == sevenRadio.getId()){
          MusicConcerts = sevenText.getText().toString();
        }else {
           MusicConcerts = "";
        }

    }

    public void winetesting(View view) {
        if (view.getId() == eightRadio.getId()){
            Winetesting = eightText.getText().toString();
        }else {
         Winetesting = "";
        }
    }
}