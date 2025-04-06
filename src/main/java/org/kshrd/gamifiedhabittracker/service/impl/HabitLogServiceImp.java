package org.kshrd.gamifiedhabittracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.enumeration.HabitStatus;
import org.kshrd.gamifiedhabittracker.exception.ApiException;
import org.kshrd.gamifiedhabittracker.exception.ResourceNotFoundException;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;
import org.kshrd.gamifiedhabittracker.model.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.domain.UserPrincipal;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;
import org.kshrd.gamifiedhabittracker.repository.*;
import org.kshrd.gamifiedhabittracker.service.HabitLogService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        AppUserEntity user = appUserRepository.findAppUserByEmail(userPrincipal.getUsername());
        HabitEntity habit = habitRepository.findHabitById(habitLogRequest.getHabitId(), user.getUserId());
        if (habit == null) {
            throw new ResourceNotFoundException("The habit id "+ habitLogRequest.getHabitId() + " has not been found.");
        }

        HabitStatus status = habitLogRequest.getStatus();
        System.out.println("Request status: " + status);
        int xpEarned = 0;
        if (status == HabitStatus.MISSED) {
            xpEarned = 0;
        } else if (status == HabitStatus.COMPLETED) {
            xpEarned = 10;
        } else {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        System.out.println("Calculated xpEarned: " + xpEarned);


        System.out.println("xpEarned before insertion: " + xpEarned);
        HabitLogEntity habitLog = habitLogRepository.createNewHabitLogRepo(habitLogRequest, LocalDate.now(), xpEarned);
        System.out.println("Inserted habitLog: " + habitLog);

        if (habitLog == null)
            throw new ApiException("Failed to create habit log");

        UUID userId = habit.getUser().getUserId();
        updateUserXp(userId, xpEarned);

        AppUserEntity updatedUser = appUserRepository.getAppUserRepo(userId);
        habit.setUser(updatedUser);
        habitLog.setHabits(habit);

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
            appUserRepository.updateLevel(user.getUserId(), newLevel);
        }
    }

    @Override
    public List<HabitLogEntity> getHabitLogByIdService(UUID habitId, Integer page, Integer size ) {
        return habitLogRepository.getHabitLogByIdRepo(habitId, page, size );
    }

}