package org.kshrd.gamifiedhabittracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitLogEntity {

    private UUID habitLogId; // Use UUID instead of String
    private LocalDateTime logDate;
    private String status;
    private Integer xpEarned;
    private HabitEntity habits;
}