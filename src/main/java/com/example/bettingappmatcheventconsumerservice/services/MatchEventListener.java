package com.example.bettingappmatcheventconsumerservice.services;


import com.example.bettingappmatcheventconsumerservice.dtos.ScoreUpdateDTO;
import com.example.bettingappmatcheventconsumerservice.events.MatchCreatedEvent;
import com.example.bettingappmatcheventconsumerservice.events.ScoreUpdateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MatchEventListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "match-events", groupId = "match-event-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeMatchCreatedEvent(MatchCreatedEvent event) {
        log.info("🎯 Match Created Event received:");
        log.info("→ ID: {}", event.getId());
        log.info("→ {} vs {}", event.getHomeTeam(), event.getAwayTeam());
        log.info("→ Start Time: {}", event.getStartTime());
        log.info("→ Status: {}", event.getStatus());
    }
    @KafkaListener(topics = "score-updates", groupId = "score-update-consumer-group")
    public void consumeScoreUpdate(ScoreUpdateEvent event) {
        System.out.println("📥 Score Update for Match ID " + event.getMatchId() + ": " + event.getScoreSummary());
        // In the future: update DB or broadcast to frontend via WebSocket
        ScoreUpdateDTO dto = ScoreUpdateDTO.builder()
                .matchId(event.getMatchId())
                .scoreSummary(event.getScoreSummary())
                .status(event.getStatus())
                .updatedAt(event.getUpdatedAt())
                .build();

        // Broadcast to WebSocket clients
        messagingTemplate.convertAndSend("/topic/match/" + dto.getMatchId(), dto);
    }

}
