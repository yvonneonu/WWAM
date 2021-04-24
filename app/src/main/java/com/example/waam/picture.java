package com.example.waam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class picture extends AppCompatActivity {

    String pathFile;
    ImageView imageView;
    TextView gallery, takePicsave, cancel;
    private int requestCode;
    private int resultCode;
    Button wipe;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        gallery = findViewById(R.id.textView27);
        takePicsave = findViewById(R.id.textView28);
        cancel = findViewById(R.id.textView2);

       // if (Build.VERSION.SDK_INT >= 23) {
            //requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
       // }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bottom

            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openGallery1();
                Log.d("tag", "tailking");
            }
        });

        takePicsave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent1();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK || requestCode == PICK_IMAGE){
            // imageUri = data.getData();
            imageView.setImageURI(imageUri);
            if (requestCode == 1){
                Bitmap bitmap = BitmapFactory.decodeFile(pathFile);
                imageView.setImageBitmap(bitmap);

            }
        }
    }

    private void dispatchTakePictureIntent1() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            photoFile = createPhotoFile();
            if (photoFile != null) {

                pathFile = photoFile.getAbsolutePath();
                Uri photouri = FileProvider.getUriForFile(picture.this, "com.example.android.fileprovider", photoFile);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photouri);
                startActivityForResult(takePicture, 1);
                Log.d("tag", "bhbjhjhjhsv");
            }
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


    private void openGallery1() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
        Log.d("tag", "dncnncn");
    }
}