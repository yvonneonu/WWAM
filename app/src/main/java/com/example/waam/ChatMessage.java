package com.example.waam;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatMessage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChatScreenAdapter chatScreenAdapter;
    private List<Chat> chats;
    private GeneralFactory generalFactoryInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);
        chats = GeneralFactory.getGeneralFactory().getChatList();
        recyclerView = findViewById(R.id.recyclerView);
        chatScreenAdapter = new ChatScreenAdapter(chats, ChatMessage.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatMessage.this);
        recyclerView.setAdapter(chatScreenAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        // Provide chat connection configuration


        imageButtonSender.setOnClickListener(v -> {
            EditText editText = v.findViewById(R.id.edtMess);
            String messages = editText.toString().trim();
            generalFactoryInstance.sendMessage(messages,receiverId,ChatMessage.this);
        });

    }

    public void goback(View view) {
        finish();
    }
}