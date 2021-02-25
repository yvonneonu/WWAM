package com.example.waam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class Enable_Location extends AppCompatActivity {
    private static final int MY_PERMISION_REQUEST_ACCESS_COARSE_LOCATION = 99;
    Button fetch;
    TextView user_location;
    Spinner spinner;
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable__location);

        fetch = findViewById(R.id.fetch_location);
        user_location = findViewById(R.id.user_location);
        spinner = findViewById(R.id.spin);



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchLocation();

            }
        });
    }

  //  private void fetchLocation() {
    public boolean fetchLocation() {

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.

            if (ActivityCompat.shouldShowRequestPermissionRationale(Enable_Location.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {


                new AlertDialog.Builder(Enable_Location.this)
                        .setTitle(R.string.description)
                        .setMessage(R.string.title)
                        .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(Enable_Location.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISION_REQUEST_ACCESS_COARSE_LOCATION);

                            }
                        })
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(Enable_Location.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISION_REQUEST_ACCESS_COARSE_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case MY_PERMISION_REQUEST_ACCESS_COARSE_LOCATION:{
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){

                }
                else { }
                {
                    return;
                }

            }
        }
    }
}