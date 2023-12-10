package com.example.myappl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    // Other code
    private ListView mChatListView;
    private EditText mChatInput;
    private Button mChatSendButton;

    private ChatMessageAdapter mAdapter;
    private List<ChatMessage> mMessages;

    private ChatMessageAdapter mChatMessageAdapter;

    private List<ChatMessage> mChatMessages;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Other code
        mChatListView = findViewById(R.id.list_view_messages);
        mChatInput = findViewById(R.id.edit_text_message);
        mChatSendButton = findViewById(R.id.button_send);

        mMessages = new ArrayList<>();
        mAdapter = new ChatMessageAdapter(this, R.layout.list_item_chat_message, mMessages);
        mChatListView.setAdapter(mAdapter);
//        mChatMessageAdapter = new ChatMessageAdapter(this, R.layout.list_item_chat_message, mChatMessages);
//        mChatListView.setAdapter(mChatMessageAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mChatSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mChatInput.getText().toString();
                if (!message.isEmpty()) {
                    String key = mDatabase.child("chats").push().getKey();
                    ChatMessage chatMessage = new ChatMessage(message, getUserId());
                    Map<String, Object> messageValues = chatMessage.toMap();

                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/chats/" + key, messageValues);

                    mDatabase.updateChildren(childUpdates);
                    mChatInput.setText("");
                }
            }
        });

        mDatabase.child("chats").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChatMessage chatMessage = snapshot.getValue(ChatMessage.class);
                 mMessages.add(chatMessage);
                mAdapter.notifyDataSetChanged();
                // Add chat message to listview adapter
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Empty implementation (optional)
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // Empty implementation (optional)
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Empty implementation (optional)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Empty implementation (optional)
            }
        });
    }

    private String getUserId() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            return currentUser.getUid();
        } else {
            return null;
        }
    }


}


