package com.mindWar.mindWar.openAi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindWar.mindWar.openAi.dto.OpenAiResponse;
import com.mindWar.mindWar.openAi.model.Riddle;
import com.mindWar.mindWar.openAi.service.RiddleService;

@RestController
public class RiddleController {


    @Autowired
    private RiddleService riddleService;

      public RiddleController(RiddleService riddleService) {
        this.riddleService = riddleService;
    }


//     @PostMapping("/chat")
//     public String chat(@RequestBody String prompt) { 
        
//         OpenAiResponse response = riddleService.sendChatResponse("Ge mig en en gåta och rätt svar på svenska.");


//         return response.getChoices().get(0).getMessage().getContent();
    
// }
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