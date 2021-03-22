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
    private int count1;
    private int count2;
    private int count3;
    private int count4;
    private int count5;
    private int count6;
    private int count7;
    private int count8;
    private int count9;
    private int count10;
    Spinner spinner, career;

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
        career = findViewById(R.id.carer);
        Spinner body = findViewById(R.id.spinnN);
        Spinner ethni = findViewById(R.id.ethnic);
        Spinner fait = findViewById(R.id.faith);
        Spinner polit = findViewById(R.id.politic);
        Spinner childre = findViewById(R.id.children);

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
                if (textView.toString().isEmpty() ){
                    textView.setError("Choose your Career");
                    //||
                    textView.requestFocus();
                  //  Toast.makeText()
                }else if (textView.toString().isEmpty()){

                }else{
                    Intent intent = new Intent(finalProfile.this, LookingFor.class);
                    if (imageUri != null) {
                        intent.putExtra("images", imageUri);
                    }
                    startActivity(intent);
                }

            }
        });

        FetchSpinnerValues.getSpinnerValues().fetchEducation(new FetchSpinnerValues.EducationListener() {
            @Override
            public void onEducationListener(List<String> qualification) {
                ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, qualification);
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
                ArrayAdapter<String> userBodyAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userBody);
                userBodyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                body.setAdapter(userBodyAdapter);
                spinn = body.getSelectedItem().toString();
            }
        }, toks);

        FetchSpinnerValues.getSpinnerValues().fetchEthnicity(userEthnicity -> {
            ArrayAdapter<String> userEhtnicityAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userEthnicity);
            userEhtnicityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ethni.setAdapter(userEhtnicityAdapter);
            spinn = ethni.getSelectedItem().toString();
        }, toks);

        FetchSpinnerValues.getSpinnerValues().fetchFaith(userFaith ->  {
            ArrayAdapter<String> userFaithAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userFaith);
            userFaithAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fait.setAdapter(userFaithAdapter);
            spinn = fait.getSelectedItem().toString();
        }, toks);

        FetchSpinnerValues.getSpinnerValues().fetchPolitics(userPolitics ->  {
            ArrayAdapter<String> userPoliticsAdapetr = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userPolitics);
            userPoliticsAdapetr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            polit.setAdapter(userPoliticsAdapetr);
            spinn = polit.getSelectedItem().toString();
        }, toks);

        FetchSpinnerValues.getSpinnerValues().fetchChildren(userChildren ->  {
            ArrayAdapter<String> userChildrenAdapetr = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userChildren);
            userChildrenAdapetr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            childre.setAdapter(userChildrenAdapetr);
            spinn = childre.getSelectedItem().toString();
        }, toks);

        FetchSpinnerValues.getSpinnerValues().fetchSmoke(userSmoke ->  {
            ArrayAdapter<String> userSmokeAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userSmoke);
            userSmokeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            childre.setAdapter(userSmokeAdapter);
            spinn = childre.getSelectedItem().toString();
        }, toks);

        FetchSpinnerValues.getSpinnerValues().fetchDrink(userDrink ->  {
            ArrayAdapter<String> userDrinkAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userDrink);
            userDrinkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            childre.setAdapter(userDrinkAdapter);
            spinn = childre.getSelectedItem().toString();
        }, toks);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        count++;
        count1++;
        count2++;
        count3++;
        count4++;
        count5++;
        count6++;
        count7++;
        count8++;
        count9++;
        count10++;



        if(view.getId() == R.id.one){

            if(count > 1) textView.setText(spinner.getSelectedItem().toString());


        }else if(view.getId() == R.id.one){

            if(count > 1) textView.setText(career.getSelectedItem().toString());

        }else if(count > 1){
            if(count > 1) textView.setText(career.getSelectedItem().toString());
        }
        if(count > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }else if(count1 > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }else  if(count2 > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }else  if(count3 > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }else  if(count4 > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }else  if(count5 > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }
        else  if(count6 > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }else  if(count7 > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }else  if(count8 > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }else  if(count9 > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }else  if(count10 > 1){
            textView.setText(spinner.getSelectedItem().toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Log.d("Nothing","Nothing is fine");
    }
}



