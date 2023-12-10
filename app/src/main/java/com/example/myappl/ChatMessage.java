package com.example.myappl;

import java.util.HashMap;
import java.util.Map;

public class ChatMessage {
    private String message;
    private String userId;

    public ChatMessage() {
        // Default constructor required for Firebase Realtime Database
    }

    public ChatMessage(String message, String userId) {
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("message", message);
        result.put("userId", userId);
        return result;
    }
}


