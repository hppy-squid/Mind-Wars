package com.mindWar.mindWar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class MindWarApplication {

	public static void main(String[] args) {
		System.out.println("DEBUG: Current working directory = " + new java.io.File(".").getAbsolutePath());

    Dotenv dotenv = Dotenv.configure()
                .directory("mindwar") 
                .ignoreIfMissing()
                .load();

        String key = dotenv.get("OPENAI_API_KEY");
		String url = dotenv.get("OPENAI_URL");
        System.out.println("DEBUG: OPENAI_API_KEY = " + key);
		System.out.println("DEBUG: OPENAI_URL = " + url);

        if (key == null) {
            System.err.println("⚠️  OPENAI_API_KEY hittades inte i .env! Kontrollera att .env ligger i projektroten och är korrekt formaterad.");
        } else {
            System.setProperty("OPENAI_API_KEY", key);
        }

		SpringApplication.run(MindWarApplication.class, args);
	}

}
