package com.example.waam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class Enable_Location extends AppCompatActivity {
    private static final int MY_PERMISION_REQUEST_ACCESS_COARSE_LOCATION = 1;
    Button fetch;
    TextView user_location;
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable__location);

        fetch = findViewById(R.id.fetch_location);
        user_location = findViewById(R.id.user_location);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchLocation();

            }
        });
    }

    private void fetchLocation() {

        if (ContextCompat.checkSelfPermission(
                Enable_Location.this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.

            if (ActivityCompat.shouldShowRequestPermissionRationale(Enable_Location.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)){



               new  AlertDialog.Builder(Enable_Location.this)
                        .setTitle("Allow WWAM to access your location while you are using the app? ")
                        .setMessage("Recommendations will be presented based on your location." )
                        .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(Enable_Location.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISION_REQUEST_ACCESS_COARSE_LOCATION);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               dialog.dismiss();
                            }
                        })
                        .create()
                        .show();

            }else {
                ActivityCompat.requestPermissions(Enable_Location.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                       MY_PERMISION_REQUEST_ACCESS_COARSE_LOCATION);

            }

        } else {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                             //   Double latitude = location.getLatitude();
                               // Double longitude = location.getLongitude();
                                //user_location.setText("Latitude = "+latitude + "\nLongitude = " + longitude);
                            }
                        }
                    });

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISION_REQUEST_ACCESS_COARSE_LOCATION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }else {

            }
        }
    }
}