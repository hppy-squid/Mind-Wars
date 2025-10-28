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

    // public OpenAiResponse sendChatResponse(String prompt) {


    //     OpenAiRequest chatRequest = new OpenAiRequest("gpt-4o", prompt, 1);
    //     OpenAiResponse response = restTemplate.postForObject(apiUrl, chatRequest, OpenAiResponse.class);

    //     return response;
    // }

    public Riddle generateRiddle() {
        String prompt = "Ge mig en gåta och rätt svar på svenska i formatet: 'Gåta: ... Svar: ...'";

        OpenAiRequest request = new OpenAiRequest("gpt-4o", prompt, 1);
        OpenAiResponse response = restTemplate.postForObject(apiUrl, request, OpenAiResponse.class);

        String content = response.getChoices().get(0).getMessage().getContent();

        // Enkelt sätt att dela upp texten
        String question = content.split("Svar:")[0].replace("Gåta:", "").trim();
        String answer = content.split("Svar:")[1].trim();

        Riddle riddle = new Riddle(question, answer);
        riddleRepository.save(riddle);

        return riddle;
    }

    public boolean checkAnswer(Long riddleId, String userGuess) {
        Riddle riddle = riddleRepository.findById(riddleId)
                .orElseThrow(() -> new RuntimeException("Riddle not found"));

        return riddle.getAnswer().equalsIgnoreCase(userGuess.trim());
    }
    
}
