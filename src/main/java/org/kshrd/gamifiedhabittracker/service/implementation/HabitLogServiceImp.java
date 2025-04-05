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
        HabitLogEntity log = HabitLogEntity.builder()
                .logDate(LocalDate.now())
                .status(habitLogRequest.getStatus())
                .xpEarned(10)
                .habits(null) // assuming this is set already
                .build();
       return habitLogRepository.createNewHabitLogRepo(habitLogRequest);
    }


    @Override
    public List<HabitLogEntity> getHabitLogByIdService(UUID habitId) {
        return habitLogRepository.getHabitLogByIdRepo(habitId);
    }

}