package com.mindWar.mindWar.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mindWar.mindWar.websocket.model.Chat;
import com.mindWar.mindWar.websocket.model.ChatMessage;
import com.mindWar.mindWar.websocket.model.Hello;
import com.mindWar.mindWar.websocket.model.HelloMessage;

@Controller
@CrossOrigin(origins = "*")
public class StompController {

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
    
}
