package com.example.bettingappmatcheventconsumerservice.services;


import com.example.bettingappmatcheventconsumerservice.events.MatchCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MatchEventListener {

    @KafkaListener(topics = "match-events", groupId = "match-event-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeMatchCreatedEvent(MatchCreatedEvent event) {
        log.info("🎯 Match Created Event received:");
        log.info("→ ID: {}", event.getId());
        log.info("→ {} vs {}", event.getHomeTeam(), event.getAwayTeam());
        log.info("→ Start Time: {}", event.getStartTime());
        log.info("→ Status: {}", event.getStatus());
    }
}
