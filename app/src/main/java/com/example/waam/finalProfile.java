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

import java.util.List;

public class finalProfile extends AppCompatActivity {

    private TextView textView, swipe, educate;
    private ImageView image;
    private String spinn;
    private boolean textVisible;
    private int count;

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
        Spinner spinner = findViewById(R.id.one);
        Spinner career = findViewById(R.id.carer);
        Spinner body = findViewById(R.id.spinnN);
        Spinner ethni = findViewById(R.id.ethnic);
       // textView.setText(spinn);

        //textView.setText(spinn);
        //String token = getIntent().getStringExtra("everytoken");
        String toks = getIntent().getStringExtra("token");
        //Log.d("sorry", "iknowyouaretired "+token);


     //   textView.setText(spinn);

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

        FetchSpinnerValues.getSpinnerValues().fetchEducation(new FetchSpinnerValues.EducationListener() {
            @Override
            public void onEducationListener(List<String> qualification) {

                ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, qualification);
                qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(qualificationAdapter);
               // spinn = spinner.getSelectedItem().toString();

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {

                        String item = "nothing";
                        // On selecting a spinner item
Log.d()
                        count++;

                        Log.d("Counts",""+count);
                        if(count > 1){
                            item = parent.getItemAtPosition(position).toString();
                            textView.setText(item);
                        }


                        // showing a toast on selecting an item
                        Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                        Log.d("Nothing","Nothing was selected");
                    }

                });

                //textView.setText(spinn);
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

        FetchSpinnerValues.getSpinnerValues().fetchEthnicity(new FetchSpinnerValues.EthnicityListener() {
            @Override
            public void onEthnicityListener(List<String> userEthnicity) {
                ArrayAdapter<String> userSchollAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userEthnicity);
                userSchollAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ethni.setAdapter(userSchollAdapter);
                spinn = ethni.getSelectedItem().toString();
            }
        }, toks);


    }

}