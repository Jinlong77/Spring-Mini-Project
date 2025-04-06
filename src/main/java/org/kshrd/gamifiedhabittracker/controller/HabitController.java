package org.kshrd.gamifiedhabittracker.controller;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.service.HabitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.kshrd.gamifiedhabittracker.constant.Constant.HABIT_API;

@RestController
@RequestMapping(HABIT_API)
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping
    public String getAllHabits() {
        List<?> habits = habitService.getAllHabitsByUserId("userId");
        return "Get all habits";
    }
}
