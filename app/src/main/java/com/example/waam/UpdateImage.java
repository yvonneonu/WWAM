package com.example.waam;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class UpdateImage extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private StorageTask<UploadTask.TaskSnapshot> mUploads;
    private static final String PROFILEPIC = "profilePic";
    private static final String VIDEOPIC = "videoPic";
    public static  final String FROMMEDIA = "fromMedia";
    private static final int PICTURE_TAKEN = 4 ;
    private String uri;
    private PhotoView photoView;
    String pathFile;
    private Uri photouri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_image);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        uri = getIntent().getStringExtra("image");
        photoView = findViewById(R.id.imageView75);

       // uri = photouri;
        Glide.with(this)
                .asBitmap()
                .load(uri)
                .into(photoView);

    }

    public void cancelit(View view) {
        finish();
    }

    public void uploadImage(View view) {

        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {

            // Create the File where the photo should go
            File photoFile ;
            photoFile = createPhotoFile();
            if (photoFile != null) {

                uri = photoFile.getAbsolutePath();
                photouri = FileProvider.getUriForFile(UpdateImage.this, "com.example.android.fileprovider", photoFile);

            }

            // Continue only if the File was successfully created
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

}