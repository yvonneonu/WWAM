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

public class finalProfile extends AppCompatActivity {

    private TextView textView, swipe, careerText;
    private ImageView image;
    private String spinn,spinn2, ret;
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
    Spinner spinner, careerSpin;

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
        count1 = 0;
        textView = findViewById(R.id.textView);
        careerText = findViewById(R.id.textView26);
        spinner = findViewById(R.id.one);
        careerSpin = findViewById(R.id.carer);
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
        names.add("Cyndy");
        names.add("Yvonne");

        names.add("Hailey");
        names.add("Juliet");
        names.add("Maria");

        careerSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count++;
                Log.d("InCareer","Running");
                Log.d("InCareer",""+count);
                if (count > 1) careerText.setText(careerSpin.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count1++;
                if(count1 > 1) textView.setText(spinner.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        body.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count2++;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ethni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count3++;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fait.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count4++;
                if(count4 > 1) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        polit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count5++;

                if(count5 > 1) ;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        childre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count6++;
                if(count6 > 1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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

        FetchSpinnerValues.getSpinnerValues().fetchOccupation(new FetchSpinnerValues.OccupationListener() {
            @Override
            public void onOccupationListener(List<String> userSchool) {
                ArrayAdapter<String> userSchollAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, names);
                userSchollAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                careerSpin.setAdapter(userSchollAdapter);
                spinn2 = careerSpin.getSelectedItem().toString();
                //careerText.setText(spinn2);
                //educate.setText(spinn);
            }
        }, toks);

        FetchSpinnerValues.getSpinnerValues().fetchEducation(new FetchSpinnerValues.EducationListener() {
            @Override
            public void onEducationListener(List<String> qualification) {
                ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, names);
                qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(qualificationAdapter);
                spinn = spinner.getSelectedItem().toString();

            }
        },toks);

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

        FetchSpinnerValues.getSpinnerValues().fetchSalay(userSalary ->  {
            ArrayAdapter<String> userSalaryAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userSalary);
            userSalaryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            childre.setAdapter(userSalaryAdapter);
            spinn = childre.getSelectedItem().toString();
        }, toks);
    }






}



