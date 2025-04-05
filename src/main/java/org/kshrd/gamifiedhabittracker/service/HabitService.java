package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;

import java.util.List;
import java.util.UUID;

public interface HabitService {

    List<HabitEntity> getHabitsByUserId(UUID userId);
}
