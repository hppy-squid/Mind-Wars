package com.mindWar.mindWar.openAi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindWar.mindWar.openAi.dto.OpenAiResponse;
import com.mindWar.mindWar.openAi.model.Riddle;
import com.mindWar.mindWar.openAi.service.RiddleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/riddle")
public class RiddleController {

  private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RiddleService riddleService;

      public RiddleController(RiddleService riddleService, SimpMessagingTemplate messagingTemplate) {
        this.riddleService = riddleService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/trigger")
public void triggerRiddle() {
    Riddle newRiddle = riddleService.generateRiddle();
    messagingTemplate.convertAndSend("/topic/riddle", newRiddle);
}

  @PostMapping("/generate")
    public Riddle generateRiddle() {
        return riddleService.generateRiddle();
    }

    @PostMapping("/{id}/guess")
    public String checkGuess(@PathVariable Long id, @RequestBody String guess) {
        boolean correct = riddleService.checkAnswer(id, guess);
        return correct ? "✅ Rätt svar!" : "❌ Fel svar!";
    }
    
}