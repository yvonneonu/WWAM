package com.example.waam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class finalProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textView, swipe, educate;
    private ImageView image;
    private String spinn;
    private boolean textVisible;
    private int count;
    Spinner spinner;

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

        count = 0;
        textView = findViewById(R.id.textView);
        educate = findViewById(R.id.textView26);
        spinner = findViewById(R.id.one);
        Spinner career = findViewById(R.id.carer);
        Spinner body = findViewById(R.id.spinnN);
        Spinner ethni = findViewById(R.id.ethnic);
       // textView.setText(spinn);

        //textView.setText(spinn);
        //String token = getIntent().getStringExtra("everytoken");
        String toks = getIntent().getStringExtra("token");
        //Log.d("sorry", "iknowyouaretired "+token);


        List<String> names = new ArrayList<>();
     //   textView.setText(spinn);

        names.add("Cynthia");
        names.add("Yvonne");
        names.add("Hailey");
        names.add("Juliet");
        names.add("Maria");
        spinner.setOnItemSelectedListener(this);

        swipe = findViewById(R.id.textView34);
        swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalProfile.this, LookingFor.class);
                if (imageUri != null) {
                    intent.putExtra("images", imageUri);
                }
                startActivity(intent);
            }
        });

        FetchSpinnerValues.getSpinnerValues().fetchEducation(new FetchSpinnerValues.EducationListener() {
            @Override
            public void onEducationListener(List<String> qualification) {
                ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, names);
                qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(qualificationAdapter);

            }
        },toks);

        FetchSpinnerValues.getSpinnerValues().fetchOccupation(new FetchSpinnerValues.OccupationListener() {
            @Override
            public void onOccupationListener(List<String> userSchool) {
                ArrayAdapter<String> userSchollAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userSchool);
                userSchollAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                career.setAdapter(userSchollAdapter);
                spinn = career.getSelectedItem().toString();
                //educate.setText(spinn);
            }
        }, toks);

        FetchSpinnerValues.getSpinnerValues().fetchBody(new FetchSpinnerValues.BodyTypeListener() {
            @Override
            public void onBodyTypeListener(List<String> userBody) {
                ArrayAdapter<String> userSchollAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userBody);
                userSchollAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                body.setAdapter(userSchollAdapter);
                spinn = body.getSelectedItem().toString();
            }
        }, toks);

        FetchSpinnerValues.getSpinnerValues().fetchEthnicity(userEthnicity -> {
            ArrayAdapter<String> userSchollAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userEthnicity);
            userSchollAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ethni.setAdapter(userSchollAdapter);
            spinn = ethni.getSelectedItem().toString();
        }, toks);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        count++;

        if(count > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Log.d("Nothing","Nothing is fine");
    }
}



