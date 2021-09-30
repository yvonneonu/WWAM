package com.example.waam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullImage extends AppCompatActivity{
    PhotoView photoView;
    private String uri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        uri =  getIntent().getStringExtra("image");
        photoView = findViewById(R.id.imageView75);

        Glide.with(this)
                .asBitmap()
                .load(uri)
                .into(photoView);


    }

    public void backto(View view) {
        finish();
    }

    public void upload(View view) {

        BottomSheet bottom = new BottomSheet();
        //bottom.show();
        bottom.show(getSupportFragmentManager(), "MED");
        bottom.onSelectedImageListener(new BottomSheet.SelectedImage() {
            @Override
            public void selectedImageListener(Uri uri) {
                if (uri != null){
                    Intent intent = new Intent(FullImage.this, UpdateImage.class);

                    intent.putExtra("image", uri.toString());
                    Log.d("notifyimage", ""+uri);
                    startActivity(intent);

                }else {
                    Log.d("error", "bo image uploaded");

                }

            }
        });

    }


}