package com.example.waam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;


public class CallingActivity extends AppCompatActivity {
    private TextView nameContact;
    private ImageView profileImage;
    private ImageView cancelCallBtn, makeCallBtn;
    private WaamUser userFriends, contactlist;
    private GeneralFactory generalFactory;
    RelativeLayout mRemoteContainer;


    String token= "006b88e7fe917734f2ba7c356df6a380392IAA2iWaFQi9obvdxBw48sSkyvPv2/YAV7hlVB0jfYBJFKOIVUyEAAAAAEAD4YHXYTEekYAEAAQBMR6Rg";
    private static final String LOG_TAG = CallingActivity.class.getSimpleName();

    private static final int PERMISSION_REQ_ID = 22;

    // permission WRITE_EXTERNAL_STORAGE is not mandatory for Agora RTC SDK, just incase if you wanna save logs to external sdcard
    private static final String[] REQUESTED_PERMISSIONS = {Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling2);
        //reciverUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userFriends = (WaamUser) getIntent().getSerializableExtra("user_contact");
        contactlist = (WaamUser) getIntent().getSerializableExtra("contact_id");
        nameContact = findViewById(R.id.name_calling);
        profileImage = findViewById(R.id.profile_image_calling);
        //  cancelCallBtn = findViewById(R.id.cancel_call);
        //  makeCallBtn = findViewById(R.id.make_call);

      //  mRtcEngine = RtcEngine.

        /*if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[2], PERMISSION_REQ_ID)) {
            initAgoraEngineAndJoinChannel();
        }*/


//        setupVideoProfile();
       if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[2], PERMISSION_REQ_ID)) {
            initAgoraEngineAndJoinChannel();

        }


        generalFactory = GeneralFactory.getGeneralFactory(CallingActivity.this);

        if (userFriends != null) {
            nameContact.setText(userFriends.getFullname());
            Glide.with(this)
                    .asBitmap()
                    .load(userFriends.getImageUrl())
                    .into(profileImage);
        } else {
            nameContact.setText(contactlist.getFullname());
            Glide.with(this)
                    .asBitmap()
                    .load(contactlist.getImageUrl())
                    .into(profileImage);
        }


    }

    private void initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine();
        setupVideoProfile();
        setupLocalVideo();
        joinChannel();
    }



    private boolean checkSelfPermission(String permission, int requestCode) {
        Log.i(LOG_TAG, "checkSelfPermission " + permission + " " + requestCode);
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    REQUESTED_PERMISSIONS,
                    requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        Log.i(LOG_TAG, "onRequestPermissionsResult " + grantResults[0] + " " + requestCode);

        switch (requestCode) {
            case PERMISSION_REQ_ID: {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED || grantResults[2] != PackageManager.PERMISSION_GRANTED) {
                    showLongToast("Need permissions " + Manifest.permission.RECORD_AUDIO + "/" + Manifest.permission.CAMERA + "/" + Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    finish();
                    break;
                }

                initAgoraEngineAndJoinChannel();
                break;
            }
        }
    }


    private final void showLongToast(final String msg) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

  /*  public void onCallRated(View view) {
        String description = ((EditText) findViewById(R.id.rateMessage)).getText().toString();
        int rating = ((SeekBar) findViewById(R.id.rateNumber)).getProgress() + 1;

        Log.i(LOG_TAG, "Description: " + description);
        Log.i(LOG_TAG, "Rating: " + rating);

        String callId = mRtcEngine.getCallId();
        int result = mRtcEngine.rate(callId, rating, description);

        if (result == 0) {
            showLongToast("Successfully rated the call!");
        } else {
            showLongToast("Failed to rate the call!");
        }

        Log.i(LOG_TAG, "Success: " + result);
    }*/

    public void onLocalVideoMuteClicked(View view) {
        ImageView iv = (ImageView) view;
        if (iv.isSelected()) {
            iv.setSelected(false);
            iv.clearColorFilter();
        } else {
            iv.setSelected(true);
            iv.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.MULTIPLY);
        }



        FrameLayout container = (FrameLayout) findViewById(R.id.local_video_view_container);
        SurfaceView surfaceView = (SurfaceView) container.getChildAt(0);
        surfaceView.setZOrderMediaOverlay(!iv.isSelected());
        surfaceView.setVisibility(iv.isSelected() ? View.GONE : View.VISIBLE);
    }

    public void onLocalAudioMuteClicked(View view) {
        ImageView iv = (ImageView) view;
        if (iv.isSelected()) {
            iv.setSelected(false);
            iv.clearColorFilter();
        } else {
            iv.setSelected(true);
            iv.setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.MULTIPLY);
        }


    }

    public void onSwitchCameraClicked(View view) {

    }



    public void onEncCallClicked(View view) {
        finish();
    }

    private int mTxQuality = -1, mRxQuality = -1;



    private void updateStatsDisplay() {
        TextView statsDisplay = (TextView) findViewById(R.id.statsDisplay);




    }

    private void initializeAgoraEngine() {

    }

    private void setupVideoProfile() {

    }

    private void setupLocalVideo() {
        FrameLayout container = (FrameLayout) findViewById(R.id.local_video_view_container);

    }

    private void joinChannel() {

    }

    private void setupRemoteVideo(int uid) {
        FrameLayout container = (FrameLayout) findViewById(R.id.remote_video_view_container);

        if (container.getChildCount() >= 1) {
            return;
        }



    }

    private void leaveChannel() {

    }

    private void onRemoteUserLeft() {
        FrameLayout container = (FrameLayout) findViewById(R.id.remote_video_view_container);
        container.removeAllViews();

       // View tipMsg = findViewById(R.id.quick_tips_when_use_agora_sdk); // optional UI
      //  tipMsg.setVisibility(View.VISIBLE);
    }

    private void onRemoteUserVideoMuted(int uid, boolean muted) {
        FrameLayout container = (FrameLayout) findViewById(R.id.remote_video_view_container);

        SurfaceView surfaceView = (SurfaceView) container.getChildAt(0);

        Object tag = surfaceView.getTag();
        if (tag != null && (Integer) tag == uid) {
            surfaceView.setVisibility(muted ? View.GONE : View.VISIBLE);
        }
    }



    /*@Override
    protected void onStart() {
        super.onStart();
        userRef.child(receiverUserId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.hasChild("Calling") && !dataSnapshot.hasChild("Ringing")){
                            final HashMap<String, Object> callingInfo = new HashMap<>();

                            callingInfo.put("uid",senderUserId);
                            callingInfo.put("name",senderUserName);
                            callingInfo.put("image", senderUserImage);
                            callingInfo.put("calling", receiverUserId);

                            userRef.child(senderUserId)
                                    .child("Calling")
                                    .updateChildren(callingInfo)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                final HashMap<String, Object> ringingInfo = new HashMap<>();

                                                ringingInfo.put("uid",receiverUserId);
                                                ringingInfo.put("name",receiverUserName);
                                                ringingInfo.put("image", receiverUserImage);
                                                ringingInfo.put("ringing", senderUserId);

                                                userRef.child(receiverUserId)
                                                        .child("Ringing")
                                                        .updateChildren(ringingInfo);
                                            }
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }*/

  }