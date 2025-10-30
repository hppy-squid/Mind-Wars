package com.mindWar.mindWar.websocket.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.mindWar.mindWar.openAi.model.Riddle;
import com.mindWar.mindWar.openAi.service.RiddleService;

@Service
public class WsService {

    private final RiddleService riddleService;
    private final SimpMessagingTemplate messagingTemplate;

     private Riddle currentRiddle;

    public WsService(RiddleService riddleService, SimpMessagingTemplate messagingTemplate) {
        this.riddleService = riddleService;
        this.messagingTemplate = messagingTemplate;
        this.currentRiddle = riddleService.generateRiddle(); // Startgåta
    }

    public Riddle getCurrentRiddle() {
        return currentRiddle;
    }

    public void nextRiddle() {
        currentRiddle = riddleService.generateRiddle();
        broadcastRiddle();
    }

    public boolean handleGuess(String playerName, Long riddleId, String guess) {
        boolean correct = riddleService.checkAnswer(riddleId, guess);
        if (correct) {
            // Skicka meddelande till alla att någon gissade rätt
            Map<String, Object> payload = new HashMap<>();
            payload.put("type", "GUESS_CORRECT");
            payload.put("player", playerName);
            messagingTemplate.convertAndSend("/topic/riddle", payload);
        }
        return correct;
    }

    private void broadcastRiddle() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "NEW_RIDDLE");
        payload.put("id", currentRiddle.getId());
        payload.put("riddle", currentRiddle.getRiddle());
        messagingTemplate.convertAndSend("/topic/riddle", payload);
    }
    
}
