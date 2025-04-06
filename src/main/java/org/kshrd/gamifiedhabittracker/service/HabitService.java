package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.HabitEntity;

import java.util.List;

public interface HabitService {

    List<HabitEntity> getAllHabitsByUserId(String userId);
}
