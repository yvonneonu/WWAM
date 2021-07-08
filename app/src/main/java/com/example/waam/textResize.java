package com.example.waam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
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


        textdisplay = findViewById(R.id.textView125);


        int lineHeight = textdisplay.getLineHeight();
        //lineHeight = font * mult + add

        float add = textdisplay.getLineSpacingExtra();          // API 16+
        float mult = textdisplay.getLineSpacingMultiplier();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            str = bundle.getString("typedText");
            //  String str = intent.getStringExtra("typedText");

            textdisplay.setText(str);
        }else {
            Log.d("didnt show", ""+str);
        }


        font = findViewById(R.id.seekBar2);
        lineSpacing = findViewById(R.id.seekBar);


        pref = getPreferences(MODE_PRIVATE);

        float fs = pref.getFloat("VALUE", 25);

        font.setProgress((int)fs);
        lineSpacing.setProgress((int)fs);

        textdisplay.setTextSize(TypedValue.COMPLEX_UNIT_PX, font.getProgress());


        textdisplay.setLineSpacing(TypedValue.COMPLEX_UNIT_PX, lineSpacing.getProgress());
        //textdisplay.setTextSize(TypedValue.);

        lineSpacing.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //textdisplay.setLineSpacing();
                textdisplay.setLineSpacing(TypedValue.COMPLEX_UNIT_PX, lineSpacing.getProgress());

                textdisplay.setLineSpacing(add, mult);
                // textdisplay.setLineSpacing(TypedValue.UnUnitConverter.spToPixels, lineSpacing.getProgress());
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
        font.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textdisplay.setTextSize(TypedValue.COMPLEX_UNIT_PX, font.getProgress());


                //textdisplay.setTextSize(progress);
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



    }
}