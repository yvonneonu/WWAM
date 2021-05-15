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
    private WaamUser userFriends;


    private String reciverUserId = "", receiverUserImage= "", receiverUserName="";


    private String senderUserId = "", senderUserImage= "", senderUserName="";

    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling2);

        senderUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

       // reciverUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        reciverUserId = getIntent().getExtras().get("callerid").toString();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        nameContact = findViewById(R.id.name_calling);
        profileImage = findViewById(R.id.profile_image_calling);
        cancelCallBtn = findViewById(R.id.cancel_call);
        makeCallBtn = findViewById(R.id.make_call);
        
        getAndUserProfileInfo();
    }

    private void getAndUserProfileInfo() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(reciverUserId).exists()){
                    receiverUserImage = dataSnapshot.child(reciverUserId).child("image").getValue().toString();
                    receiverUserName = dataSnapshot.child(reciverUserId).child("name").getValue().toString();
                    nameContact.setText(receiverUserName);

                    Glide.with(CallingActivity.this)
                            .asBitmap()
                            .circleCrop()
                            .load(receiverUserImage)
                            .into(profileImage);

                }
                if (dataSnapshot.child(senderUserId).exists()){
                    senderUserImage = dataSnapshot.child(senderUserId).child("image").getValue().toString();
                    senderUserName = dataSnapshot.child(senderUserId).child("name").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

       // userRef.child(reciverUserId)
    }
}