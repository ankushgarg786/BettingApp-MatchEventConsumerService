package com.example.bettingappmatcheventconsumerservice.dtos;

import com.ankush.bettingappentityservice.models.MatchStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreUpdateDTO {
    private Long matchId;
    private String scoreSummary;
    private MatchStatus status;
    private LocalDateTime updatedAt;
}
