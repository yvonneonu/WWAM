package com.example.waam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.waam.ui.RecyclerItemClickListener;
import com.example.waam.ui.RtlLinearLayoutManager;

import java.util.HashMap;
import java.util.Iterator;



public class VideoCallActivity extends AppCompatActivity {

    public static final int LAYOUT_TYPE_DEFAULT = 0;
    public static final int LAYOUT_TYPE_SMALL = 1;

    private String channelName;
    private static final String TAG = VideoCallActivity.class.getName();
    public static final String AGOREUSER = "agorauser";
    public int mLayoutType = LAYOUT_TYPE_DEFAULT;
    private static final int PERMISSION_REQ_ID = 22;

    private ImageView mCallBtn, mMuteBtn, mSwitchVoiceBtn;
    //private GridVideoViewContainer mGridVideoViewContainer;
    private boolean isCalling = true;
    private boolean isMuted = false;
    private boolean isVoiceChanged = false;
    private boolean mIsLandscape = false;
    private RelativeLayout mSmallVideoViewDock;
   // private SmallVideoViewAdapter mSmallVideoViewAdapter;
    private boolean mIsPeerToPeerMode = true;
    private String mActualTarget;
    private WaamUser waamUser;
   // private User user;

    private final HashMap<Integer, SurfaceView> mUidsList = new HashMap<>();

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




    }






    private boolean checkSelfPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, REQUESTED_PERMISSIONS, requestCode);
            return false;
        }
        return true;
    }









    private void initUI() {
        mCallBtn = findViewById(R.id.start_call_end_call_btn);
        mMuteBtn = findViewById(R.id.audio_mute_audio_unmute_btn);
        mSwitchVoiceBtn = findViewById(R.id.switch_voice_btn);

        //mGridVideoViewContainer = findViewById(R.id.grid_video_view_container);
      /*  mGridVideoViewContainer.setItemEventHandler(new RecyclerItemClickListener.OnItemClickListener() {
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
        });*/
    }



    private void onBigVideoViewDoubleClicked(View view, int position) {

    }


    private void switchToSmallVideoView(int bigBgUid) {
        HashMap<Integer, SurfaceView> slice = new HashMap<>(1);
        slice.put(bigBgUid, mUidsList.get(bigBgUid));
        Iterator<SurfaceView> iterator = mUidsList.values().iterator();
        while (iterator.hasNext()) {
            SurfaceView s = iterator.next();
            s.setZOrderOnTop(true);
            s.setZOrderMediaOverlay(true);
        }

        mUidsList.get(bigBgUid).setZOrderOnTop(false);
        mUidsList.get(bigBgUid).setZOrderMediaOverlay(false);

       // mGridVideoViewContainer.initViewContainer(this, bigBgUid, slice, mIsLandscape);

        bindToSmallVideoView(bigBgUid);

        mLayoutType = LAYOUT_TYPE_SMALL;
    }


    private void bindToSmallVideoView(int exceptUid) {
        if (mSmallVideoViewDock == null) {
            ViewStub stub = (ViewStub) findViewById(R.id.small_video_view_dock);
            mSmallVideoViewDock = (RelativeLayout) stub.inflate();
        }

        boolean twoWayVideoCall = mUidsList.size() == 2;

        RecyclerView recycler = (RecyclerView) findViewById(R.id.small_video_view_container);

        boolean create = false;

       // if (mSmallVideoViewAdapter == null) {
            create = true;
           // mSmallVideoViewAdapter = new SmallVideoViewAdapter(this, this.user.getAgoraUid(), exceptUid, mUidsList);
           // mSmallVideoViewAdapter.setHasStableIds(true);
       // }
        recycler.setHasFixedSize(true);

        if (twoWayVideoCall) {
            recycler.setLayoutManager(new RtlLinearLayoutManager(getApplicationContext(), RtlLinearLayoutManager.HORIZONTAL, false));
        } else {
            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        }
      //  recycler.addItemDecoration(new SmallVideoViewDecoration());
       // recycler.setAdapter(mSmallVideoViewAdapter);
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }

            @Override
            public void onItemDoubleClick(View view, int position) {

            }


        }));

        recycler.setDrawingCacheEnabled(true);
        recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);


        recycler.setVisibility(View.VISIBLE);
        mSmallVideoViewDock.setVisibility(View.VISIBLE);
    }




    public void onVideoChatClicked(View view) {
    }

    public void onSwitchVoiceClicked(View view) {
    }



    public void onCallClicked(View view) {
    }

    private void getExtras() {
        channelName = getIntent().getExtras().getString("Channel");
        waamUser = getIntent().getExtras().getParcelable("User");
        mIsPeerToPeerMode = getIntent().getBooleanExtra(ChatMessage.INTENT_EXTRA_IS_PEER_MODE, true);
        mActualTarget = getIntent().getExtras().getString("Actual Target");
    }


}