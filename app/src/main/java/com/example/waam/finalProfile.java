package com.example.waam;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.waam.DisplayProfile.ProfileModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class finalProfile extends AppCompatActivity {

    private TextView textView, swipe, careerText, bod, ethnictext, faithtext, politictext,
            childrentext, smoketext, drinktext, salatext, namme, location;
    private ImageView image;
    private String  spinn2, spinn, ret, spinehnic, spinfaith, spinPolitics, spinChildren, spinSmoke, spinDrink, spinsala;
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
    private FirebaseAuth mAuth;

    private String token;
    private Button saveDetails;
    private int zero, first, second, third, fourth, five, six, seven, eight, night;
   // private int count10;
   String imageUri;
    Spinner spinner, careerSpin, body, ethni, fait, polit, childre, smok, drink, sala;

    WaamUser waamUser = new WaamUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        token = getIntent().getStringExtra("everytoken");

        //  String imageUri = getIntent().getStringExtra("getProfilePics");

        String uid = FirebaseAuth.getInstance().getUid();

        token = SharedPref.getInstance(this).getStoredToken();
//        Log.d("Complete",imageUri);
        String Fullname = getIntent().getStringExtra("name");
      //  String tired = getIntent().getStringExtra("everytoken");



        imageUri = getIntent().getStringExtra("image");
        image = findViewById(R.id.imageView12);
       /* if (imageUri != null) {
            Glide.with(this)
                    .asBitmap()
                    .circleCrop()
                    .load(Uri.parse(imageUri))
                    .into(image);
        }*/

        count = 0;
        count1 = 0;
        textView = findViewById(R.id.textView);
        namme = findViewById(R.id.textView19);
        careerText = findViewById(R.id.textView26);
        ethnictext = findViewById(R.id.textView3);
        faithtext = findViewById(R.id.textView4);
        politictext = findViewById(R.id.textView5);
        childrentext = findViewById(R.id.textView6);
        smoketext = findViewById(R.id.textView7);
        drinktext = findViewById(R.id.textView8);
        salatext = findViewById(R.id.textView9);
        saveDetails = findViewById(R.id.button6);
        location = findViewById(R.id.textView22);


        spinner = findViewById(R.id.one);
        bod = findViewById(R.id.textView2);
        careerSpin = findViewById(R.id.carer);
        body = findViewById(R.id.spinnN);
        ethni = findViewById(R.id.ethnic);
        fait = findViewById(R.id.faith);
        polit = findViewById(R.id.politic);
        childre = findViewById(R.id.children);
        smok = findViewById(R.id.smoke);
        drink = findViewById(R.id.drink);
        sala = findViewById(R.id.salary);

        profileDetail();


        //String toks = getIntent().getStringExtra("token");
        //Log.d("sorry", "iknowyouaretired "+token);


        //namme.setText(Fullname);
        Log.d("TAG", ""+Fullname);

        List<String> names = new ArrayList<>();
     //   textView.setText(spinn);

        names.add("Cynthia");
        names.add("Cyndy");
        names.add("Yvonne");

        names.add("Hailey");
        names.add("Juliet");
        names.add("Maria");

        GeneralFactory.getGeneralFactory(this).loadSpecUser(uid, new GeneralFactory.SpecificUser() {
            @Override
            public void loadSpecUse(WaamUser user) {
                Glide.with(finalProfile.this)
                        .asBitmap()
                        .fitCenter()
                        .circleCrop()
                        .load(user.getImageUrl())
                        .into(image);

                namme.setText(user.getFullname());
            }
        });
        // name.setTe

       // namme.setText(SharedPref.getInstance(this).getStoredName());

        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hereapi();
                Log.d("bfei", "jabhbchj");
            }
        });





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

                zero = 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        body.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count2++;
                if (count2> 1) bod.setText(body.getSelectedItem().toString());

                first = 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ethni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count3++;
                if (count3> 1)ethnictext.setText(ethni.getSelectedItem().toString());
                second = 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fait.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count4++;
                if(count4> 1)faithtext.setText(fait.getSelectedItem().toString());
                third = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        polit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count5++;

                if(count5> 1)politictext.setText(polit.getSelectedItem().toString());
                fourth = 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        childre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count6++;
                if(count6> 1)childrentext.setText(childre.getSelectedItem().toString());

                five = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        smok.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count7++;
                if (count7> 1)smoketext.setText(smok.getSelectedItem().toString());
                six = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        drink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count8++;
                if (count8> 1)drinktext.setText(drink.getSelectedItem().toString());
                seven = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count9++;
                if (count9> 1)salatext.setText(sala.getSelectedItem().toString());
                eight = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swipe = findViewById(R.id.textView34);
        swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finalProfile.this, LookingFor.class);

                startActivity(intent);
            }
        });

        FetchSpinnerValues.getSpinnerValues().fetchOccupation(new FetchSpinnerValues.OccupationListener() {
            @Override
            public void onOccupationListener(List<String> userSchool) {
                ArrayAdapter<String> userSchollAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userSchool);
                userSchollAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                careerSpin.setAdapter(userSchollAdapter);
                spinn2 = careerSpin.getSelectedItem().toString();
                //careerText.setText(spinn2);
                //educate.setText(spinn);
            }
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchEducation(new FetchSpinnerValues.EducationListener() {
            @Override
            public void onEducationListener(List<String> qualification) {
                ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, qualification);
                qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(qualificationAdapter);
                spinn = spinner.getSelectedItem().toString();

            }
        },token);

        FetchSpinnerValues.getSpinnerValues().fetchBody(new FetchSpinnerValues.BodyTypeListener() {
            @Override
            public void onBodyTypeListener(List<String> userBody) {
                ArrayAdapter<String> userBodyAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userBody);
                userBodyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                body.setAdapter(userBodyAdapter);
                ret = body.getSelectedItem().toString();
            }
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchEthnicity(userEthnicity -> {
            ArrayAdapter<String> userEhtnicityAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userEthnicity);
            userEhtnicityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ethni.setAdapter(userEhtnicityAdapter);
            spinehnic = ethni.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchFaith(userFaith ->  {
            ArrayAdapter<String> userFaithAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userFaith);
            userFaithAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fait.setAdapter(userFaithAdapter);
            spinfaith = fait.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchPolitics(userPolitics ->  {
            ArrayAdapter<String> userPoliticsAdapetr = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userPolitics);
            userPoliticsAdapetr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            polit.setAdapter(userPoliticsAdapetr);
            spinPolitics = polit.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchChildren(userChildren ->  {
            ArrayAdapter<String> userChildrenAdapetr = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userChildren);
            userChildrenAdapetr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            childre.setAdapter(userChildrenAdapetr);
            spinChildren = childre.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchSmoke(userSmoke ->  {
            ArrayAdapter<String> userSmokeAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userSmoke);
            userSmokeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            smok.setAdapter(userSmokeAdapter);
            spinSmoke = smok.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchDrink(userDrink ->  {
            ArrayAdapter<String> userDrinkAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userDrink);
            userDrinkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            drink.setAdapter(userDrinkAdapter);
            spinDrink = drink.getSelectedItem().toString();
        }, token);

        FetchSpinnerValues.getSpinnerValues().fetchSalay(userSalary ->  {
            ArrayAdapter<String> userSalaryAdapter = new ArrayAdapter<String>(finalProfile.this, android.R.layout.simple_spinner_item, userSalary);
            userSalaryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sala.setAdapter(userSalaryAdapter);
            spinsala = sala.getSelectedItem().toString();
        }, token);
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

                final Geocoder geocoder = new Geocoder(finalProfile.this);
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
                        location.setText(address.getLocality());

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


        Log.d("countryname", countryAbbre);
        // Return the country code for the given country name using the map.
        // Here you will need some validation or better yet
        // a list of countries to give to user to choose from.
        return countryMap.get(countryName); // "NL" for Netherlands.
    }

    private void Hereapi() {
        if (imageUri != null) {

            SpinnerResponse getSpinnerResponse = new SpinnerResponse( "", 2, 3, 4, 5,
                    6, 7, 8, 9, 10);

            getSpinnerResponse.setCareer(spinn2);
            getSpinnerResponse.setEducation_id(zero);
            getSpinnerResponse.setBody_type_id(first);
            getSpinnerResponse.setEthnicity_id(second);
            getSpinnerResponse.setFaith_id(third);
            getSpinnerResponse.setPolitics_id(fourth);
            getSpinnerResponse.setChildren_id(five);
            getSpinnerResponse.setSmoke_id(six);
            getSpinnerResponse.setDrink_id(seven);
            getSpinnerResponse.setIncome_id(eight);
            requestDetails(getSpinnerResponse);


           // Log.d("imageshow", "" + imageUri.toString());
            Log.d("imageshow", "" + spinsala);



            String message = "Successful";
            Toast.makeText(finalProfile.this, message, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(finalProfile.this, LookingFor.class);
            if (imageUri != null) {
                intent.putExtra("images", imageUri);
            }
            if (token != null){
                intent.putExtra("token", token);
            }
            Log.d("TAG", "TOKENSHOW7 " +token);
            startActivity(intent);


            // Call<GetImage> getImageCall = ApiClient.getService().getimage()
        } else {
            String message = "Select details about me";
            Toast.makeText(finalProfile.this, message, Toast.LENGTH_LONG).show();
            //Log.d("imageshow", ""+r);
            // Log.d("Body",new Gson().toJson(response.body()));
        }


    }

    private void requestDetails(SpinnerResponse spinnerResponse) {
        Call<SpinnerRequest> getSpinnerCall = ApiClient.getService().getSpinner(spinnerResponse, "Bearer " + token);
        getSpinnerCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    String message = "Successful";
                    Toast.makeText(finalProfile.this, message, Toast.LENGTH_LONG).show();
                    Log.d("imageview", response.message());
                    Log.d("imageview", response.errorBody().toString());
                    return;
                }


                String message = "Successful";
                Toast.makeText(finalProfile.this, message, Toast.LENGTH_LONG).show();
                Log.d("Body", new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Log.d("No Details Selected", t.getMessage());
            }
        });


    }

    public void go(View view) {
        finish();
    }
}
  /*swipe = findViewById(R.id.textView34);
          swipe.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        if (textView.toString().isEmpty()) {
        textView.setError("Choose your Career");
        //||
        textView.requestFocus();
        //  Toast.makeText()
        } else if (textView.toString().isEmpty()) {

        } else {
        String uid = SharedPref.getInstance(LookingFor.this).getStoredUid();

        GeneralFactory.getGeneralFactory(LookingFor.this).loadSpecUser(uid, new GeneralFactory.SpecificUser() {
@Override
public void loadSpecUse(WaamUser user) {
        Intent intent = new Intent(LookingFor.this, DrawelayoutActivity.class);
        // if (imageUri != null) {
        intent.putExtra("images", imageUri);
        intent.putExtra("toking", token);
        intent.putExtra("WaamUser", user);
        startActivity(intent);
        }
        });

        //}
        }
        }
        });*/



