package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.dto.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;

import java.util.List;
import java.util.UUID;

public interface HabitLogService  {
     HabitLogEntity createNewHabitLogService(HabitLogRequest habitLogRequest) ;


     //na
     List<HabitLogEntity> getHabitLogByIdService(UUID habitId );
}
