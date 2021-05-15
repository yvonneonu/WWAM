package com.example.waam;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ChatMessage extends AppCompatActivity {
    public static final String NEW_FRIENDS = "com.example.waam.WaamUserFromChatList";
    public static final String FRIENDS = "com.example.waam.WaamUserFromFriends";
    private RecyclerView recyclerView;
    private ChatScreenAdapter chatScreenAdapter;
    private List<Chat> chats;
    private GeneralFactory generalFactoryInstance;
    private WaamUser contactlist;
    private WaamUser userFriends;
    private TextView textViewStatus;

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
        }







    }

    public void goback(View view) {
        finish();
    }
}