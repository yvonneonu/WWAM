package com.example.waam;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

public class Profile extends AppCompatActivity {

    private static final int PICTURE_TAKEN = 4 ;
    //public static final String KEY_User_Document1 = "doc1";
    String pathFile;
    // private String Document_img1="";


    ImageView imageView;
    TextView textView, gallery, wipe;
    private int requestCode;
    private int resultCode;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private Uri photouri;
    private Intent data;
    private String profilePics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String Fullname = getIntent().getStringExtra("name");
        String bigTokeng = getIntent().getStringExtra("alltoken");

        textView = findViewById(R.id.captureImage);
        imageView = findViewById(R.id.imageView);
        gallery = findViewById(R.id.galary);
        wipe = findViewById(R.id.swipe);
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });


        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        wipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null){

                    Intent intent = new Intent(Profile.this, Interest.class);
                    Log.d("ImageUri",imageUri.toString());
                    intent.putExtra("profilepics", imageUri.toString());
                    intent.putExtra("name", Fullname);
                    intent.putExtra("mytoken", bigTokeng);
                    Log.d("TAG", "TOKENSHOW5 " +bigTokeng);
                    startActivity(intent);
                }else {
                    Toast.makeText(Profile.this, "please select an image", Toast.LENGTH_LONG).show();
                    Log.d("Swip", "wipe");
                }

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
                //Bitmap bitmap = BitmapFactory.decodeFile(pathFile);
                Glide.with(this)
                        .asBitmap()
                        .circleCrop()
                        .load(imageUri)
                        .into(imageView);
                //imageView.setImageBitmap(bitmap);

        }else if(resultCode == RESULT_OK && requestCode == PICTURE_TAKEN){
            imageUri = photouri;
            Glide.with(this)
                    .asBitmap()
                    .circleCrop()
                    .load(imageUri)
                    .into(imageView);

        }else{
            Toast.makeText(this,"No image was selected ",Toast.LENGTH_SHORT).show();
        }
    }

    // @RequiresApi(api = Build.VERSION_CODES.N)
    private void dispatchTakePictureIntent() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile ;
            photoFile = createPhotoFile();
            if (photoFile != null) {

                pathFile = photoFile.getAbsolutePath();
                photouri = FileProvider.getUriForFile(Profile.this, "com.example.android.fileprovider", photoFile);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photouri);
                startActivityForResult(takePicture, PICTURE_TAKEN);
            }

            // Continue only if the File was successfully created
        }
    }


    private File createPhotoFile() {

        //  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        String name = String.valueOf(System.currentTimeMillis());
        File storagepath = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, "jpeg", storagepath);
        } catch (IOException e) {
            Log.d("mylog", "Excep: " + e.toString());
        }
        return image;

    }




    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }




}




