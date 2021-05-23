package com.example.waam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.waam.layout.GridVideoViewContainer;
import com.example.waam.layout.SmallVideoViewAdapter;
import com.example.waam.model.UserStatusData;
import com.example.waam.ui.RecyclerItemClickListener;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;

public class VideoCallActivity extends AppCompatActivity {

    public static final int LAYOUT_TYPE_DEFAULT = 0;
    public static final int LAYOUT_TYPE_SMALL = 1;

    private String channelName;
    private static final String TAG = VideoCallActivity.class.getName();
    public int mLayoutType = LAYOUT_TYPE_DEFAULT;
    private static final int PERMISSION_REQ_ID = 22;
    RtcEngine mRtcEngine;
    private ImageView mCallBtn, mMuteBtn, mSwitchVoiceBtn;
    private GridVideoViewContainer mGridVideoViewContainer;
    private boolean isCalling = true;
    private boolean isMuted = false;
    private boolean isVoiceChanged = false;
    private boolean mIsLandscape = false;
    private RelativeLayout mSmallVideoViewDock;
    private SmallVideoViewAdapter mSmallVideoViewAdapter;
    private boolean mIsPeerToPeerMode = true;
    private String mActualTarget;
    private WaamUser waamUser;
    private IRtcEngineEventHandler  mRtcEventHandler;
    private String appID = "b88e7fe917734f2ba7c356df6a380392";

    private static final String[] REQUESTED_PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.hide();
        }

        setContentView(R.layout.activity_video_calling);
        getExtras();
        initUI();

        if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[2], PERMISSION_REQ_ID)) {
            initEngineAndJoinChannel();
        }




        setUpLocalVideo();
    }

    private void initEngineAndJoinChannel() {
        initializeEngine();
        setupLocalVideo();
        joinChannel();

    }

    private void initializeEngine() {
    }

    private void setupLocalVideo(){

    }

    private void joinChannel(){

    }


    private void initUI() {
        mCallBtn = findViewById(R.id.start_call_end_call_btn);
        mMuteBtn = findViewById(R.id.audio_mute_audio_unmute_btn);
        mSwitchVoiceBtn = findViewById(R.id.switch_voice_btn);

        mGridVideoViewContainer = findViewById(R.id.grid_video_view_container);
        mGridVideoViewContainer.setItemEventHandler(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //can add single click listener logic
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //can add long click listener logic
            }

            @Override
            public void onItemDoubleClick(View view, int position) {
                onBigVideoViewDoubleClicked(view, position);
            }
        });
    }



    private void onBigVideoViewDoubleClicked(View view, int position) {
        /*if (mUidsList.size() < 2) {
            return;
        }

        UserStatusData user = mGridVideoViewContainer.getItem(position);
        int uid = (user.mUid == 0) ? this.waamUser.getAgoraUid() : user.mUid;

        if (mLayoutType == LAYOUT_TYPE_DEFAULT && mUidsList.size() != 1) {
            switchToSmallVideoView(uid);
        } else {
            switchToDefaultVideoView();
        }*/
    }

    public void onVideoChatClicked(View view) {
    }

    public void onSwitchVoiceClicked(View view) {
    }

    public void onLocalAudioMuteClicked(View view) {
        mRtcEngine.muteLocalAudioStream(isMuted);
    }

    public void onSwitchCameraClicked(View view) {
        mRtcEngine.switchCamera();
    }

    public void onCallClicked(View view) {
    }

    private void getExtras() {
        channelName = getIntent().getExtras().getString("Channel");
        waamUser = getIntent().getExtras().getParcelable("User");
        mIsPeerToPeerMode = getIntent().getBooleanExtra(ChatMessage.INTENT_EXTRA_IS_PEER_MODE, true);
        mActualTarget = getIntent().getExtras().getString("Actual Target");
    }

    private void setUpLocalVideo(){
        mRtcEngine.enableVideo();
        SurfaceView surfaceView = RtcEngine.CreateRendererView(getBaseContext());
        mRtcEngine.setupLocalVideo(new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, 0));
    }
}