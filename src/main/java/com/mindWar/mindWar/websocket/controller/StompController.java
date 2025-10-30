package com.mindWar.mindWar.websocket.controller;

import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mindWar.mindWar.openAi.model.Riddle;
import com.mindWar.mindWar.openAi.service.RiddleService;
import com.mindWar.mindWar.websocket.model.Chat;
import com.mindWar.mindWar.websocket.model.ChatMessage;
import com.mindWar.mindWar.websocket.model.Hello;
import com.mindWar.mindWar.websocket.model.HelloMessage;

@Controller
@CrossOrigin(origins = "*")
public class StompController {

        private final SimpMessagingTemplate messagingTemplate;
    private final RiddleService riddleService;

    public StompController(SimpMessagingTemplate messagingTemplate, RiddleService riddleService) {
        this.messagingTemplate = messagingTemplate;
        this.riddleService = riddleService;
    }

        @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Hello hello(HelloMessage message){
        System.out.println("hello");
        return new Hello("Hello: "  + message.getName() + " has entered the chat!");
    }

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public Chat chat(ChatMessage chat){
        System.out.println("chat");
        return new Chat(chat.getName() + ": " + chat.getContent());
}

 @MessageMapping("/riddle/next")
    public void nextRiddle() {
        Riddle newRiddle = riddleService.generateRiddle();

        // skicka ut till alla spelare via topic
        messagingTemplate.convertAndSend("/topic/riddle", newRiddle);
    }

    @MessageMapping("/riddle/correct")
    public void correctGuess(@Payload String playerName) {
        messagingTemplate.convertAndSend("/topic/riddle",
            Map.of("type", "CORRECT_GUESS", "player", playerName));
    }
    
}
