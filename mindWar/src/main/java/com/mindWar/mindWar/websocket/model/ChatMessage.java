package com.mindWar.mindWar.websocket.model;

public class ChatMessage {

    
    private String content;
    private String name;

    public ChatMessage() {}

    public ChatMessage(String content, String name) {
        this.content = content;
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
