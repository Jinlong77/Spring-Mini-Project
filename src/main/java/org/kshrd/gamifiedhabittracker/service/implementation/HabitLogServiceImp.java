package org.kshrd.gamifiedhabittracker.service.implementation;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;
import org.kshrd.gamifiedhabittracker.repository.HabitLogRepository;
import org.kshrd.gamifiedhabittracker.repository.HabitRepository;
import org.kshrd.gamifiedhabittracker.service.HabitLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class HabitLogServiceImp implements HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;
    @Override
    public HabitLogEntity createNewHabitLogService(HabitLogRequest habitLogRequest) {
        // Get how many times this habit has already been logged
        int count = habitLogRepository.countLogsByHabitId(habitLogRequest.getHabitId());

        // XP logic: (current count + 1) * 10
        int xpEarned = 10;

        // Create the entity
        HabitLogEntity log = HabitLogEntity.builder()
                .logDate(LocalDate.now())
                .status(habitLogRequest.getStatus())
                .xpEarned(xpEarned)
                .build();

        // Call the repo to insert
        habitLogRepository.createNewHabitLogRepo(habitLogRequest, LocalDate.now(), xpEarned);

        return log;
    }




    @Override
    public List<HabitLogEntity> getHabitLogByIdService(UUID habitId) {
        return habitLogRepository.getHabitLogByIdRepo(habitId);
    }

}