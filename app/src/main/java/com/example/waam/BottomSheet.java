package com.example.waam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import java.io.IOException;

public class BottomSheet extends BottomSheetDialogFragment {
    //picture picture;
    String pathFile;
    private int requestCode;
    private int resultCode;
    Button wipe;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private Intent data;
    //private ImageView image;

    public BottomSheet(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_picture, container, false);
        TextView takePicsave = view.findViewById(R.id.textView28);

       // image = view.findViewById(R.id.linearLayout);
        TextView galary = view.findViewById(R.id.textView27);
        //picture picture = new picture();
        galary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  openGallery1();

            }
        });
        return view;
    }




}
