package org.kshrd.gamifiedhabittracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kshrd.gamifiedhabittracker.enumeration.HabitStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitLogEntity {

    private UUID habitLogId;
    private LocalDateTime logDate;
    private HabitStatus status;
    private Integer xpEarned;
    private List<HabitEntity> habits;

}
