package org.kshrd.gamifiedhabittracker.service.implementation;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;
import org.kshrd.gamifiedhabittracker.repository.HabitLogRepository;
import org.kshrd.gamifiedhabittracker.repository.HabitRepository;
import org.kshrd.gamifiedhabittracker.service.HabitLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class HabitLogServiceImp implements HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;
    @Override
    public HabitLogEntity createNewHabitLogService(HabitLogRequest habitLogRequest) {
        // Count how many times the habit has been completed
        int completionCount = habitLogRepository.getCompletionCount(habitLogRequest.getHabitId());

        // Calculate xpEarned based on completion count
        int xpEarned = (completionCount == 0) ? 10 : 100;

        // Fetch the full HabitEntity from DB
        HabitEntity habitEntity = habitRepository.findHabitById(UUID.fromString(habitLogRequest.getHabitId()));

        // Check if habit exists
        if (habitEntity == null) {
            throw new RuntimeException("Habit not found with ID: " + habitLogRequest.getHabitId());
        }

        // Create and save the new log
        HabitLogEntity habitLogEntity = HabitLogEntity.builder()
                .habitLogId(UUID.randomUUID())
                .logDate(LocalDateTime.now().minusDays(1))
                .status(habitLogRequest.getStatus())
                .xpEarned(xpEarned)
                .habits(habitEntity)
                .build();

        return habitLogRepository.createNewHabitLogRepo(habitLogRequest);
    }

}