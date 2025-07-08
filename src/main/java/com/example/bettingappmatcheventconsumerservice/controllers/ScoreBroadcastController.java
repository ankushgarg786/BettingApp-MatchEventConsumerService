package com.example.bettingappmatcheventconsumerservice.controllers;

import com.example.bettingappmatcheventconsumerservice.dtos.ScoreUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scores")
public class ScoreBroadcastController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/broadcast")
    public void broadcastScoreUpdate(@RequestBody ScoreUpdateDTO update) {
        messagingTemplate.convertAndSend("/topic/match/" + update.getMatchId(), update);
    }
}
