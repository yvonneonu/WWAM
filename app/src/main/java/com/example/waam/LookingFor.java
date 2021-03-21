package com.example.waam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class LookingFor extends AppCompatActivity {

    private TextView textView, swipe;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_for);
        swipe = findViewById(R.id.textView34);
        image = findViewById(R.id.imageView12);
        textView = findViewById(R.id.edutextView);
        String token = getIntent().getStringExtra("token");
        Spinner spinner = findViewById(R.id.educatespinner);

        String imageUri = getIntent().getStringExtra("images");


        Glide.with(this)
                .asBitmap()
                .circleCrop()
                .load(Uri.parse(imageUri))
                .into(image);

        swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LookingFor.this, DrawelayoutActivity.class);
                startActivity(intent);
            }
        });
       FetchSpinnerValues.getSpinnerValues().fetchEducation(new FetchSpinnerValues.EducationListener() {
            @Override
            public void onEducationListener(List<String> qualification) {
                ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(LookingFor.this, android.R.layout.simple_spinner_item, qualification);
                qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(qualificationAdapter);
                String spinn = spinner.getSelectedItem().toString();
                textView.setText(spinn);
            }
        },token);

    }
}