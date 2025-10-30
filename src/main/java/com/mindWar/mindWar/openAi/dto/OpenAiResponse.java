package com.mindWar.mindWar.openAi.dto;

import java.util.List;

import com.mindWar.mindWar.openAi.model.Message;


public class OpenAiResponse {
    
       private List<Choice> choices;

    public OpenAiResponse() {
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    

    public static class Choice {
        private Message message;
 

        public Choice() {
        }


        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }


       

    }
    
}
