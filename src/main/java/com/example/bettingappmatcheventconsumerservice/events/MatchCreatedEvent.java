package com.example.bettingappmatcheventconsumerservice.events;

import com.ankush.bettingappentityservice.models.MatchStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchCreatedEvent {
    private Long id;
    private String homeTeam;
    private String awayTeam;
    private LocalDateTime startTime;
    private MatchStatus status;
}
