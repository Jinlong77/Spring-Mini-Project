package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;

import java.util.List;
import java.util.UUID;

public interface HabitLogService  {

    //post habit
    HabitLogEntity createNewHabitLogService(HabitLogRequest habitLogRequest) ;

     //get habit log id
     List<HabitLogEntity> getHabitLogByIdService(UUID habitId, Integer page, Integer size );
}
