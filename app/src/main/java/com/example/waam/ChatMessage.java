package com.example.waam;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connectycube.chat.ConnectycubeChatService;
import com.connectycube.chat.IncomingMessagesManager;
import com.connectycube.chat.exception.ChatException;
import com.connectycube.chat.listeners.ChatDialogMessageListener;
import com.connectycube.chat.model.ConnectycubeChatDialog;
import com.connectycube.chat.model.ConnectycubeChatMessage;

import org.jivesoftware.smack.SmackException;

import java.util.List;

public class ChatMessage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChatScreenAdapter chatScreenAdapter;
    private List<Chat> chats;
    ConnectycubeChatDialog privateDialog;
    private GeneralFactory generalFactoryInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);
        ImageButton imageButtonSender = findViewById(R.id.imageButton);
        chats = GeneralFactory.getGeneralFactory().getChatList();
        recyclerView = findViewById(R.id.recyclerView);
        chatScreenAdapter = new ChatScreenAdapter(chats, ChatMessage.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatMessage.this);
        recyclerView.setAdapter(chatScreenAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        // Provide chat connection configuration


        imageButtonSender.setOnClickListener(v -> {

            EditText editText = findViewById(R.id.edtMess);

            String message = editText.toString().trim();
            try {
                privateDialog = new ConnectycubeChatDialog();

                ConnectycubeChatMessage chatMessage = new ConnectycubeChatMessage();
                chatMessage.setBody("How are you today?");
                chatMessage.setSaveToHistory(true);

//                privateDialog.sendMessage(message);
              //  privateDialog.sendMessage(chatMessage);
              //  privateDialog.sendEncryptedMessage(chatMessage);
                privateDialog.sendMessage(chatMessage);
            } catch (SmackException.NotConnectedException | InterruptedException e) {

            }

            privateDialog.addMessageListener(new ChatDialogMessageListener() {
                @Override
                public void processMessage(String dialogId, ConnectycubeChatMessage message, Integer senderId) {

                }

                @Override
                public void processError(String dialogId, ChatException exception, ConnectycubeChatMessage message, Integer senderId) {

                }
            });


            ConnectycubeChatService chatService = ConnectycubeChatService.getInstance();
            IncomingMessagesManager incomingMessagesManager = chatService.getIncomingMessagesManager();
                    //chatService.getIncomingMessagesManager();

         // chatService = editText.toString().trim();

            //messages = chatService.getIncomingMessagesManager();
            //messages = chatService.getIncomingMessagesManager("vguy");
            incomingMessagesManager.addDialogMessageListener(new ChatDialogMessageListener() {
                @Override
                public void processMessage(String dialogId, ConnectycubeChatMessage message, Integer senderId) {

                }

                @Override
                public void processError(String dialogId, ChatException exception, ConnectycubeChatMessage message, Integer senderId) {

                }
            });

            //generalFactoryInstance.sendMessage(messages,receiverId,ChatMessage.this);
        });

    }

    public void goback(View view) {
        finish();
    }
}