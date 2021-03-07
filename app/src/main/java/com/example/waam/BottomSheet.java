package com.example.waam;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import java.io.IOException;

public class BottomSheet extends BottomSheetDialogFragment {
    private static final int VILLAGEPEOPLE = 1;
    //picture picture;
    String pathFile;
    private int requestCode;
    private int resultCode;
    Button wipe;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private Intent data;
    private Uri photouri;
    private SelectedImage imageListener;
    private static final int MY_CAMERA_REQUEST_CODE = 100;


    public BottomSheet() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_picture, container, false);
        TextView takePic = view.findViewById(R.id.textView28);


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
                pathFile = photoFile.getAbsolutePath();
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
        startActivityForResult(gallery, PICK_IMAGE);
        Log.d("tag", "dncnncn");
    }


    public void onSelectedImageListener(SelectedImage imageListener){
        this.imageListener = imageListener;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
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

    /*public void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACESS_C)){
            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.title_location_permission)
                    .setMessage(R.string.text_location_permission)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),
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
    }*/

    public interface SelectedImage{
        void selectedImageListener(Uri uri);
    }



}

