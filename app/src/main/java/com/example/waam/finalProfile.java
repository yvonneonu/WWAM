package com.example.waam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class finalProfile extends AppCompatActivity {

    private TextView textView, swipe;
    private ImageView image;
    private String spinn;
    private boolean textVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_profile);


        String imageUri = getIntent().getStringExtra("image");
        image = findViewById(R.id.imageView12);
        if (imageUri != null) {
            Glide.with(this)
                    .asBitmap()
                    .circleCrop()
                    .load(Uri.parse(imageUri))
                    .into(image);
        }

        textView = findViewById(R.id.textView);


        Spinner spinner = findViewById(R.id.one);
        Spinner career = findViewById(R.id.carer);

        //textView.setText(spinn);
        //String token = getIntent().getStringExtra("everytoken");
        String toks = getIntent().getStringExtra("token");
        //Log.d("sorry", "iknowyouaretired "+token);


        textView.setText(spinn);

        swipe = findViewById(R.id.textView34);
        swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalProfile.this, LookingFor.class);
                if (imageUri != null) {
                    intent.putExtra("images", imageUri.toString());
                }

                /*if (token != null){
                    intent.putExtra("token", token);
                }*/
                //Log.d("TAG", "TOKENSHOW8 " +token);
                startActivity(intent);
            }
        });


        FetchSpinnerValues.getSpinnerValues().fetchOccupation(new FetchSpinnerValues.OccupationListener() {
            @Override
            public void onOccupationListener(List<String> userSchool) {

                if (career == null) {

                    ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userSchool);

                    qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinn = career.getSelectedItem().toString();
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            career.setAdapter(qualificationAdapter);

                            textView.setText(spinn);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }


            }
        }, toks);
        // spinn = spinner.getSelectedItem().toString();
                    // spinn = spinner.getSelectedItem().toString();
                    //  textView.setText("spi")
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




    }



}