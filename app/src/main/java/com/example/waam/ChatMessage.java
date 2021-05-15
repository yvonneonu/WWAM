package com.example.waam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    private ImageView videoButton;


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
        videoButton = findViewById(R.id.Video);
        contactlist =  (WaamUser) getIntent().getSerializableExtra(NEW_FRIENDS);
        userFriends = (WaamUser) getIntent().getSerializableExtra(FRIENDS);


        if(userFriends != null){
            String receiverId = userFriends.getUid();
            if(userFriends.getOnlineStatus().equals("online")){
                textViewStatus.setText(R.string.ONLNE);
            }else{
                textViewStatus.setText(userFriends.getTimeStamp());
            }


            //This loads message on the activity
            generalFactoryInstance.loadMessages(chatCont -> {
                chats = chatCont;
                chatScreenAdapter = new ChatScreenAdapter(chats, ChatMessage.this);
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


        }else{
            String receiverId = contactlist.getUid();
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
                chatScreenAdapter = new ChatScreenAdapter(chats, ChatMessage.this);
                recyclerView = findViewById(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatMessage.this);
                recyclerView.setAdapter(chatScreenAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            },receiverId,ChatMessage.this);

            imageButtonSender.setOnClickListener(v -> {
                String messages = editText.toString().trim();
                generalFactoryInstance.sendMessage(messages,receiverId,ChatMessage.this);
            });
        }



        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callingIntent = new Intent(ChatMessage.this, CallingActivity.class);
                callingIntent.putExtra("callerid", userFriends);
                callingIntent.putExtra("contact_id", contactlist);
                startActivity(callingIntent);
            }
        });

    }

    public void goback(View view) {
      finish();
    }
}