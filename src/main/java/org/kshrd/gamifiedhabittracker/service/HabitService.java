package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitDto;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    HabitEntity getHabitById(UUID habitId);
    void removeHabitById(UUID habitId);
    List<HabitEntity> getAllHabits(Integer offset, Integer limit);
    HabitEntity addNewHabit(HabitDto habitDto);
    HabitEntity updateHabitById(UUID habitId, HabitDto habitDto);
}
