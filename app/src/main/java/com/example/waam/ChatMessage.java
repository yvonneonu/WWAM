package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waam.model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ChatMessage extends AppCompatActivity {
    public static final String NEW_FRIENDS = "com.example.waam.WaamUserFromChatList";
    public static final String FRIENDS = "com.example.waam.WaamUserFromFriends";
    public static final String INTENT_EXTRA_IS_PEER_MODE = "videoChat";

    private User user;
    private TextView mTitleTextView;
    private TextView mChatButton, mCallButton;
    private EditText mNameEditText;
    private boolean mIsPeerToPeerMode = true;
    private String mTargetName;
    private RecyclerView recyclerView;
    private ChatScreenAdapter chatScreenAdapter;
    private List<Chat> chats;
    private GeneralFactory generalFactoryInstance;
    private WaamUser contactlist;
    private WaamUser userFriends;
    private TextView textViewStatus;
    private ImageView videoButton;
    public static final int MAX_INPUT_NAME_LENGTH = 64;

    @Override
    protected void onStart() {
        super.onStart();
        generalFactoryInstance.setOnlineStatus("online");

    }

    @Override
    protected void onPause() {
        super.onPause();
        generalFactoryInstance.setOnlineStatus("offline");
        generalFactoryInstance.setTimeStamp();
    }


    @Override
    protected void onResume() {
        super.onResume();
        generalFactoryInstance.setOnlineStatus("online");
    }

    @Override
    protected void onStop() {
        super.onStop();
        generalFactoryInstance.setOnlineStatus("offline");
        generalFactoryInstance.setTimeStamp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        generalFactoryInstance.setOnlineStatus("offline");
        generalFactoryInstance.setTimeStamp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);
        ImageButton imageButtonSender = findViewById(R.id.imageButton);
        generalFactoryInstance = GeneralFactory.getGeneralFactory(this);
        EditText editText = findViewById(R.id.edtMess);
        textViewStatus = findViewById(R.id.status);
        ImageView displayPic = findViewById(R.id.imagetool);
        videoButton = findViewById(R.id.Video);
        contactlist =  (WaamUser) getIntent().getSerializableExtra(NEW_FRIENDS);
        userFriends = (WaamUser) getIntent().getSerializableExtra(FRIENDS);
        String myId = FirebaseAuth.getInstance().getUid();

        if(userFriends != null){
            String receiverId = userFriends.getUid();
            String userImage = userFriends.getImageUrl();

            generalFactoryInstance.loadSpecUser(receiverId, user -> {
                if(user != null){
                    Log.d("LoadSpec",""+user);
                    if(user.getTypingTo().equals(myId)) textViewStatus.setText(R.string.typing);
                    else if(user.getOnlineStatus().equals("online")) textViewStatus.setText(R.string.online);
                    else textViewStatus.setText(user.getTimeStamp());
                    Log.d("TextView",textViewStatus.getText().toString());
                }else{
                    Log.d("LoadSpec","User is null sorry");
                }

            });

            Glide.with(this)
                    .asBitmap()
                    .circleCrop()
                    .load(userFriends.getImageUrl())
                    .into(displayPic);
            if(userFriends.getOnlineStatus().equals("online")){
                textViewStatus.setText(R.string.ONLNE);
            }else{
                textViewStatus.setText(userFriends.getTimeStamp());
            }


            //This loads message on the activity
            generalFactoryInstance.loadMessages(chatCont -> {
                chats = chatCont;
                chatScreenAdapter = new ChatScreenAdapter(chats, ChatMessage.this,userImage);
                recyclerView = findViewById(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatMessage.this);
                recyclerView.setAdapter(chatScreenAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setStackFromEnd(true);
            },receiverId,ChatMessage.this);

            imageButtonSender.setOnClickListener(v -> {
                String messages = editText.getText().toString().trim();
                generalFactoryInstance.sendMessage(messages,receiverId,ChatMessage.this);
                editText.setText("");
            });

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(s.toString().trim().length() == 0){
                        generalFactoryInstance.checkTypingStatus("noOne");
                    }else{
                        generalFactoryInstance.checkTypingStatus(userFriends.getUid());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            videoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callingIntent = new Intent(ChatMessage.this, CallingActivity.class);
                    callingIntent.putExtra("user_contact", userFriends);
                    startActivity(callingIntent);
                    String myName = user.getFireDisplayName();
                    mTargetName = userFriends.getFullname();
                    if (mTargetName.equals("")) {
                        showToast(getString(mIsPeerToPeerMode ? R.string.account_empty : R.string.channel_name_empty));
                    } else if (mTargetName.length() >= MAX_INPUT_NAME_LENGTH) {
                        showToast(getString(mIsPeerToPeerMode ? R.string.account_too_long : R.string.channel_name_too_long));
                    } else if (mTargetName.startsWith(" ")) {
                        showToast(getString(mIsPeerToPeerMode ? R.string.account_starts_with_space : R.string.channel_name_starts_with_space));
                    } else if (mTargetName.equals("null")) {
                        showToast(getString(mIsPeerToPeerMode ? R.string.account_literal_null : R.string.channel_name_literal_null));
                    } else if (mIsPeerToPeerMode && mTargetName.equals(user.getFireDisplayName())) {
                        showToast(getString(R.string.account_cannot_be_yourself));
                    } else {
                        String channelName = "";
                        if (mIsPeerToPeerMode) {
                            channelName = myName.compareTo(mTargetName) < 0 ? myName + mTargetName : mTargetName + myName;

                        }else {
                            channelName = mTargetName;
                        }
                        Intent intent = new Intent(ChatMessage.this, VideoCallActivity.class);
                        intent.putExtra("User", user);
                        intent.putExtra("Channel", channelName);
                        intent.putExtra(INTENT_EXTRA_IS_PEER_MODE, mIsPeerToPeerMode);
                        intent.putExtra("Actual Target", mTargetName);
                        startActivity(intent);
                    }
                }
            });



        }else{
            String receiverId = contactlist.getUid();

            generalFactoryInstance.loadSpecUser(receiverId, user -> {
                if(user != null){
                    Log.d("LoadSpec",""+user);
                    if(user.getTypingTo().equals(myId)) textViewStatus.setText(R.string.typing);
                    else if(user.getOnlineStatus().equals("online")) textViewStatus.setText(R.string.online);
                    else textViewStatus.setText(user.getTimeStamp());
                }else{
                    Log.d("LoadSpec","User is null sorry");
                }

            });

            Glide.with(this)
                    .asBitmap()
                    .circleCrop()
                    .load(contactlist.getImageUrl())
                    .into(displayPic);
            Log.d("Chatlist",contactlist.getUid());
            if(contactlist.getOnlineStatus().equals("online")){
                textViewStatus.setText("online");
            }else{
                textViewStatus.setText(contactlist.getTimeStamp());
            }

            //This loads message on the activity
            generalFactoryInstance.loadMessages(chatCont -> {
                chats = chatCont;
                textViewStatus.setText(contactlist.getOnlineStatus());
                chatScreenAdapter = new ChatScreenAdapter(chats, ChatMessage.this,contactlist.getImageUrl());
                recyclerView = findViewById(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatMessage.this);
                recyclerView.setAdapter(chatScreenAdapter);
                linearLayoutManager.setStackFromEnd(true);
                recyclerView.setLayoutManager(linearLayoutManager);
            },receiverId,ChatMessage.this);

            imageButtonSender.setOnClickListener(v -> {
                String messages = editText.getText().toString().trim();
                generalFactoryInstance.sendMessage(messages,receiverId,ChatMessage.this);
                editText.setText("");
            });

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(s.toString().trim().length() == 0){
                        generalFactoryInstance.checkTypingStatus("noone");
                    }else {
                        generalFactoryInstance.checkTypingStatus(contactlist.getUid());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            videoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callingIntent = new Intent(ChatMessage.this, CallingActivity.class);
                    callingIntent.putExtra("user_contact", contactlist);
                    startActivity(callingIntent);
                }
            });
        }

    }


    private void initUIAndData() {
        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        /*mTitleTextView = findViewById(R.id.selection_title);
        mNameEditText = findViewById(R.id.selection_name);
        RadioGroup modeGroup = findViewById(R.id.mode_radio_group);
        modeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.peer_radio_button:
                        mIsPeerToPeerMode = true;
                        mTitleTextView.setText(getString(R.string.title_peer));
                        mChatButton.setText(getString(R.string.btn_chat));
                        mNameEditText.setHint(getString(R.string.hint_friend));
                        break;
                    case R.id.selection_tab_channel:
                        mIsPeerToPeerMode = false;
                        mTitleTextView.setText(getString(R.string.title_channel));
                        mChatButton.setText(getString(R.string.btn_join));
                        mNameEditText.setHint(getString(R.string.hint_channel));
                        break;
                }
            }
        }

        );


        RadioButton peerMode = findViewById(R.id.peer_radio_button);
        peerMode.setChecked(true);*/
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    public void goback(View view) {
        finish();
    }
}