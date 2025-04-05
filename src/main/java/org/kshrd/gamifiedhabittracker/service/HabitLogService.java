package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.dto.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;

public interface HabitLogService  {
    HabitLogEntity createNewHabitLogService(HabitLogRequest habitLogRequest);
}
