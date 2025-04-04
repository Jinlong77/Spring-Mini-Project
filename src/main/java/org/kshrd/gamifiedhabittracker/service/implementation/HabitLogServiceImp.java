package org.kshrd.gamifiedhabittracker.service.implementation;

import org.kshrd.gamifiedhabittracker.repository.HabitLogRepository;
import org.kshrd.gamifiedhabittracker.service.HabitLogService;
import org.springframework.stereotype.Service;

@Service
public class HabitLogServiceImp implements HabitLogService {

    //dependencies injection
    private final HabitLogRepository habitLogRepository;
    public HabitLogServiceImp(HabitLogRepository habitLogRepository) {
        this.habitLogRepository = habitLogRepository;
    }

    //service logic

}
