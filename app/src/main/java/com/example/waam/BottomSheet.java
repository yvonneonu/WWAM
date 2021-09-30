package com.example.waam;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class BottomSheet extends BottomSheetDialogFragment {
    private static final int VILLAGEPEOPLE = 1;
    private static final int PICK_IMAGE_VIDEO = 100;
    private Uri photouri;
    private SelectedImage imageListener;
    private static final int MY_CAMERA_REQUEST_CODE = 200;
    private StorageTask<UploadTask.TaskSnapshot> mUploads;


    public BottomSheet() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_picture, container, false);

        TextView takePic = view.findViewById(R.id.textView28);
        TextView cancel = view.findViewById(R.id.textView2);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        takePic.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)

            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    dispatchTakePictureIntent();
                }else {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
                }


               //
            }
        });

        TextView galary = view.findViewById(R.id.textView27);
        //picture picture = new picture();
        galary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery1();
            }
        });
        return view;
    }

    private void dispatchTakePictureIntent() {

        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getActivity().getPackageManager()) != null) {
            Log.d("Dispatch","Yea i am taking picture");
            // Create the File where the photo should go
            File photoFile ;
            photoFile = createPhotoFile();
            if (photoFile != null) {
                Log.d("Dispatch","Photofile is not null");
                String pathFile = photoFile.getAbsolutePath();
                photouri = FileProvider.getUriForFile(getActivity(), "com.example.android.fileprovider", photoFile);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photouri);
                startActivityForResult(takePicture, VILLAGEPEOPLE);
            }
        }
    }

    private File createPhotoFile() {

            //  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            String name = String.valueOf(System.currentTimeMillis());
            File storagepath = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = null;
            try {
                image = File.createTempFile(name, "png", storagepath);
            } catch (IOException e) {
                Log.d("mylog", "Excep: " + e.toString());
            }
            return image;
    }

    private void openGallery1() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE_VIDEO);
        Log.d("tag", "dncnncn");
    }


    public void onSelectedImageListener(SelectedImage imageListener){
        this.imageListener = imageListener;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imageUri;
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_VIDEO) {
             imageUri = data.getData();
             if(imageListener != null){
                 imageListener.selectedImageListener(imageUri);
             }
        }else if(resultCode == Activity.RESULT_OK && requestCode == VILLAGEPEOPLE ){
            imageUri = photouri;
            if(imageListener != null){
                imageListener.selectedImageListener(imageUri);
            }
        }else{
            Toast.makeText(getActivity(),"Nothing was clicked",Toast.LENGTH_SHORT).show();

        }

    }




    public interface SelectedImage{
        void selectedImageListener(Uri uri);
    }



}

