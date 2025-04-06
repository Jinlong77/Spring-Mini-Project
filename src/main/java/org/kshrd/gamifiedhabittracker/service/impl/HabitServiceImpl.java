package org.kshrd.gamifiedhabittracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.exception.ResourceNotFoundException;
import org.kshrd.gamifiedhabittracker.model.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitDto;
import org.kshrd.gamifiedhabittracker.repository.HabitRepository;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.kshrd.gamifiedhabittracker.service.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final AppUserService appUserService;

    @Override
    public HabitEntity getHabitById(UUID habitId) {
        HabitEntity habit = habitRepository.findHabitById(habitId, appUserService.getCurrentAppUserByID().getUserId());
        if (habit == null)
            throw new ResourceNotFoundException("The habit id " + habitId + " has not been found.");
        return habit;
    }

    @Override
    public void removeHabitById(UUID habitId) {
        var result = habitRepository.deleteHabitById(habitId, appUserService.getCurrentAppUserByID().getUserId());
        if (result == 0)
            throw new ResourceNotFoundException("The habit id " + habitId + " has not been found.");
    }

    @Override
    public List<HabitEntity> getAllHabits(Integer offset, Integer limit) {
        return habitRepository.findAllHabits(offset, limit, appUserService.getCurrentAppUserByID().getUserId());
    }

    @Override
    public HabitEntity addNewHabit(HabitDto habitDto) {
        return habitRepository.insertNewHabit(habitDto, appUserService.getCurrentAppUserByID().getUserId());
    }

    @Override
    public HabitEntity updateHabitById(UUID habitId, HabitDto habitDto) {
        HabitEntity habit = habitRepository.updateHabitById(habitId, habitDto, appUserService.getCurrentAppUserByID().getUserId());
        if (habit == null)
            throw new ResourceNotFoundException("The habit id " + habitId + " has not been found.");
        return habit;
    }
}
