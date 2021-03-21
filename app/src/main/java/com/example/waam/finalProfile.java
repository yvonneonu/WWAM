package com.example.waam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class finalProfile extends AppCompatActivity {

    private TextView textView;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_profile);

        String imageUri = getIntent().getStringExtra("image");
        image = findViewById(R.id.imageView12);
        Spinner spinner =  findViewById(R.id.one);
        String token = getIntent().getStringExtra("everytoken");
        Log.d("sorry", "iknowyouaretired "+token);

       Glide.with(this)
                .asBitmap()
                .circleCrop()
                .load(Uri.parse(imageUri))
                .into(image);


        textView = findViewById(R.id.textView34);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalProfile.this, LookingFor.class);
                intent.putExtra("images", imageUri.toString());
                if (token != null){
                    intent.putExtra("token", token);
                }
                Log.d("TAG", "TOKENSHOW8 " +token);

                startActivity(intent);
            }
        });

        FetchSpinnerValues.getSpinnerValues().fetchEducation(new FetchSpinnerValues.EducationListener() {
            @Override
            public void onEducationListener(List<String> qualification) {
                ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, qualification);
                qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(qualificationAdapter);
            }
        },token);

    }
}