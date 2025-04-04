package org.kshrd.gamifiedhabittracker.controller;

import org.kshrd.gamifiedhabittracker.service.HabitLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.kshrd.gamifiedhabittracker.constant.Constant.HABIT_LOG_API;

@RestController
@RequestMapping(HABIT_LOG_API)
public class HabitLogController {

    //dependencies injection
    private final HabitLogService habitLogService;
    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }


    //endpoints here
}
