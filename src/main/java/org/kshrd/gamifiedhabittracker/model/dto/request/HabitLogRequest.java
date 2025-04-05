package org.kshrd.gamifiedhabittracker.model.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.enumeration.HabitStatus;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;

import java.time.LocalDateTime;
import java.util.List;


@Data
@RequiredArgsConstructor
public class HabitLogRequest {
    private LocalDateTime logDate;
    private HabitStatus status;
    private Integer xpEarned;
    private List<HabitEntity> habits;

}
