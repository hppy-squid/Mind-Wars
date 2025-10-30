package com.mindWar.mindWar.openAi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindWar.mindWar.openAi.model.Riddle;

public interface RiddleRepository extends JpaRepository<Riddle, Long> {
    
}
