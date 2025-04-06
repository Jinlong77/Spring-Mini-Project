package org.kshrd.gamifiedhabittracker.service.implementation;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.exception.ApiException;
import org.kshrd.gamifiedhabittracker.exception.ResourceNotFoundException;
import org.kshrd.gamifiedhabittracker.model.dto.*;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;
import org.kshrd.gamifiedhabittracker.repository.*;
import org.kshrd.gamifiedhabittracker.service.HabitLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class HabitLogServiceImp implements HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;
    private final AppUserRepository appUserRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HabitLogEntity createNewHabitLogService(HabitLogRequest habitLogRequest) {
        // 1. Get the habit to verify it exists
        HabitEntity habit = habitRepository.getHabitById(habitLogRequest.getHabitId());
        if (habit == null) {
            throw new ResourceNotFoundException("The habit id "+ habitLogRequest.getHabitId() + " has not been found.");
        }
        // 2. Count existing logs for this habit
//        int count = habitLogRepository.countLogsByHabitId(habitLogRequest.getHabitId());
        // 3. Calculate XP earned (fixed 10 XP per log)
        int xpEarned = 0;

        if (habitLogRequest.getStatus().equals("MISSED"))
            xpEarned = 0;
        else if (habitLogRequest.getStatus().equals("COMPLETED"))
            xpEarned = 10;

        // 4. Create and save the log
//        HabitLogEntity habitLog = HabitLogEntity.builder()
//                .logDate(LocalDate.now())
//                .status(habitLogRequest.getStatus())
//                .xpEarned(xpEarned)
//                .habits(HabitEntity.builder()
//                        .habitId(habitLogRequest.getHabitId())
//                        .build())
//                .build();

        HabitLogEntity habitLog =  habitLogRepository.createNewHabitLogRepo(habitLogRequest, LocalDate.now(), xpEarned);

        if (habitLog == null)
            throw new ApiException("Failed to create habit log");

        // 5. Update user's total XP - accessing through nested appUser object
//        updateUserXp(UUID.fromString(habit.getAppUser().getUserId()), xpEarned);
        UUID userId = UUID.fromString(habit.getAppUser().getUserId());
        updateUserXp(userId, xpEarned);

        return habitLog;
    }

    private void updateUserXp(UUID userId, int xpToAdd) {
        AppUserEntity user = appUserRepository.getAppUserRepo(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User with id " + userId + " not found.");
        }

        int currentXp = user.getXp();
        int newXp = currentXp + xpToAdd;
        appUserRepository.updateUserXp(userId, newXp);
        checkForLevelUp(user, newXp);
    }

    private void checkForLevelUp(AppUserEntity user, int newXp) {
        int newLevel = newXp / 100;
        if (newLevel > user.getLevel()) {
            appUserRepository.updateLevel(UUID.fromString(user.getUserId()), newLevel);
        }
    }

    @Override
    public List<HabitLogEntity> getHabitLogByIdService(UUID habitId, Integer page, Integer size ) {
        return habitLogRepository.getHabitLogByIdRepo(habitId, page, size );
    }

}