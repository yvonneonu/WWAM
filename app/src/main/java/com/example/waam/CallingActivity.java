package com.example.waam;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CallingActivity extends AppCompatActivity {
    private TextView nameContact;
    private ImageView profileImage;
    private ImageView cancelCallBtn, makeCallBtn;
    private WaamUser userFriends,contactlist;
    private GeneralFactory generalFactory;


    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling2);
        //reciverUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userFriends = (WaamUser) getIntent().getSerializableExtra("user_contact");
        contactlist = (WaamUser) getIntent().getSerializableExtra("contact_id");
        nameContact = findViewById(R.id.name_calling);
        profileImage = findViewById(R.id.profile_image_calling);
        cancelCallBtn = findViewById(R.id.cancel_call);
        makeCallBtn = findViewById(R.id.make_call);


        generalFactory = GeneralFactory.getGeneralFactory(CallingActivity.this);

        if(userFriends != null){
           nameContact.setText(userFriends.getFullname());
           Glide.with(this)
                   .asBitmap()
                   .load(userFriends.getImageUrl())
                   .into(profileImage);
        }else{
            nameContact.setText(contactlist.getFullname());
            Glide.with(this)
                    .asBitmap()
                    .load(contactlist.getImageUrl())
                    .into(profileImage);
        }


    }



    @Override
    protected void onStart() {
        super.onStart();

       // userRef.child(reciverUserId)
    }
}