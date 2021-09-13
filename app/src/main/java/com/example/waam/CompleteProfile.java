package com.example.waam;

import android.content.ContentResolver;
import android.content.ContextWrapper;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.waam.DisplayProfile.ProfileModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompleteProfile extends AppCompatActivity {
    private ImageView firstImage, secondImage, thirdImage, fourthImage, fivethImage, sixthImage,
            seventhImage, eightImage, ninethImage, photo, gallerysave;
    ;
    private TextView wipe, name, location;
    private ImageView image, imagefirst, imagesecond, imagethird, imagefourth, imagefifth,
            imagesixth, imageseveth, imageeight, profile;
    private static final String PROFILEPIC = "profilePic";
    private static final String VIDEOPIC = "videoPic";
    private DatabaseReference mDatabaseRef;
    private StorageTask<UploadTask.TaskSnapshot> mUploads;
    private Task<Uri> uriTask;
    private ProgressBar progressBar;
    private Button save;
    private String token;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance();

        String imageUri = getIntent().getStringExtra("getProfilePics");

//        Log.d("Complete",imageUri);
        String Fullname = getIntent().getStringExtra("nameprofile");
        String tired = getIntent().getStringExtra("token");
        token = SharedPref.getInstance(this).getStoredToken();


        firstImage = findViewById(R.id.imageView0);
        secondImage = findViewById(R.id.imageView1);
        thirdImage = findViewById(R.id.imageView2);
        fourthImage = findViewById(R.id.imageView3);
        fivethImage = findViewById(R.id.imageView4);
        sixthImage = findViewById(R.id.imageView5);
        seventhImage = findViewById(R.id.imageView6);
        eightImage = findViewById(R.id.imageView7);
        ninethImage = findViewById(R.id.imageView8);
        image = findViewById(R.id.image);
        profile = findViewById(R.id.imageView12);
        imagefirst = findViewById(R.id.image1);
        imagesecond = findViewById(R.id.image2);
        imagethird = findViewById(R.id.image3);
        imagefourth = findViewById(R.id.image4);
        imagefifth = findViewById(R.id.image5);
        imagesixth = findViewById(R.id.image6);
        imageseveth = findViewById(R.id.image7);
        imageeight = findViewById(R.id.image8);
        wipe = findViewById(R.id.swipe);
        name = findViewById(R.id.textView19);
        progressBar = findViewById(R.id.progressBar4);
        save = findViewById(R.id.button5);
        location = findViewById(R.id.textView22);

        String uid = FirebaseAuth.getInstance().getUid();

        profileDetail();


       /* if (imageUri != null) {
            Glide.with(this)
                    .asBitmap()
                    .circleCrop()
                    .load(Uri.parse(imageUri))
                    .into(profile);
        }*/


        GeneralFactory.getGeneralFactory(this).loadSpecUser(uid, new GeneralFactory.SpecificUser() {
            @Override
            public void loadSpecUse(WaamUser user) {
                Glide.with(CompleteProfile.this)
                        .asBitmap()
                        .fitCenter()
                        .circleCrop()
                        .load(user.getImageUrl())
                        .into(profile);

                name.setText(user.getFullname());
            }
        });
        // name.setText(Fullname);

      //  name.setText(SharedPref.getInstance(this).getStoredName());

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "TAG");

                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {

                        if (mUploads != null || uriTask != null) {
                            Toast.makeText(CompleteProfile.this, "An upload is ongoing", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            uploadPicOrVid(getFileExtension(uri), uri);
                            Glide.with(CompleteProfile.this)
                                    .asBitmap()
                                    .load(uri)
                                    .into(image);
                            firstImage.setVisibility(View.GONE);
                            LinearLayout linearLayout = findViewById(R.id.linearLayout);
                            linearLayout.setVisibility(View.GONE);
                            bottomSheet.dismiss();
                        }

                    }
                });
            }
        });

        imagefirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "TAG");

                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {

                        if (mUploads != null || uriTask != null) {
                            Toast.makeText(CompleteProfile.this, "An upload is ongoing", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            uploadPicOrVid(getFileExtension(uri), uri);
                            Glide.with(CompleteProfile.this)
                                    .asBitmap()
                                    .load(uri)
                                    .into(imagefirst);
                            Log.d("Bed", "mad");
                            secondImage.setVisibility(View.GONE);
                            Log.d("Bedy", "mad");
                            LinearLayout linearLayout1 = findViewById(R.id.linearLayout1);
                            TextView view1 = findViewById(R.id.text1);
                            view1.setVisibility(View.GONE);
                            linearLayout1.setVisibility(View.INVISIBLE);
                            Log.d("Bedm", "mad");
                            bottomSheet.dismiss();
                        }

                    }
                });
            }
        });

        imagesecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        if (mUploads != null || uriTask != null) {
                            Toast.makeText(CompleteProfile.this, "An upload is ongoiong", Toast.LENGTH_LONG).show();
                        } else {
                            uploadPicOrVid(getFileExtension(uri), uri);
                            Glide.with(CompleteProfile.this)
                                    .asBitmap()
                                    .load(uri)
                                    .into(imagesecond);
                            thirdImage.setVisibility(View.GONE);
                            LinearLayout linearLayout = findViewById(R.id.linearLayout2);
                            linearLayout.setVisibility(View.GONE);
                            bottomSheet.dismiss();
                        }

                    }
                });
            }
        });

        imagethird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        if (mUploads != null || uriTask != null) {
                            Toast.makeText(CompleteProfile.this, "An upload is ongoing", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            uploadPicOrVid(getFileExtension(uri), uri);
                            Glide.with(CompleteProfile.this)
                                    .asBitmap()
                                    .load(uri)
                                    .into(imagethird);
                            fourthImage.setVisibility(View.GONE);
                            LinearLayout linearLayout = findViewById(R.id.linearLayout3);
                            linearLayout.setVisibility(View.GONE);
                            bottomSheet.dismiss();
                        }
                    }
                });
            }
        });

        imagefourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {

                        if (mUploads != null || uriTask != null) {
                            Toast.makeText(CompleteProfile.this, "An upload is ongoing", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            uploadPicOrVid(getFileExtension(uri), uri);
                            Glide.with(CompleteProfile.this)
                                    .asBitmap()
                                    .load(uri)
                                    .into(imagefourth);
                            fivethImage.setVisibility(View.GONE);
                            LinearLayout linearLayout = findViewById(R.id.linearLayout4);
                            linearLayout.setVisibility(View.GONE);
                            bottomSheet.dismiss();
                        }
                    }
                });
            }
        });

        imagefifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {

                        if (mUploads != null || uriTask != null) {
                            Toast.makeText(CompleteProfile.this, "An upload is ongoing", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            uploadPicOrVid(getFileExtension(uri), uri);
                            Glide.with(CompleteProfile.this)
                                    .asBitmap()
                                    .load(uri)
                                    .into(imagefifth);
                            bottomSheet.dismiss();
                            sixthImage.setVisibility(View.GONE);
                            LinearLayout linearLayout = findViewById(R.id.linearLayout5);
                            linearLayout.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        imagesixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {
                        if (mUploads != null || uriTask != null) {
                            Toast.makeText(CompleteProfile.this, "An upload is ongoing", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            uploadPicOrVid(getFileExtension(uri), uri);
                            Glide.with(CompleteProfile.this)
                                    .asBitmap()
                                    .load(uri)
                                    .into(imagesixth);

                            seventhImage.setVisibility(View.GONE);
                            LinearLayout linearLayout = findViewById(R.id.linearLayout6);
                            linearLayout.setVisibility(View.GONE);
                            bottomSheet.dismiss();
                        }
                    }
                });
            }
        });

        imageseveth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {

                        if (mUploads != null || uriTask != null) {
                            Toast.makeText(CompleteProfile.this, "An upload is ongoing", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            uploadPicOrVid(getFileExtension(uri), uri);
                            Glide.with(CompleteProfile.this)
                                    .asBitmap()
                                    .load(uri)
                                    .into(imageseveth);
                            eightImage.setVisibility(View.GONE);
                            LinearLayout linearLayout = findViewById(R.id.linearLayout7);
                            linearLayout.setVisibility(View.GONE);
                            bottomSheet.dismiss();
                        }

                    }
                });
            }
        });

        imageeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "TAG");
                bottomSheet.onSelectedImageListener(new BottomSheet.SelectedImage() {
                    @Override
                    public void selectedImageListener(Uri uri) {

                        if (mUploads != null || uriTask != null) {
                            Toast.makeText(CompleteProfile.this, "An upload is ongoing", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            uploadPicOrVid(getFileExtension(uri), uri);
                            Glide.with(CompleteProfile.this)
                                    .asBitmap()
                                    .load(uri)
                                    .into(imageeight);
                            ninethImage.setVisibility(View.GONE);
                            LinearLayout linearLayout = findViewById(R.id.linearLayout8);
                            linearLayout.setVisibility(View.GONE);
                            bottomSheet.dismiss();
                        }

                    }
                });
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null){
                    Intent intent = new Intent(CompleteProfile.this, finalProfile.class);
                    if (imageUri != null) {
                        intent.putExtra("image", imageUri.toString());
                    }

                    if (tired != null) {
                        intent.putExtra("everytoken", tired);
                    }

                    intent.putExtra("name", Fullname);
                    Log.d("TAG", ""+Fullname);
                    Log.d("TAG", "TOKENSHOW7 " + tired);
                    startActivity(intent);
                }else {
                    Toast.makeText(CompleteProfile.this, "Please Upload An Image", Toast.LENGTH_LONG).show();
                    Log.d("Swip", "wipe");
                }
            }
        });
        wipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(CompleteProfile.this, finalProfile.class);


                    startActivity(intent);

            }
        });

    }

    private String getFileExtension(Uri uri) {
        // This was just a test
        ContextWrapper rapper = new ContextWrapper(this);
        ContentResolver resolver = rapper.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(resolver.getType(uri));
    }


    public void uploadPicOrVid(String filetype, Uri uri) {
        String uid = FirebaseAuth.getInstance().getUid();
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference(VIDEOPIC).child(uid);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(VIDEOPIC).child(uid);
        progressBar.setVisibility(View.VISIBLE);

        if (uri != null) {
            final StorageReference fileref = mStorageRef.child(System.currentTimeMillis() + "." + filetype);
            VideoPicModel videoPicModel = new VideoPicModel();

            if (filetype.equals("jpg") || filetype.equals("jpeg") || filetype.equals("png") ) {
                mUploads = fileref.putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                String uploadId = mDatabaseRef.push().getKey();
                                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        videoPicModel.setVideo(false);
                                        videoPicModel.setVideoPicUrl(uri.toString());
                                        mDatabaseRef.child(uploadId).setValue(videoPicModel);
                                        progressBar.setVisibility(View.GONE);
                                        //This might crash it;
                                        mUploads = null;
                                    }
                                });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(CompleteProfile.this, e.toString(), Toast.LENGTH_LONG).show();
                                //This might crash it;
                                mUploads = null;
                            }
                        });

            } else {
                uriTask = fileref.putFile(uri).continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileref.getDownloadUrl();
                })
                        .addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUrl = task.getResult();
                                    String uploadId = mDatabaseRef.push().getKey();
                                    videoPicModel.setVideo(true);
                                    videoPicModel.setVideoPicUrl(downloadUrl.toString());
                                    mDatabaseRef.child(uploadId).setValue(videoPicModel);
                                    progressBar.setVisibility(View.GONE);
                                    //This might crash it;
                                    uriTask = null;
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                //This might crash it;
                                uriTask = null;
                            }
                        });

            }


        } else {
            Log.d("CompleteProfile", "No image or video was selected");
        }

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

                final Geocoder geocoder = new Geocoder(CompleteProfile.this);
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

    public void gob(View view) {
        finish();
    }
}