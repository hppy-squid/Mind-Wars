package com.mindWar.mindWar.openAi.dto;

import java.util.ArrayList;
import java.util.List;

import com.mindWar.mindWar.openAi.model.Message;


public class OpenAiRequest {
    
        private String model;
    private List<Message> messages;
    private int n;


    public OpenAiRequest(String model, String promt, int n) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", promt));
        this.n = n;
        
        
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getmessages() {
        return messages;
    }

    public void setmessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

}
