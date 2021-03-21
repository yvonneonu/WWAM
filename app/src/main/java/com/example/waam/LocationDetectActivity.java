package com.example.waam;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LocationDetectActivity extends AppCompatActivity implements LocationListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    String Fullname;
    private TextView locate;
    private boolean textVisible;
    LocationManager locationManager;
    Button loca;
    String provider;

    private TextView textViewMore, moreinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable__location);
        String Fullname = getIntent().getStringExtra("name");
        String token = getIntent().getStringExtra("bearer");

        defineViews();
        textVisible = false;
        loca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(LocationDetectActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    Log.d("Perm", "permimisson is already granted so execution stops here");
                    Toast.makeText(LocationDetectActivity.this,"Permission already granted",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LocationDetectActivity.this, Profile.class);
                    intent.putExtra("name", Fullname);
                    if (token != null){
                        intent.putExtra("alltoken", token);
                    }

                    Log.d("TAG", "TOKENSHOW4 " +token);
                    startActivity(intent);
                }else {

                    Log.d("Perm", "permimisson is not granted yet and seek it");
                    requestPermission();
                }
            }
        });


        textViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textVisible){
                    textVisible = false;
                    moreinfo.setVisibility(View.GONE);
                }else{
                    textVisible = true;
                    moreinfo.setVisibility(View.VISIBLE);
                }
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            Log.d("Perm", "I am in request");
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(LocationDetectActivity.this,"Permission Granted",Toast.LENGTH_SHORT).show();
                Log.d("Perm", "permision granted");
            }else{
                Log.d("Perm", "permision denied");
                Toast.makeText(LocationDetectActivity.this,"Permission denied",Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void defineViews(){

        locate = findViewById(R.id.user_location);
        textViewMore = findViewById(R.id.txtMore);
        moreinfo = findViewById(R.id.textView18);
        loca = findViewById(R.id.fetch_location);

    }

    public void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.title_location_permission)
                    .setMessage(R.string.text_location_permission)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(LocationDetectActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_LOCATION);
                        }
                    })
                    .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();

        }else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        double lat = location.getLatitude();
        double lng = location.getLongitude();

        Log.i("Location info: Lat", Double.toString(lat));
        Log.i("Location info: Lng", Double.toString(lng));
    }
}