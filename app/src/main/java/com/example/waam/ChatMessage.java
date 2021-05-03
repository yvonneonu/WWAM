package com.example.waam;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connectycube.chat.model.ConnectycubeChatDialog;

import java.util.List;

public class ChatMessage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChatScreenAdapter chatScreenAdapter;
    private List<Chat> chats;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);
        String receiverId = getIntent().getStringExtra("receiversId");
        ImageButton imageButtonSender = findViewById(R.id.imageButton);
        generalFactoryInstance = GeneralFactory.getGeneralFactory(this);
        generalFactoryInstance.loadMessages(chatCont -> {
            chats = chatCont;
            chatScreenAdapter = new ChatScreenAdapter(chats, ChatMessage.this);
            recyclerView = findViewById(R.id.recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatMessage.this);
            recyclerView.setAdapter(chatScreenAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
        },receiverId,ChatMessage.this);

        imageButtonSender.setOnClickListener(v -> {
            EditText editText = v.findViewById(R.id.edtMess);
            String messages = editText.toString().trim();
            generalFactoryInstance.sendMessage(messages,receiverId,ChatMessage.this);
        });

    public void goback(View view) {
      finish();
    }
}