package com.example.myappl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {
    private Context mContext;
    private int mResource;

    public ChatMessageAdapter(@NonNull Context context,int resource, @NonNull List<ChatMessage> messages) {
        super(context, resource, messages);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        ChatMessage chatMessage = getItem(position);

        TextView messageTextView = convertView.findViewById(R.id.message_text_view);
        TextView senderTextView = convertView.findViewById(R.id.sender_text_view);

        messageTextView.setText(chatMessage.getMessage());
        senderTextView.setText(chatMessage.getUserId());

        return convertView;
    }
}


