package com.example.waam;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.waam.DisplayProfile.ProfileModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareTot extends AppCompatActivity implements View.OnClickListener {
    private ImageView text2, image;
    private ConstraintLayout coonc;
    private TextView name, resizetext, country, stateName;
    private TextInputEditText typeText;
    private String text;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_tot);

        token = SharedPref.getInstance(ShareTot.this).getStoredToken();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        String uid = FirebaseAuth.getInstance().getUid();
        image = findViewById(R.id.imageView);
        typeText = findViewById(R.id.typeText);
        country = findViewById(R.id.textView187);
        stateName = findViewById(R.id.textView72);

        String more;

        coonc = findViewById(R.id.coonc);
        text2 = findViewById(R.id.text2);
        name = findViewById(R.id.textView71);
        resizetext = findViewById(R.id.share);


        profileDetail();

        GeneralFactory.getGeneralFactory(this).loadSpecUser(uid, new GeneralFactory.SpecificUser() {
            @Override
            public void loadSpecUse(WaamUser user) {
                Glide.with(ShareTot.this)
                        .asBitmap()
                        .fitCenter()
                        .circleCrop()
                        .load(user.getImageUrl())
                        .into(image);

                name.setText(user.getFullname());
            }
        });


        //text = Objects.requireNonNull(typeText.getText()).toString();

      text2.setOnClickListener(this);
        text2.setColorFilter(Color.BLUE);

        resizetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeText != null){
                    text = Objects.requireNonNull(typeText.getText()).toString();

                    Intent intent = new Intent(ShareTot.this, textResize.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("typedText", text);
                    intent.putExtras(bundle);
                    Log.d("please diplay", text);
                    startActivity(intent);
                }
                else {
                    Log.d("please diplay", "did not show");

                }

            }
        });

    }


    @Override
    public void onClick(View v) {
        final int image = R.id.text2;
        final int totFram = R.id.totFram;

        switch (v.getId()){
            case image:
                text2.setColorFilter(Color.BLUE);

                Fragment fragment = new TextdisplayFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(totFram, fragment);
                fragmentTransaction.commit();

                break;
        }
    }

    public void saveText(View text){
        String tots = typeText.getText().toString();

    }
    public void backtofragment(View view) {
        finish();
    }


    private void profileDetail() {
        Call<ProfileModel> getProfile = ApiClient.getService().profiledisplay( "Bearer " + token);
        getProfile.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (!response.isSuccessful()){
                    Log.d("no profile", "no profile listed");
                    return;

                }
                ProfileModel model = response.body();

                Log.d("location3445", "day"+model.getGender());


                final Geocoder geocoder = new Geocoder(ShareTot.this);
                final String zip = response.body().getZipcode();
                try {
                    List<Address> addresses = geocoder.getFromLocationName(zip, 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);

                        String abbreviate = address.getCountryName();
                        String[] FullName = address.getCountryName().split("");
                        String state = FullName[1];
                        Log.d("original", state);

                        getCountryCode(abbreviate);

                        Log.d("location", address.getCountryName());
                        Log.d("location", "" + address.getLocality());
                        stateName.setText(address.getLocality());

                    }
                } catch (IOException e) {
                    // handle exception
                }

            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {

                Log.d("no profile",t.getMessage());
            }
        });
    }



    public String getCountryCode(String countryName) {

        // Get all country codes in a string array.
        String[] isoCountryCodes = Locale.getISOCountries();
        Map<String, String> countryMap = new HashMap<>();
        Locale locale;
        String name;

        // Iterate through all country codes:
        for (String code : isoCountryCodes) {
            // Create a locale using each country code
            locale = new Locale("", code);
            // Get country name for each code.
            name = locale.getDisplayCountry();
            // Map all country names and codes in key - value pairs.
            countryMap.put(name, code);
        }

        Log.d("countryname", ""+countryMap.get(countryName));
        String countryAbbre = countryMap.get(countryName);


        country.setText(countryAbbre);
        Log.d("countryname", countryAbbre);
        // Return the country code for the given country name using the map.
        // Here you will need some validation or better yet
        // a list of countries to give to user to choose from.
        return countryMap.get(countryName); // "NL" for Netherlands.
    }




}