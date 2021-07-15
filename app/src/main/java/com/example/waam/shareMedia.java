package com.example.waam;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class shareMedia extends AppCompatActivity {
    private ImageView imageView;
   private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_media);

        imageView = findViewById(R.id.textView12);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            image = bundle.getString("typedText");



           // uploadPicOrVid(getFileExtension(uri), uri);
            GeneralFactory.getGeneralFactory(this).loadSpecUser(image, new GeneralFactory.SpecificUser() {
                @Override
                public void loadSpecUse(WaamUser user) {
                    user.setImageUrl(image);
                    Glide.with(shareMedia.this)
                            .asBitmap()
                            .fitCenter()
                            .circleCrop()
                            .load(user.getImageUrl())
                            .into(imageView);

                    //name.setText(user.getFullname());
                }
            });

        }else {
            Log.d("didnt show", ""+image);
        }


    }
}