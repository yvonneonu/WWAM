package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AgentInterest extends AppCompatActivity {

    private TextView zeroText, oneText, twoText, threeText, fourText, fiveText, sixText, sevenText, eightText,
            ten, eleven, tewlve, thirteen, fourten, fifteen, sixten, seventeen,
            eighteen;

    ImageView back;
    Button ContNext;
    private boolean comingtoBecoomingAgent;
    private String Dinninout = "Dinning Out";

    private String Travel = "Travel";
    private String Sport = "String";
    private String NightclubsorDancing = "Nightclubs/Dancing";
    private String CoffeeConversation = "Coffee Conversation";
    private String MusicandArt = "Music / Art";
    private String MoviesorVideos = "Movies / Videos";
    private String MusicConcerts = "Music Concerts";
    private String Winetesting = "Wine testing";
    private String Entertainment = "Entertainment";

    private String Food = "Food";
    private String Fitness$Beauty = "Fitness$ & Beauty";
    private String Technology = "Technology";
    private String Automobiles = "Automobiles";
    private String Fashion = "Fashion";
    private String Apps$Ganes = "Apps & Ganes";
    private String CityTimeout = "City Timeout";
    private String Science = "Science";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_interest);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        back = findViewById(R.id.back);



        zeroText = findViewById(R.id.textView20);
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
        eightText = findViewById(R.id.textView9);
        ten = findViewById(R.id.textView174);
        eleven = findViewById(R.id.textView175);
        tewlve = findViewById(R.id.textView176);
        thirteen = findViewById(R.id.textView177);
        fourten = findViewById(R.id.textView178);
        fifteen = findViewById(R.id.texte);
        sixten = findViewById(R.id.textView179);
        seventeen = findViewById(R.id.secondtolast);
        eighteen = findViewById(R.id.lastText);
        ContNext = findViewById(R.id.ContNext);

        ContNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(AgentInterest.this, DiscoverDrawerLayerout.class);
              intent.putExtra("comingtoBecoomingAgent", true);
              startActivity(intent);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //public AgentInterest(boolean comingtoBecoomingAgent){
     //   this.comingtoBecoomingAgent = comingtoBecoomingAgent;
    //}

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.radioButton:
                if (checked) {
                    Dinninout = zeroText.getText().toString();
                    Log.d("DIning", "" + Dinninout);
                } else {
                    Dinninout = "";
                    Log.d("non", "" + Dinninout);
                }
                break;

            case R.id.radioButton1:
                if (checked) {
                    Travel = oneText.getText().toString();
                    Log.d("DIning", "" + Travel);
                } else {
                    Travel = "";
                }
                break;

            case R.id.radioButton2:
                if (checked) {
                    Sport = twoText.getText().toString();

                    Log.d("text", "show " + Sport);
                } else {
                    Sport = "";
                }
                break;

            case R.id.radioButton3:
                if (checked) {
                    NightclubsorDancing = threeText.getText().toString();

                } else {
                    NightclubsorDancing = "";
                }
                break;

            case R.id.radioButton4:
                if (checked) {
                    CoffeeConversation = fourText.getText().toString();

                } else {
                    CoffeeConversation = "";
                }
                break;

            case R.id.radioButton5:
                if (checked) {
                    MusicandArt = fiveText.getText().toString();

                } else {
                    MusicandArt = "";
                }
                break;

            case R.id.radioButton6:
                if (checked) {
                    MoviesorVideos = sixText.getText().toString();

                } else {
                    MoviesorVideos = "";
                }
                break;

            case R.id.radioButton8:
                if (checked) {
                    MusicConcerts = sevenText.getText().toString();
                } else {
                    MusicConcerts = "";
                }
                break;
            case R.id.radioButton9:
                if (checked) {
                    Winetesting = eightText.getText().toString();

                } else {
                    Winetesting = "";
                }
                break;
            case R.id.checkBox10:
                if (checked) {
                    Entertainment = ten.getText().toString();
                } else {
                    Entertainment = "";
                }
                break;
            case R.id.checkBox3:
                if (checked) {
                    Food = eleven.getText().toString();
                } else {
                    Food = "";
                }
                break;
            case R.id.checkBox2:
                if (checked) {
                    Fitness$Beauty = tewlve.getText().toString();
                } else {
                    Fitness$Beauty = "";
                }
                break;
            case R.id.checkBox4:
                if (checked) {
                    Technology = thirteen.getText().toString();
                } else {
                    Technology = "";
                }
                break;
            case R.id.checkBox5:
                if (checked) {
                    Automobiles = fourten.getText().toString();
                } else {
                    Automobiles = "";
                }
                break;
            case R.id.checkBox6:
                if (checked) {
                    Fashion = fifteen.getText().toString();
                } else {
                    Fashion = "";
                }
                break;
            case R.id.checkBox8:
                if (checked) {
                    Apps$Ganes = sixten.getText().toString();
                } else {
                    Apps$Ganes = "";
                }
                break;
            case R.id.checkBox7:
                if (checked) {
                    CityTimeout = seventeen.getText().toString();
                } else {
                    CityTimeout = "";
                }
                break;
            case R.id.lastOne:
                if (checked) {
                    Science = eighteen.getText().toString();
                } else {
                    Science = "";
                }
                break;


        }
    }
}