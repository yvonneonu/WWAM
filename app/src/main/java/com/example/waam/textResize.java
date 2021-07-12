package com.example.waam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class textResize extends AppCompatActivity {
    TextView textdisplay;
    SharedPreferences pref;
    SeekBar font, lineSpacing;
    private  String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_resize);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        textdisplay = findViewById(R.id.textView125);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            str = bundle.getString("typedText");

            textdisplay.setText(str);
        }else {
            Log.d("didnt show", ""+str);
        }


        font = findViewById(R.id.seekBar2);
        lineSpacing = findViewById(R.id.seekBar);

        pref = getPreferences(MODE_PRIVATE);

        float fs = pref.getFloat("VALUE", 50);

        float fs2 = pref.getFloat("VALUE", 3);


        font.setProgress((int)fs);
        lineSpacing.setProgress((int)fs2);

        textdisplay.setTextScaleX(lineSpacing.getProgress());


        textdisplay.setTextSize(TypedValue.COMPLEX_UNIT_PX, font.getProgress());

        font.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textdisplay.setTextSize(TypedValue.COMPLEX_UNIT_PX, font.getProgress());


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                pref = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor ed = pref.edit();
                ed.putFloat("VALUE", textdisplay.getTextSize());
                ed.apply();
            }
        });



        lineSpacing.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                textdisplay.setTextScaleX(lineSpacing.getProgress());

                Log.d("feg", "yfft");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                pref = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor ed = pref.edit();
                ed.putFloat("VALUE", textdisplay.getTextScaleX());
                Log.d("uyg", "fgg");
                ed.apply();
            }
        });

    }

    public void cancel(View view) {
        finish();
    }
}