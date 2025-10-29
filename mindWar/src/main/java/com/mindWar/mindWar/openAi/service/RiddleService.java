package com.mindWar.mindWar.openAi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mindWar.mindWar.openAi.dto.OpenAiRequest;
import com.mindWar.mindWar.openAi.dto.OpenAiResponse;
import com.mindWar.mindWar.openAi.model.Riddle;
import com.mindWar.mindWar.openAi.repository.RiddleRepository;

@Service
public class RiddleService {
    
      @Value("${openai.api.url}")
    String apiUrl;

    private final RestTemplate restTemplate;
    private final RiddleRepository riddleRepository;
    
    public RiddleService(RestTemplate restTemplate, RiddleRepository riddleRepository) {
        this.restTemplate = restTemplate;
        this.riddleRepository = riddleRepository;
    }
    

    public Riddle generateRiddle() {
        String prompt = "Ge mig en gåta och rätt svar på svenska i formatet: 'Gåta: ... Svar: ...'";

        OpenAiRequest request = new OpenAiRequest("gpt-4o", prompt, 1);
        OpenAiResponse response = restTemplate.postForObject(apiUrl, request, OpenAiResponse.class);

        String content = response.getChoices().get(0).getMessage().getContent();

        String riddle = content.split("Svar:")[0].replace("Gåta:", "").trim();
        String answer = content.split("Svar:")[1].trim();

        Riddle entity = new Riddle(riddle, answer);
        Riddle saved = riddleRepository.save(entity);

        return saved;
    }

    public boolean checkAnswer(Long riddleId, String userGuess) {
        Riddle riddle = riddleRepository.findById(riddleId)
                .orElseThrow(() -> new RuntimeException("Riddle not found"));

        return isCloseEnough(userGuess, riddle.getAnswer());
    }

     private boolean isCloseEnough(String guess, String answer) {
        if (guess == null || answer == null) return false;

        String normalizedGuess = guess.trim().toLowerCase().replaceAll("[^a-zåäö0-9 ]", "");
        String normalizedAnswer = answer.trim().toLowerCase().replaceAll("[^a-zåäö0-9 ]", "");

        normalizedGuess = normalizedGuess.replaceAll("\\s+", " ").trim();
        normalizedAnswer = normalizedAnswer.replaceAll("\\s+", " ").trim();

        // Exakt eller delvis matchning
        return normalizedAnswer.contains(normalizedGuess) || normalizedGuess.contains(normalizedAnswer);
    }
    
}
